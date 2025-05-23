package com.example.demo.mapper;

import com.example.demo.dao.entity.CardEntity;
import com.example.demo.dao.entity.CardOwnerEntity;
import com.example.demo.model.request.card.CreateCardRequest;
import com.example.demo.model.response.CardResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CardMapper {


    //@Mapping(target = "placeHolder", source = "placeHolder")
    CardResponse toCardResponse(CardEntity card);


    default CardEntity toCardEntity(CreateCardRequest cardRequest, CardOwnerEntity owner) {
        return CardEntity.builder()
                .cardType(cardRequest.getCardType())
                .placeHolder(cardRequest.getPlaceHolder())
                .owner(owner)
                .build();
    }

}
