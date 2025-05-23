package com.example.demo.service.impl.inter;

import com.example.demo.dao.entity.CardOwnerEntity;
import com.example.demo.model.request.card.CardOwnerRequest;
import com.example.demo.model.response.CardOwnerResponse;

public interface CardOwnerService {

    CardOwnerEntity createCardOwnerEntity(CardOwnerRequest cardOwnerRequest);

    CardOwnerEntity getCardOwnerEntity(Long cardOwnerId);

    CardOwnerResponse getCardOwner(Long cardOwnerId);

}
