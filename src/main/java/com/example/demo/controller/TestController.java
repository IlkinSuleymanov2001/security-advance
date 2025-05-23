package com.example.demo.controller;

import com.example.demo.aspect.security.DeletePermission;
import com.example.demo.aspect.security.WritePermission;
import com.example.demo.service.impl.TicketServiceHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/v1/test")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TestController {

    TicketServiceHandler ticketService;


//    @GetMapping()
//    @ResponseStatus(ACCEPTED)
//    @PreAuthorize("@permissionProxyService.checkPermission('TEST_CREATE')")
//    public Object test() {
//        return new Car("F90", "MBW");
//
//    }
//    @PatchMapping
//    @ResponseStatus(ACCEPTED)
//    @CheckDomainPermission(WRITE)
//    public Object test2() {
//        return new Car("F90", "MBW");
//    }

    @PutMapping
    @ResponseStatus(ACCEPTED)
    @WritePermission
    public Object test22() {
        return new Car("F90", "MBW");

    }

    @DeleteMapping
    @ResponseStatus(ACCEPTED)
    @DeletePermission
    public Object test2222() {
        return new Car("F90", "MBW");

    }

    @Data
    @AllArgsConstructor
    static class Car {
        String name;
        String mark;

    }

}
