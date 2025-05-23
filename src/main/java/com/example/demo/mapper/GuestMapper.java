package com.example.demo.mapper;

import com.example.demo.dao.entity.Guest;
import com.example.demo.model.request.GuestDto;
import com.example.demo.model.request.GuestResponseDto;
import com.example.demo.model.request.OrderTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface GuestMapper {

    Guest toGuest(GuestDto guestDto);


    GuestDto toGuestDto(Guest guest);

    @Mapping(target = "phoneNumber", source = "orderTicket.updateGuest.phoneNumber")
    @Mapping(target = "email", source = "orderTicket.updateGuest.email")
    @Mapping(target = "legalId", ignore = true)
    Guest updateGuest(Guest guest, OrderTicket orderTicket);

    GuestResponseDto toGuestResponseDto(Guest guest);


}
