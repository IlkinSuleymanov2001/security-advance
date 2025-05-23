package com.example.demo.client;


import com.example.demo.permission_ms.model.CheckPermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "permissions-ms", url = "http://localhost:8080")
public interface PermissionClient {

    @PostMapping("/v1/permission/check")
    boolean checkPermission(@RequestBody CheckPermissionDto dto);
}
