package com.example.demo.model.request.card;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CardRequest {


    @JsonProperty(value = "cards")
    List<CreateCardRequest> cards;

    @JsonProperty(value = "owner")
    CardOwnerRequest owner;


}
