package com.example.demo.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class OrderTicket {

    String legalId;

    UpdateGuest updateGuest;

    GuestDto guest;

    Long travelId;

}
