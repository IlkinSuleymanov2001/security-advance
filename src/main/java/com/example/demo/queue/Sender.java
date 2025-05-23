package com.example.demo.queue;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;

//@Component
@RequiredArgsConstructor
public class Sender {

    private final ObjectMapper mapper;
    private final AmqpTemplate amqpTemplate;


    @SneakyThrows
    public void sendMessageToQ(String queueName, Object dto) {
        amqpTemplate.convertAndSend(queueName, mapper.writeValueAsString(dto));
    }
}
