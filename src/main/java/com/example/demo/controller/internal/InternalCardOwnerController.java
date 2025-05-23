package com.example.demo.controller.internal;


import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.response.CardOwnerResponse;
import com.example.demo.service.impl.inter.CardOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("internal/v1/card/owner")
@RequiredArgsConstructor
@Slf4j
public class InternalCardOwnerController {  // private controller


    CardOwnerService cardOwnerService;


    @GetMapping("/{id}")
    public ResponseModel<CardOwnerResponse> getCardOwner(@PathVariable Long id) {
        CardOwnerResponse cardOwner = cardOwnerService.getCardOwner(id);
        return ResponseModel.<CardOwnerResponse>builder()
                .data(cardOwner)
                .build();
    }


}
