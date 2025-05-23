package com.example.demo.service.impl

import com.example.demo.dao.entity.CardOwnerEntity
import com.example.demo.dao.repository.CardOwnerRepository
import com.example.demo.exception.NotFoundException
import com.example.demo.mapper.CardOwnerMapper
import com.example.demo.model.enums.ErrorInfo
import com.example.demo.model.request.card.CardOwnerRequest
import com.example.demo.service.impl.inter.CardOwnerService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardOwnerServiceHandlerTest extends Specification {

    CardOwnerRepository cardOwnerRepository = Mock()
    CardOwnerMapper cardOwnerMapper = Mock()
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    CardOwnerService cardOwnerService;


    def setup() {
        cardOwnerService = new CardOwnerServiceHandler(
                cardOwnerRepository,
                cardOwnerMapper
        )
    }


    def "createCardOwnerEntityTest success case"() {
        given:
        def object = random.nextObject(CardOwnerRequest)

        def cardOwnerEntity = random.nextObject(CardOwnerEntity) // ✅ Mock the entity
        cardOwnerEntity.id == null //set null


        when:
        cardOwnerService.createCardOwnerEntity(object)

        then:
        1 * cardOwnerMapper.toCardOwnerEntity(object) >> cardOwnerEntity // ✅ Mock return value
        1 * cardOwnerRepository.save(cardOwnerEntity)
        cardOwnerEntity.id != null


    }

    def "getCardOwnerEntityTest success case"() {
        given:
        def id = random.nextObject(Long)
        def cardOwnerEntity = random.nextObject(CardOwnerEntity)

        when:
        cardOwnerService.getCardOwnerEntity(id)

        then:
        1 * cardOwnerRepository.findById(id) >> Optional.of(cardOwnerEntity) // ✅ Mock return value


    }

    def "getCardOwnerEntityTest Error case"() {
        given:
        def id = random.nextObject(Long)

        when:
        cardOwnerService.getCardOwnerEntity(id)

        then:
        1 * cardOwnerRepository.findById(id) >> Optional.empty() // ✅ Mock return value
        def e = thrown(NotFoundException)
        e.message == ErrorInfo.CARD_OWNER_NOT_FOUND.message
        e.errorCode == ErrorInfo.CARD_OWNER_NOT_FOUND.errorCode


    }
}
