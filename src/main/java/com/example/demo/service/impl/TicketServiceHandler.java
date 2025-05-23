package com.example.demo.service.impl;


import com.example.demo.aspect.logger.Logger;
import com.example.demo.aspect.performance.LogElapsedTime;
import com.example.demo.dao.entity.Guest;
import com.example.demo.dao.entity.Ticket;
import com.example.demo.dao.entity.Travel;
import com.example.demo.dao.repository.TicketRepository;
import com.example.demo.mapper.GuestMapper;
import com.example.demo.mapper.TicketMapper;
import com.example.demo.mapper.TravelMapper;
import com.example.demo.model.request.CreatedTicketResponse;
import com.example.demo.model.request.OrderTicket;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@LogElapsedTime
@Logger
public class TicketServiceHandler {

    GuestServiceHandler guestService;
    GuestMapper guestMapper;
    TravelServiceHandler travelService;
    TicketMapper ticketMapper;
    TravelMapper travelMapper;
    TicketRepository ticketRepository;


    //@Transactional
    public CreatedTicketResponse orderTicket(OrderTicket orderTicket) {


        Travel travel = travelService.getTravelEntity(orderTicket.getTravelId());

        Guest guest = getGuest(orderTicket);

        Ticket ticket = ticketMapper.createTicket(guest, travel);

        ticketRepository.save(ticket);

        return CreatedTicketResponse
                .builder()
                .guest(guestMapper.toGuestDto(guest))
                .travel(travelMapper.toTravelResponse(travel))
                .ticketId(ticket.getId())
                .build();
    }

    private Guest getGuest(OrderTicket orderTicket) {
        if (orderTicket.getLegalId() != null) {
            Guest guestEntity = guestService.getGuestEntity(orderTicket.getLegalId());
//            Guest guest = guestMapper.updateGuest(guestEntity, orderTicket);
            return updateGuest(guestEntity, orderTicket);
        }
        Guest guestEntity = guestService.createGuestEntity(orderTicket.getGuest());
        return guestEntity;
    }

    private Guest updateGuest(Guest guestEntity, OrderTicket orderTicket) {
        if (orderTicket.getUpdateGuest() != null) {
            if (orderTicket.getUpdateGuest().getPhoneNumber() != null)
                guestEntity.setPhoneNumber(orderTicket.getUpdateGuest().getPhoneNumber());
            if (orderTicket.getUpdateGuest().getEmail() != null)
                guestEntity.setEmail(orderTicket.getUpdateGuest().getEmail());
        }

        return guestEntity;
    }

}
