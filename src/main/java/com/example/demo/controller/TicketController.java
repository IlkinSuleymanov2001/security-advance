package com.example.demo.controller;


import com.example.demo.aspect.security.WritePermission;
import com.example.demo.model.request.OrderTicket;
import com.example.demo.service.impl.TicketServiceHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/ticket")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    TicketServiceHandler ticketService;


    @PostMapping("/order")
    @ResponseStatus(CREATED)
    @WritePermission
    public Object orderTicket(@RequestBody OrderTicket orderTicket) {

        return ticketService.orderTicket(orderTicket);
    }


}
