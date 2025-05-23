package com.example.demo.model.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenClaimsSet {

    private String iss;
    @JsonProperty("sub")
    private String userId;
    private Date createdTime;
    private Date exp;
    private List<String> roles;


}
