package com.poc.alerts.routing;

import com.poc.alerts.model.EventType;

public interface RouteMapper {

    NotificationChannel route(EventType eventType, String alertType);

}
