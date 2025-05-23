package com.example.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CreatedCardResponse {

    List<CardResponse> cards;

    CardOwnerResponse owner;


}
