package com.example.demo.model.criteria;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria {


    @JsonProperty(value = "ageFrom")
    private Integer ageFrom;

    @JsonProperty(value = "ageTo")
    private Integer ageTo;

    @JsonProperty(value = "createdAtFrom")
    private LocalDateTime createdAtFrom;

    @JsonProperty(value = "CreatedAtTo")
    private LocalDateTime CreatedAtTo;

    @JsonProperty(value = "birthPlace")
    private String birthPlace;
}
