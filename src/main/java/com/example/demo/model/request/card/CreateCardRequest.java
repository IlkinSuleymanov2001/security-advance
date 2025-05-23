package com.example.demo.model.request.card;


import com.example.demo.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CreateCardRequest {

    @JsonProperty(value = "placeHolder", required = true)
    String placeHolder;

    @JsonProperty(value = "cardType", required = true)
    CardType cardType;

    @JsonProperty(value = "cardValidityYear", required = true)
    Integer cardValidityYear;


}
