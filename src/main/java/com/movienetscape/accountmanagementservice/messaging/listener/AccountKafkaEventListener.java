package com.movienetscape.accountmanagementservice.messaging.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movienetscape.accountmanagementservice.messaging.event.AccountVerifiedEvent;
import com.movienetscape.accountmanagementservice.service.contract.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class AccountKafkaEventListener {

    private final ObjectMapper objectMapper;

    private final AccountService accountService;

    @KafkaListener(topics = "user-verified-topic", groupId = "notification-service")
    public void handleUserRegisteredEvent(String eventJson) {
        try {
            AccountVerifiedEvent event = objectMapper.readValue(eventJson, AccountVerifiedEvent.class);
            log.info("Received UserVerifiedEvent for user Id: {}", event.getUserId());
            accountService.verifyAccount(event.getUserId(),event.isVerified());
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize UserVerifiedEvent: {}", eventJson, e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }
}
