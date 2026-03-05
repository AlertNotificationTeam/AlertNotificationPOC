package com.poc.alerts.payload;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.poc.alerts.config.NotificationApiConfig;
import com.poc.alerts.model.EventType;
import com.poc.alerts.model.NotificationRequest;
import com.poc.alerts.routing.NotificationChannel;

@Component
public class NotificationPayloadBuilderImpl implements NotificationPayloadBuilder {

    private final NotificationApiConfig apiConfig;

    public NotificationPayloadBuilderImpl(NotificationApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    @SuppressWarnings("unused")
	@Override
    public NotificationRequest build(EventType eventType,
                                     NotificationChannel channel,
                                     Map<String, Object> payload,
                                     String eventId,
                                     String timestamp) {

        String apiToken = apiConfig.getApiToken();   // Step-4 usage

        NotificationRequest request = new NotificationRequest();
        
        request.setEventId(eventId);
        request.setEventType(eventType);
        request.setChannel(channel);
        request.setTimestamp(timestamp);
        request.setxRequestId(UUID.randomUUID().toString());

        switch (eventType) {

            case CUSTOMER_CREATED:

                String phone = (String) payload.get("phone");
                String name = (String) payload.get("customerName");

                request.setDestination(phone);
                request.setMessage("Hello " + name + ", welcome to PocBank.");

                break;

            case ACCOUNT_CREATED:

                String email = (String) payload.get("email");
                String accountName = (String) payload.get("customerName");

                request.setDestination(email);
                request.setMessage("Dear " + accountName + ", your account has been created.");

                break;

            default:
                break;
        }

        return request;
    }
}
