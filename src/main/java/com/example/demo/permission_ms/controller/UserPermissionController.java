package com.example.demo.permission_ms.controller;


import com.example.demo.permission_ms.model.CheckPermissionDto;
import com.example.demo.permission_ms.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/permission")
public class UserPermissionController {

    private final PermissionService permissionService;

    @PostMapping("/check")
    public boolean checkPermission(@RequestBody CheckPermissionDto permissionDto) {
        return permissionService.checkPermission(permissionDto);
    }


    @PostMapping("/add")
    @ResponseStatus(ACCEPTED)
//    @Async
    public void addPermission(@RequestBody CheckPermissionDto permissionDto) {
        permissionService.addPermission(permissionDto);
    }

}
