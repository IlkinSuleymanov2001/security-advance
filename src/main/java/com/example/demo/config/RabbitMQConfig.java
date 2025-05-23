//package com.example.demo.config;
//
//
//import com.rabbitmq.client.AMQP;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    private final  String testQ;
//    private final  String testDLQ;
//    private final  String testQExchange;
//    private final  String testDLQExchange;
//    private final  String testQKey;
//    private final  String testDLQKey;
//
//    public RabbitMQConfig(@Value("${rabbitmq.queue.test}") String testQ,
//                          @Value("${rabbitmq.queue.test-dlq}") String testDLQ) {
//        this.testQ = testQ;
//        this.testDLQ = testDLQ;
//        this.testQExchange = testQ + "_Exchange";
//        this.testDLQExchange = testDLQ + "_Exchange";
//        this.testQKey = testQ + "_Key";
//        this.testDLQKey = testDLQ + "_key";
//    }
//
//    @Bean
//    Queue testQ(){
//      return  QueueBuilder.durable(testQ).
//               withArgument("X-dead-letter-exchange",testQExchange)
//               .withArgument("X-dead-letter-routing-key",testQKey)
//               .build();
//
//    }
//    @Bean
//    Binding  testQBinding(){
//        return BindingBuilder.bind(testQ()).
//                to(testQExchange()).with(testQKey);
//    }
//
//    @Bean
//    DirectExchange testQExchange() {
//        return ExchangeBuilder.directExchange(testQExchange).
//                build();
//    }
//
//
//
//    @Bean
//    Queue testQLQ(){
//      return  QueueBuilder.durable(testDLQ).
//               withArgument("X-dead-letter-exchange",testDLQExchange)
//               .withArgument("X-dead-letter-routing-key",testDLQKey)
//               .build();
//
//    }
//    @Bean
//    Binding  testDLQBinding(){
//        return BindingBuilder.bind(testQ()).
//                to(testQExchange()).with(testDLQKey);
//    }
//
//    @Bean
//    DirectExchange testDLQExchange() {
//        return  ExchangeBuilder.directExchange(testDLQExchange).
//                build();
////        return new DirectExchange(testDLQExchange);
//    }
//
////    // Define the ConnectionFactory bean
////    @Bean
////    public ConnectionFactory connectionFactory() {
////        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost"); // RabbitMQ host
////        connectionFactory.setUsername("guest");
////        connectionFactory.setPassword("guest");
////        return connectionFactory;
////    }
////
////    // Create the AmqpTemplate bean (RabbitTemplate is the default implementation)
////    @Bean
////    public AmqpTemplate amqpTemplate() {
////        return new RabbitTemplate(connectionFactory());
////    }
//
//
//
//}
