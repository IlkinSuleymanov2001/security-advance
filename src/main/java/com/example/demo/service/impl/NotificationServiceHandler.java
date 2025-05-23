package com.example.demo.service.impl;


import com.example.demo.model.request.NotificationDto;
import com.example.demo.service.impl.inter.NotificationService;
import com.example.demo.service.impl.provider.factory.NotificationProviderFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class NotificationServiceHandler implements NotificationService {

    NotificationProviderFactory notificationProviderFactory;


    @Async
    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 4, backoff = @Backoff(delay = 10000))
    public void sendAsync(NotificationDto notificationDto) {
        notificationProviderFactory.
                getNotificationProvider(notificationDto.getProvider()).
                sendNotification(notificationDto);
    }


    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 4, backoff = @Backoff(delay = 2000))
    public void send(NotificationDto notificationDto) {
        notificationProviderFactory.
                getNotificationProvider(notificationDto.getProvider()).
                sendNotification(notificationDto);


    }


}
