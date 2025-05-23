package com.example.demo.controller;


import com.example.demo.aspect.security.WritePermission;
import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.request.card.CardRequest;
import com.example.demo.model.response.CardResponse;
import com.example.demo.model.response.CreatedCardResponse;
import com.example.demo.service.impl.inter.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/card")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class CardController {

    CardService cardService;

    @GetMapping("/all")
    @ResponseStatus(OK)
    @WritePermission
    public ResponseModel<List<CardResponse>> getOrders(Long id) {
        List<CardResponse> cards = cardService.getAllCardsByOwnerId(id);
        return ResponseModel.<List<CardResponse>>builder()
                .data(cards)
                .build();
    }

    @PostMapping("/create")
    @ResponseStatus(CREATED)
   @WritePermission
    public ResponseModel<CreatedCardResponse> createOrder(
            @RequestBody CardRequest cardRequest,
            @RequestParam(required = false) Long ownerId) {

        CreatedCardResponse createdCardResponse = cardService.createCard(cardRequest, ownerId);
        return ResponseModel
                .<CreatedCardResponse>builder()
                .data(createdCardResponse)
                .build();
    }


}
