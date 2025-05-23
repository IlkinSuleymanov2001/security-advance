package com.example.demo.service.impl.security;

import com.example.demo.dao.entity.securtiy.PermissionEntity;
import com.example.demo.dao.repository.security.SPermissionRepository;
import com.example.demo.model.enums.Permission;
import com.example.demo.model.response.InternalPermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.util.PermissionUtil.hasPermission;

@Service
@RequiredArgsConstructor
public class SPermissionService {

    private final SPermissionRepository permissionRepository;


//    public InternalPermissionResponse checkPermission(String permission, String domain, String role) {
//
//
//        PermissionEntity permissionEntity = permissionRepository.
//                findByRoleNameAndDomainName(role, domain);
//
//        CachePermissionsModel cachePermissionsModel = CachePermissionsModel.of(permissionEntity);
//        boolean hasPermission = hasPermission(permission, permissionEntity);
//
//        return InternalPermissionResponse
//                .builder()
//                .hasPermission(hasPermission)
//                .cachePermissionsModel(cachePermissionsModel)
//                .build();
//
//    }
    public InternalPermissionResponse checkPermission(Permission permission, String domain, String role) {


        PermissionEntity permissionEntity = permissionRepository.
                findByRoleNameAndDomainName(role, domain);

        CachePermissionsModel cachePermissionsModel = CachePermissionsModel.of(permissionEntity);
        boolean hasPermission = hasPermission(permission, permissionEntity);

        return InternalPermissionResponse
                .builder()
                .hasPermission(hasPermission)
                .cachePermissionsModel(cachePermissionsModel)
                .build();

    }

    public List<CachePermissionsModel> getPermissions() {
        return null;
    }


}