package com.poc.alerts.routing;

import org.springframework.stereotype.Component;

import com.poc.alerts.exception.UnrecognisedEventTypeException;
import com.poc.alerts.model.EventType;

@Component
public class RouteMapperImpl implements RouteMapper {

   
	@Override
	public NotificationChannel route(EventType eventType, String alertType) {


        if (eventType == null) {
            throw new UnrecognisedEventTypeException("EventType cannot be null");
        }

        switch (eventType) {

            case CUSTOMER_CREATED:
                return NotificationChannel.SMS;

            case ACCOUNT_CREATED:
                return NotificationChannel.EMAIL;

            case LOAN_DISBURSED:
                return NotificationChannel.SMS;

            case PAST_DUE:
                return NotificationChannel.SMS;

            case TPH_TRANSFER:
                return NotificationChannel.EMAIL;
                
            case customer_created:
                return NotificationChannel.SMS;

            case account_created:
                return NotificationChannel.EMAIL;

            case loan_disbursed:
                return NotificationChannel.SMS;

            case past_due:
                return NotificationChannel.SMS;

            case tph_transfer:
                return NotificationChannel.EMAIL;

            default:
                throw new UnrecognisedEventTypeException("Unknown event type: " + eventType);
        }
    
	}
}