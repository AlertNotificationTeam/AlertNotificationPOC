package com.poc.alerts.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.alerts.model.NotificationRequest;

@Component
public class LogProofEmitter {

    private static final Logger log = LoggerFactory.getLogger(LogProofEmitter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void emit(NotificationRequest request) {

        try {

            String json = objectMapper.writeValueAsString(request);

            log.info("NOTIFICATION_INVOCATION_PROOF xRequestId={} payload={}",
                    request.getxRequestId(),
                    json);

        } catch (Exception e) {

            log.error("Failed to emit notification proof log", e);
        }
    }
}