package com.example.demo.client.decoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.demo.client.decoder.JsonNodeFiledName.ERROR_CODE;
import static com.example.demo.client.decoder.JsonNodeFiledName.MESSAGE;
import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;

@Component
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;  // Jackson ObjectMapper to parse JSON

    public CustomErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        String errorMessage = "Error while decoding response";
        String errorCode = errorMessage;

        final int statusCode = response.status();

        try (var body = response.body().asInputStream()) {

            // Parse the response body JSON into a Map
            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);


            if (jsonNode.has(MESSAGE)) {
                errorMessage = jsonNode.get(MESSAGE).asText();
            }
            if (jsonNode.has(ERROR_CODE)) {
                errorCode = jsonNode.get(ERROR_CODE).asText();
            }

            // Log the error message, error code, and HTTP status code
            log.error("Error occurred. Status Code: {}, Message: {}, Error Code: {}", statusCode, errorMessage, errorCode);


            // You can throw a custom exception based on the error details
            return EXCEPTION.CustomFeignClientThrowable(errorMessage, errorCode, statusCode);

        } catch (Exception e) {
            // Handle JSON parsing errors
            log.error("Error while parsing the response body", e);
            return EXCEPTION.CustomFeignClientThrowable(errorMessage, errorCode, statusCode);
        }
    }


}