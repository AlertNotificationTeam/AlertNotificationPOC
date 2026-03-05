package com.poc.alerts.dlt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DltHandlerService {

	private static final Logger log =
	        LoggerFactory.getLogger(DltHandlerService.class);
    @DltHandler
    public void handleDlt(String message) {

        log.error("Message sent to DLT: {}", message);

    }
}