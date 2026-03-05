package com.poc.alerts.validation;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.poc.alerts.model.EventType;

@Service
public class HeaderValidationService {

    public EventType validateEventType(String eventTypeHeader) {

        if (eventTypeHeader == null || eventTypeHeader.isBlank()) {
            throw new InvalidHeaderException("event-type header missing");
        }

        try {
            return EventType.valueOf(eventTypeHeader);
        } catch (Exception e) {
            throw new InvalidHeaderException("Invalid event-type: " + eventTypeHeader);
        }
    }

    public void validateEventId(String eventId) {

        if (eventId == null || eventId.isBlank()) {
            throw new InvalidHeaderException("event-id header missing");
        }

        try {
            UUID.fromString(eventId);
        } catch (Exception e) {
            throw new InvalidHeaderException("Invalid event-id format");
        }
    }

    public void validateTimestamp(String timestamp) {

        if (timestamp == null || timestamp.isBlank()) {
            throw new InvalidHeaderException("timestamp header missing");
        }
    }
}