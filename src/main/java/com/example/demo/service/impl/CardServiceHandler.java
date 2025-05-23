package com.example.demo.service.impl;

import com.example.demo.aspect.performance.LogElapsedTime;
import com.example.demo.dao.entity.CardEntity;
import com.example.demo.dao.entity.CardOwnerEntity;
import com.example.demo.dao.repository.CardRepository;
import com.example.demo.mapper.CardMapper;
import com.example.demo.mapper.CardOwnerMapper;
import com.example.demo.model.request.NotificationDto;
import com.example.demo.model.request.card.CardOwnerRequest;
import com.example.demo.model.request.card.CardRequest;
import com.example.demo.model.request.card.CreateCardRequest;
import com.example.demo.model.response.CardResponse;
import com.example.demo.model.response.CreatedCardResponse;
import com.example.demo.service.card.CardCvvService;
import com.example.demo.service.card.CardPanService;
import com.example.demo.service.impl.inter.CardOwnerService;
import com.example.demo.service.impl.inter.CardService;
import com.example.demo.service.impl.inter.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.model.enums.Provider.EMAIL;
import static lombok.AccessLevel.PRIVATE;


@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@LogElapsedTime
public class CardServiceHandler implements CardService {

    CardRepository cardRepository;
    CardMapper cardMapper;
    CardOwnerMapper cardOwnerMapper;
    CardOwnerService cardOwnerService;
    CardCvvService cardCvvService;
    CardPanService cardPanService;
    NotificationService notificationService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public CreatedCardResponse createCard(CardRequest cardRequest, Long ownerId) {

        CardOwnerEntity cardOwnerEntity = getCardOwnerEntity(cardRequest.getOwner(), ownerId);


        List<CardEntity> cardEntities = cardRequest.getCards().stream().map(card ->
                cardMapper.toCardEntity(card, cardOwnerEntity)).toList();


        cardCvvService.setCvv(cardEntities);
        cardPanService.setPan(cardEntities);

        setExpireDate(cardEntities, cardRequest.getCards());

        cardRepository.saveAll(cardEntities);

        List<CardResponse> cardResponses = cardEntities.stream().map(cardMapper::toCardResponse).toList();

        NotificationDto notificationDto = NotificationDto.builder()
                .email(cardOwnerEntity.getEmail())
                .provider(EMAIL)
                .message("salam")
                .title("create card ")
                .build();

        notificationService.send(notificationDto);  //shock effect   notificationService.sendAsync(notificationDto);

        return CreatedCardResponse
                .builder()
                .cards(cardResponses)
                .owner(cardOwnerMapper.toCardOwnerResponse(cardOwnerEntity))
                .build();
    }


    //    @Cacheable("cards")
//    @CachePut("cards")
//    @CacheEvict(allEntries = true,value = "cards")
// internal cache  speeds from than external cache
    @Override
    public List<CardResponse> getAllCardsByOwnerId(Long ownerId) {
        List<CardEntity> cardEntities = cardRepository.findByOwner_Id(ownerId);
        return cardEntities.stream().map(cardMapper::toCardResponse).toList();
    }


    private CardOwnerEntity getCardOwnerEntity(CardOwnerRequest owner, Long ownerId) {
        if (ownerId != null) {
            return cardOwnerService.getCardOwnerEntity(ownerId);
        }
        return cardOwnerService.createCardOwnerEntity(owner);
    }

    private void setExpireDate(List<CardEntity> cardEntities, List<CreateCardRequest> cards) {
//
//        for (int i = 0; i < cards.size(); i++) {
//            LocalDate now = LocalDate.now();
//            now = now.plusYears(cards.get(i).getCardValidityYear());
//            cardEntities.get(i).setExpiryDate(now);
//        }
    }


}
