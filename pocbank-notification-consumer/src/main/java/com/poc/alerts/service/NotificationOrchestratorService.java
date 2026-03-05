package com.poc.alerts.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.poc.alerts.payload.NotificationPayloadBuilder;
import com.poc.alerts.routing.NotificationChannel;
import com.poc.alerts.routing.RouteMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.alerts.client.NotificationApiClient;
import com.poc.alerts.logging.LogProofEmitter;
import com.poc.alerts.model.EventType;
import com.poc.alerts.model.NotificationRequest;

@Service
public class NotificationOrchestratorService {

    private final RouteMapper routeMapper;
    private final NotificationPayloadBuilder payloadBuilder;
    private final NotificationApiClient notificationApiClient;
    private final LogProofEmitter logEmitter;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotificationOrchestratorService(RouteMapper routeMapper,
                                           NotificationPayloadBuilder payloadBuilder,
                                           NotificationApiClient notificationApiClient,
                                           LogProofEmitter logEmitter) {

        this.routeMapper = routeMapper;
        this.payloadBuilder = payloadBuilder;
        this.notificationApiClient = notificationApiClient;
        this.logEmitter = logEmitter;
    }

    @SuppressWarnings("unchecked")
	public void processEvent(EventType eventType, String eventId, String message) {

        try {

            System.out.println("\n========== [ORCHESTRATOR] ==========");
            System.out.println("EventType : " + eventType);
            System.out.println("EventId   : " + eventId);

            Map<String,Object> payload =
                    objectMapper.readValue(message, Map.class);

            NotificationChannel channel =
                    routeMapper.route(eventType, "email");

            System.out.println("Routing to channel : " + channel);

            NotificationRequest request =
                    payloadBuilder.build(
                            eventType,
                            channel,
                            payload,
                            eventId,
                            String.valueOf(System.currentTimeMillis())
                    );

            System.out.println("Payload built successfully");

            notificationApiClient.sendNotification(request);

            System.out.println("Notification API invoked");

            logEmitter.emit(request);

            System.out.println("Log proof emitted");
            System.out.println("====================================\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}