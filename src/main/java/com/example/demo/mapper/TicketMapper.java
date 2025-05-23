package com.example.demo.mapper;

import com.example.demo.dao.entity.Guest;
import com.example.demo.dao.entity.Ticket;
import com.example.demo.dao.entity.Travel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TicketMapper {


    // Create a new Ticket using Guest and Travel objects
    @Mapping(target = "guest", source = "guest")
    @Mapping(target = "travel", source = "travel")
    @Mapping(target = "id", ignore = true)
    Ticket createTicket(Guest guest, Travel travel);


}
