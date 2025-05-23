package com.example.demo.service.impl.provider.strategy;

import com.example.demo.model.request.NotificationDto;

public interface NotificationProvider {


    void sendNotification(NotificationDto notification);
}
