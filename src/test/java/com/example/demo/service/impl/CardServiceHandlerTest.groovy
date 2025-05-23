package com.example.demo.service.impl

import com.example.demo.dao.entity.CardEntity
import com.example.demo.dao.entity.CardOwnerEntity
import com.example.demo.dao.repository.CardRepository
import com.example.demo.mapper.CardMapperImpl
import com.example.demo.mapper.CardOwnerMapper
import com.example.demo.model.request.card.CardRequest
import com.example.demo.service.card.CardCvvService
import com.example.demo.service.card.CardPanService
import com.example.demo.service.impl.inter.CardOwnerService
import com.example.demo.service.impl.inter.CardService
import com.example.demo.service.impl.inter.NotificationService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardServiceHandlerTest extends Specification {

    CardRepository cardRepository;
    CardMapperImpl cardMapper;
    CardOwnerMapper cardOwnerMapper;
    CardOwnerService cardOwnerService;
    CardCvvService cardCvvService;
    CardPanService cardPanService;
    NotificationService notificationService;
    CardService cardService;

    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def setup() {
        cardRepository = Mock()
        cardMapper = Mock()
        cardOwnerMapper = Mock()
        cardOwnerService = Mock()
        cardCvvService = Mock()
        cardPanService = Mock()
        notificationService = Mock()
        cardService = new CardServiceHandler(
                cardRepository,
                cardMapper,
                cardOwnerMapper,
                cardOwnerService,
                cardCvvService,
                cardPanService,
                notificationService
        )

    }

    def "createCard  success case"() {

        given:
        def ownerId = null
        def cardRequest = random.nextObject(CardRequest)
        def cardOwnerRequest = cardRequest.owner
        def cardOwnerEntity = CardOwnerEntity.builder().name(cardOwnerRequest.getName())
                .fin(cardOwnerRequest.getFin())
                .legalId(cardOwnerRequest.getLegalId())
                .surname(cardOwnerRequest.getSurname())
                .fatherName(cardOwnerRequest.getFatherName())
                .nationality(cardOwnerRequest.getNationality())
                .age(cardOwnerRequest.getAge())
                .email(cardOwnerRequest.getEmail())
                .build()

        def cardEntities = cardRequest.getCards().stream().map(card ->
                cardMapper.toCardEntity(card, cardOwnerEntity)).toList();

        when:
        cardService.createCard(cardRequest, ownerId)


        then:
        1 * cardOwnerService.createCardOwnerEntity(cardRequest.owner) >> cardOwnerEntity
        cardRequest.getCards().size() * cardMapper.toCardEntity(_, _)
        1 * cardCvvService.setCvv(cardEntities)
        1 * cardPanService.setPan(cardEntities)
        1 * cardRepository.saveAll(cardEntities)
        cardEntities.size() * cardMapper.toCardResponse(cardEntities)
        1 * notificationService.send(_)

    }

    def "getAllCardsByOwnerId success case"() {
        given:
        def ownerId = 1L;
        def entities = Arrays.asList(random.nextObject(CardEntity[]))

        when:
        cardService.getAllCardsByOwnerId(ownerId)

        then:
        1 * cardRepository.findByOwner_Id(ownerId) >> entities
        and:
        entities.size() * cardMapper.toCardResponse(_)

    }

    def "getAllCardsByOwnerId error case "() {
        given:
        def ownerId = null;
        when:
        cardService.getAllCardsByOwnerId(ownerId)

        then:
        1 * cardRepository.findByOwner_Id(ownerId)
        thrown(NullPointerException)
        0 * cardMapper.toCardResponse(_)

    }

}




