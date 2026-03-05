package com.poc.alerts.payload;

import java.util.Map;

import com.poc.alerts.model.EventType;
import com.poc.alerts.model.NotificationRequest;
import com.poc.alerts.routing.NotificationChannel;

public interface NotificationPayloadBuilder {

	NotificationRequest build(EventType eventType, NotificationChannel channel, Map<String, Object> payload,
			String eventId, String timestamp);

}
