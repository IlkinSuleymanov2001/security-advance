package com.example.demo.mapper;

import com.example.demo.dao.entity.CardOwnerEntity;
import com.example.demo.model.request.card.CardOwnerRequest;
import com.example.demo.model.response.CardOwnerResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CardOwnerMapper {

    CardOwnerEntity toCardOwnerEntity(CardOwnerRequest cardOwnerRequest);

    CardOwnerResponse toCardOwnerResponse(CardOwnerEntity cardOwnerEntity);
}
