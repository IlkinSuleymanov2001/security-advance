package com.example.demo.controller;


import com.example.demo.aspect.security.WritePermission;
import com.example.demo.model.request.TravelRequest;
import com.example.demo.service.impl.TravelServiceHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/travel")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TravelController {

    TravelServiceHandler travelServiceHandler;

    @PostMapping("/create")
    @ResponseStatus(OK)
    @WritePermission
    public Object createOrder(@RequestBody TravelRequest travelRequest) {
        return travelServiceHandler.createTravel(travelRequest);
    }


}
