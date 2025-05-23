package com.example.demo.model.request;


import com.example.demo.model.enums.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class NotificationDto {

    String title;
    String message;
    String email;
    String phoneNumber;
    @ToString.Exclude
    Provider provider;
}
