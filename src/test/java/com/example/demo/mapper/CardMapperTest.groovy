package com.example.demo.mapper


import com.example.demo.dao.entity.CardOwnerEntity
import com.example.demo.model.request.card.CreateCardRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardMapperTest extends Specification {

    CardMapper cardMapper = Mock()
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "toCardEntityTest"() {
        given:
        def cardRequest = random.nextObject(CreateCardRequest)
        def owner = random.nextObject(CardOwnerEntity)

        when:
        def entity1 = cardMapper.toCardEntity(cardRequest, owner)

        then:
        entity1.cardType == cardRequest.cardType
        entity1.placeHolder == cardRequest.placeHolder
        entity1.owner == owner

    }

}
