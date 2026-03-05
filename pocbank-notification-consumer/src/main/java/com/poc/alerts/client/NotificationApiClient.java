package com.poc.alerts.client;

import org.springframework.stereotype.Component;

import com.poc.alerts.model.NotificationRequest;

@Component
public class NotificationApiClient {

    public void sendNotification(NotificationRequest request) {

        System.out.println("[T3] NotificationApiClient sending notification");

        System.out.println("Channel  : " + request.getChannel());
        System.out.println("EventId  : " + request.getEventId());
        System.out.println("Message  : " + request.getMessage());
        
        /**
         * 
         * Integration
         * 
         */
         
        
        
    }
}