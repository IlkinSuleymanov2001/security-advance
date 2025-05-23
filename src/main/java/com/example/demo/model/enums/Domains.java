package com.example.demo.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.example.demo.model.enums.ServiceURI.*;

@RequiredArgsConstructor
@Getter
public enum Domains {

    CARD("CARD_DOMAIN", CARD_SERVICE),
    CARD_OWNER("CARD_OWNER_DOMAIN", CARD_OWNER_SERVICE),
    ORDER("ORDER_DOMAIN", ORDER_SERVICE),
    PRODUCT("PRODUCT_DOMAIN", PRODUCT_SERVICE),
    TRAVEL("TRAVEL_DOMAIN", TRAVEL_SERVICE),
    TICKET("TICKET_DOMAIN", TICKET_SERVICE),
    TEST("TEST_DOMAIN", TEST_SERVICE);

    final String domain;
    final String serviceURI;


}


interface ServiceURI {
    String CARD_SERVICE = "/demo-ms/api/v1/card";
    String CARD_OWNER_SERVICE = "/demo-ms/api/v1/card_owner";
    String ORDER_SERVICE = "/demo-ms/api/v1/order";
    String PRODUCT_SERVICE = "/demo-ms/api/v1/product";
    String TICKET_SERVICE = "/demo-ms/api/v1/ticket";
    String TRAVEL_SERVICE = "/demo-ms/api/v1/travel";
    String TEST_SERVICE = "/demo-ms/api/v1/test";

}
