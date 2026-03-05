package com.poc.alerts.model;

import com.poc.alerts.routing.NotificationChannel;

public class NotificationRequest {
	
	private String eventId;
    private EventType eventType;
    private NotificationChannel channel;
    private String destination;
    private String message;
    private String timestamp;
    private String xRequestId;
    
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public NotificationChannel getChannel() {
		return channel;
	}
	public void setChannel(NotificationChannel channel) {
		this.channel = channel;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getxRequestId() {
		return xRequestId;
	}
	public void setxRequestId(String xRequestId) {
		this.xRequestId = xRequestId;
	}

    
}
