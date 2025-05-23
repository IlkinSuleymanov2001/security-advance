package com.example.demo.queue;


import com.example.demo.exception.QueueException;
import com.example.demo.model.request.TravelRequest;
import com.example.demo.service.impl.TravelServiceHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
@RequiredArgsConstructor
@Slf4j
public class TravelServiceListener {

    private final ObjectMapper mapper;
    private final TravelServiceHandler travelServiceHandler;


    @RabbitListener(queues = "TEST_Q")
    public void consume(String message) {
        TravelRequest data;
        try {
            data = mapper.readValue(message, TravelRequest.class);
            travelServiceHandler.createTravel(data);
        } catch (JsonProcessingException e) {
            log.error("occur JsonProcessingException ,  json model doest not compatible to TravelRequest.class, the model {} ", message);
        } catch (Exception e) {
            throw new QueueException();
        }

    }
}
