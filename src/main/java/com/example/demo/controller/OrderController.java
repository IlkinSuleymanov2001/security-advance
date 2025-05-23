package com.example.demo.controller;


import com.example.demo.aspect.security.ReadPermission;
import com.example.demo.aspect.security.WritePermission;
import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.request.order.CreateOrderRequest;
import com.example.demo.model.response.OrderResponse;
import com.example.demo.service.impl.inter.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/order")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    OrderService orderService;

    @GetMapping("/all")
    @ResponseStatus(OK)
   @ReadPermission
    public ResponseModel<List<OrderResponse>> getOrders() {
        List<OrderResponse> orders = orderService.getOrders();
        return ResponseModel.<List<OrderResponse>>builder()
                .data(orders)
                .build();
    }

    @PostMapping("/create")
    @ResponseStatus(OK)
    @WritePermission
    public OrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(createOrderRequest);
    }


}
