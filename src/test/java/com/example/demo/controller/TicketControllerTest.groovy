package com.example.demo.controller

import com.example.demo.exception.GlobalExceptionHandler
import com.example.demo.model.request.OrderTicket
import com.example.demo.service.impl.TicketServiceHandler
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class TicketControllerTest extends Specification {

    private TicketServiceHandler ticketServiceHandler
    private MockMvc mockMvc
    private TicketController ticketController
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    void setup() {
        ticketServiceHandler = Mock()
        ticketController = new TicketController(ticketServiceHandler)
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()


    }

    def "TestAddTicket"() {
        given:
        def orderTicketDto = random.nextObject(OrderTicket)
        def url = "/v1/ticket/order-ticket"

        ObjectMapper objectMapper = new ObjectMapper()
        def requestBody = objectMapper.writeValueAsString(orderTicketDto)

        when:
        def result = mockMvc.
                perform(MockMvcRequestBuilders.post(new URI(url))
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn()

        then:
        1 * ticketServiceHandler.orderTicket(orderTicketDto)

        result.response.status == HttpStatus.CREATED.value()


    }


}
