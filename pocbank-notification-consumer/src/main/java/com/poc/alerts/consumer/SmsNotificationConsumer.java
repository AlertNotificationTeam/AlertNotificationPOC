package com.poc.alerts.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.poc.alerts.header.HeaderExtractor;
import com.poc.alerts.idempotency.IdempotencyService;
import com.poc.alerts.model.EventType;
import com.poc.alerts.service.NotificationOrchestratorService;
import com.poc.alerts.validation.HeaderValidationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SmsNotificationConsumer {

	private static final Logger log =
            LoggerFactory.getLogger(SmsNotificationConsumer.class);
	
    private final HeaderValidationService validationService;
    private final IdempotencyService idempotencyService;
    private final NotificationOrchestratorService orchestratorService;

    public SmsNotificationConsumer(
            HeaderValidationService validationService,
            IdempotencyService idempotencyService,
            NotificationOrchestratorService orchestratorService) {

        this.validationService = validationService;
        this.idempotencyService = idempotencyService;
        this.orchestratorService = orchestratorService;
    }
    
    @KafkaListener(
            topics = "notifications.events",
            groupId = "notification-cg-email"
    )
    public void consume(ConsumerRecord<String, String> record) {

        log.info("==================================================");
        log.info("                [T2-KAFKA-CONSUMER]               ");
        log.info("==================================================");

        log.info("Topic       : {}", record.topic());
        log.info("Partition   : {}", record.partition());
        log.info("Offset      : {}", record.offset());
        log.info("Key         : {}", record.key());

        /* ---------------- HEADER EXTRACTION ---------------- */

        String eventTypeHeader = HeaderExtractor.extractHeader(record, "event-type");
        String eventId = HeaderExtractor.extractHeader(record, "event-id");
        String timestamp = HeaderExtractor.extractHeader(record, "timestamp");

        log.info("Header event-type : {}", eventTypeHeader);
        log.info("Header event-id   : {}", eventId);
        log.info("Header timestamp  : {}", timestamp);
        log.info("Payload           : {}", record.value());

        /* ---------------- HEADER VALIDATION ---------------- */

        EventType eventType = validationService.validateEventType(eventTypeHeader);
        validationService.validateEventId(eventId);
        validationService.validateTimestamp(timestamp);

        log.info("Header validation successful");

        /* ---------------- IDEMPOTENCY CHECK ---------------- */

        if (idempotencyService.isDuplicate(eventId)) {

            log.warn("Duplicate event detected. Skipping eventId={}", eventId);
            return;

        }

        log.info("Idempotency check passed");

        /* ---------------- ORCHESTRATION ---------------- */

        log.info("Forwarding event to orchestrator...");
        log.info("eventType : {}", eventType);
        log.info("eventId   : {}", eventId);

        orchestratorService.processEvent(eventType, eventId, record.value());

        log.info("Event successfully forwarded to orchestrator");

        log.info("==================================================");
    }
}