package com.example.demo.client;


import com.example.demo.client.decoder.CustomErrorDecoder;
import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.client.CardOwnerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-card-owner", url = "http://localhost:8080",
        configuration = CustomErrorDecoder.class)
public interface CardClient {

    String MERCHANT_ID_HEADER = "X-Merchant";
    String API_KEY_HEADER = "X-API-Key";
    String RESPONSE_TYPE_HEADER = "X-TYPE";


    @GetMapping("internal/v1/card/owner/{id}")
    ResponseModel<CardOwnerResponseDto> getCardOwner(@PathVariable Long id);

//    @GetMapping(value = "/payment/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    YigimPaymentInitializationResponse initialize(
//            @RequestHeader(name = MERCHANT_ID_HEADER) String merchantId,
//            @RequestHeader(name = API_KEY_HEADER) String apiKey,
//            @RequestHeader(name = RESPONSE_   TYPE_HEADER) String responseType,
//            @RequestParam String reference,
//            @RequestParam String amount,
//            @RequestParam int currency,
//            @RequestParam String biller,
//            @RequestParam String template,
//            @RequestParam String language,
//            @RequestParam String callback
//    );
//
//
//    @PostMapping(value = "/api", consumes = MediaType.APPLICATION_XML_VALUE, produces =MediaType.APPLICATION_JSON_VALUE )
//    SmsResponseDto sendSms(@RequestBody SmsRequestDto smsRequestDto);

}
