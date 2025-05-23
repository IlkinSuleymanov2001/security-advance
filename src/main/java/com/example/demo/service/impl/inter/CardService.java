package com.example.demo.service.impl.inter;

import com.example.demo.model.request.card.CardRequest;
import com.example.demo.model.response.CardResponse;
import com.example.demo.model.response.CreatedCardResponse;

import java.util.List;

public interface CardService {

    CreatedCardResponse createCard(CardRequest cardRequest, Long ownerId);

    List<CardResponse> getAllCardsByOwnerId(Long ownerId);

}
