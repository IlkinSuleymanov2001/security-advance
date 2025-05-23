package com.example.demo.model.request.user;


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
public class GetUserIdentifierRequest {


    @JsonProperty(value = "userId")
    Long userId;
    @JsonProperty(value = "userEmail")
    String userEmail;
}
