package com.example.demo.model.response;


import com.example.demo.service.impl.security.CachePermissionsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InternalPermissionResponse {
    private Boolean hasPermission;
    private CachePermissionsModel cachePermissionsModel;

}
