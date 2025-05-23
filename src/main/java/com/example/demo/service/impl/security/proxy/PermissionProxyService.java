package com.example.demo.service.impl.security.proxy;

import com.example.demo.aspect.performance.LogElapsedTime;
import com.example.demo.model.enums.Permission;
import com.example.demo.service.impl.security.SPermissionService;
import com.example.demo.service.impl.security.TokenService;
import com.example.demo.util.PermissionUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.util.DomainUtil.findDomain;

@Service(value = "permissionProxyService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@LogElapsedTime
@com.example.demo.aspect.logger.Logger
@Slf4j
public class PermissionProxyService {

    PermissionCacheProxyService cacheService;
    SPermissionService permissionService;
    HttpServletRequest request;
    String ms = "demo-ms";


//    public boolean checkPermission(String permission) {
//
//        var role = TokenService.getUserEntityRole();
//        String domain = findDomain(request.getRequestURI()).getDomain();
//        final String cacheKey = ms + "_" + role + "_" + domain;
//
//        var cacheData = cacheService.get(cacheKey, PERMISSION_CACHE);
//
//        if (cacheData != null) {
//            var permissionModel = (CachePermissionsModel) cacheData;
//            return PermissionUtil.hasPermission(permission, permissionModel);
//        }
//
//        InternalPermissionResponse internalPermissionResponse = permissionService.checkPermission(permission, domain, role);
//        cacheService.saveToCache(cacheKey, internalPermissionResponse.getCachePermissionsModel(), PERMISSION_CACHE);
//        return internalPermissionResponse.getHasPermission();
//
//    }
    public boolean checkPermission(Permission permission){
        var role = TokenService.getUserEntityRole();
        String domain = findDomain(request.getRequestURI()).getDomain();
        final String cacheKey = ms + "_" + role + "_" + domain;

        var cacheData = cacheService.get(cacheKey);

        if (cacheData.isPresent()) {
            return PermissionUtil.hasPermission(permission, cacheData.get());
        }

        var response = permissionService.checkPermission(permission, domain, role);
        cacheService.saveToCache(cacheKey, response.getCachePermissionsModel());
        return response.getHasPermission();


    }


}
