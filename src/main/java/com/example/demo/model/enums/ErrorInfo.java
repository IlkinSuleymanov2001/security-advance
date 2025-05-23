package com.example.demo.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public enum ErrorInfo {
    INTERNAL_ERROR("serverde xeta bas verdi", "INTERNAL_ERROR"),
    USER_NOT_FOUND("isdifadeci tapilmadi", "USER_NOT_FOUND"),
    USER_ALREADY_EXITS("USER ARTIQ MOVCUDDUR", "USER_ALREADY_EXITS"),
    VALIDATION_ERROR("inputs is valud errati", "VALIDATION_ERROR"),
    USER_ID_AND_EMAIL_ERROR("id ve ya  email den biri mutleq girilmelidir ", "USER_ID_AND_EMAIL_ERROR"),
    PRODUCT_NOT_FOUND("id ve ya  product koddan den biri mutleq girilmelidir ", "PRODUCT_NOT_FOUND"),
    CARD_OWNER_NOT_FOUND("NOT FOUND ", "CARD_OWNER_NOT_FOUND"),
    CVV_NOT_VALID("NOT valid cvv ", "CVV_NOT_VALID"),
    PAN_NOT_VALID("NOT valid pan ", "PAN_NOT_VALID"),
    GUEST_NOT_FOUND("GUEST_NOT_FOUND", "GUEST_NOT_FOUND"),
    USER_UNAUTHORIZED("USER_UNAUTHORIZED", "USER_UNAUTHORIZED");

    String message;
    String errorCode;


}
