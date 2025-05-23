package com.example.demo.service.impl.inter;

import com.example.demo.model.request.order.CreateOrderRequest;
import com.example.demo.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest createOrderRequest);

    List<OrderResponse> getOrders();

    void deleteAllDraftOrder();

}
