package com.example.demo.mapper;

import com.example.demo.dao.entity.OrderEntity;
import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.model.request.order.CreateOrderRequest;
import com.example.demo.model.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(target = "user", source = "user")
    @Mapping(target = "products", source = "products")
    @Mapping(target = "amount", source = "order.amount")
    @Mapping(target = "status", source = "order.status")
    OrderEntity orderDtoConvertToOrderEntity(CreateOrderRequest order, UserEntity user, List<ProductEntity> products);//TODO()

    @Mapping(target = "ownerId", source = "user.id")
    @Mapping(target = "products", source = "products")
    OrderResponse orderEntityConvertToOrderDto(OrderEntity order);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "products", ignore = true)
    OrderEntity toEntity(CreateOrderRequest order);

}
