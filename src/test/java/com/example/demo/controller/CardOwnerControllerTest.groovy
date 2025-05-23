package com.example.demo.controller

import com.example.demo.client.CardClient
import com.example.demo.exception.GlobalExceptionHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class CardOwnerControllerTest extends Specification {

    CardClient cardClient = Mock()
    MockMvc mockMvc
    CardOwnerController cardOwnerController
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    void setup() {
        cardOwnerController = new CardOwnerController(cardClient)
        mockMvc = MockMvcBuilders.standaloneSetup(cardOwnerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
    }

    def "testGetCardOwner"() {
        given:

        def id = random.nextObject(Long)
        def url = "/v1/card/owner/$id"

        when:
        def result = mockMvc.
                perform(MockMvcRequestBuilders.get(new URI(url))
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                )
                .andReturn()


        then:
        1 * cardClient.getCardOwner(id)
        result.response.status == HttpStatus.OK.value()


    }

}
