package com.example.demo.service.impl;

import com.example.demo.dao.entity.OrderEntity;
import com.example.demo.dao.repository.OrderRepository;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.request.order.CreateOrderRequest;
import com.example.demo.model.response.OrderResponse;
import com.example.demo.service.impl.inter.OrderService;
import com.example.demo.service.impl.inter.ProductService;
import com.example.demo.service.impl.inter.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.model.enums.Status.DELETED;
import static com.example.demo.model.enums.Status.DRAFT;
import static lombok.AccessLevel.PRIVATE;


@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {


    OrderMapper orderMapper;
    OrderRepository orderRepository;
    UserService userService;
    ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {


        var user = userService.findUser(createOrderRequest.getUser());
        var product = productService.getProductBy(createOrderRequest.getProducts().get(0));// TODO("ele bele ")

        OrderEntity orderEntity = orderMapper.toEntity(createOrderRequest);
        orderRepository.save(orderEntity);


        orderEntity.setUser(user);
        orderEntity.addProduct(product);
        product.setStock(product.getStock() - 1);

        OrderEntity savedEntity = orderRepository.save(orderEntity);


        OrderResponse orderResponse = orderMapper.orderEntityConvertToOrderDto(savedEntity);
        return orderResponse;

    }

    @Override
    public List<OrderResponse> getOrders() {

        return orderRepository.findAll().
                stream()
                .map(orderMapper::orderEntityConvertToOrderDto)
                .toList();
    }

    @Override
    public void deleteAllDraftOrder() {
        List<OrderEntity> orders = orderRepository.findByStatus(DRAFT);
        orders.forEach(e -> e.setStatus(DELETED));
        orderRepository.saveAll(orders);
    }


}
