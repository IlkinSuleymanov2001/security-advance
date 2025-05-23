package com.example.demo.controller;


import com.example.demo.aspect.security.ReadPermission;
import com.example.demo.client.CardClient;
import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.client.CardOwnerResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("v1/card_owner")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class CardOwnerController {// public controller
    private final CardClient cardClient;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @ReadPermission
    public ResponseModel<CardOwnerResponseDto> getCardOwner(@PathVariable Long id) {
        return cardClient.getCardOwner(id);

    }

}
