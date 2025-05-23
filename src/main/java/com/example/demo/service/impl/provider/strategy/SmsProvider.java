package com.example.demo.service.impl.provider.strategy;

import com.example.demo.model.request.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SmsProvider implements NotificationProvider {


    @Override
    public void sendNotification(NotificationDto notification) {
        for (int i = 0; i < 10000000; i++) {
            log.info(notification.toString() + " " + i);
        }

    }
}