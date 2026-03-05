package com.poc.alerts.idempotency;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IdempotencyService {

	private static final Logger log =
	        LoggerFactory.getLogger(IdempotencyService.class);
    private final Set<String> processedEvents = ConcurrentHashMap.newKeySet();

    public boolean isDuplicate(String eventId) {

        if (processedEvents.contains(eventId)) {
            log.warn("Duplicate event detected: {}", eventId);
            return true;
        }

        processedEvents.add(eventId);
        return false;
    }
}