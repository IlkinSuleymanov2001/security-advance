package com.example.demo.service.impl.provider.factory;

import com.example.demo.model.enums.Provider;
import com.example.demo.service.impl.provider.strategy.EmailProvider;
import com.example.demo.service.impl.provider.strategy.NotificationProvider;
import com.example.demo.service.impl.provider.strategy.SmsProvider;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NotificationProviderFactory {

    SmsProvider smsProvider;
    EmailProvider emailProvider;

    public NotificationProvider getNotificationProvider(Provider provider) {
        return switch (provider) {
            case SMS -> smsProvider;
            case EMAIL -> emailProvider;
        };
    }
}
