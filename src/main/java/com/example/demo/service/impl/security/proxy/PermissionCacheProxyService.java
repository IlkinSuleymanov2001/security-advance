package com.example.demo.service.impl.security.proxy;

import com.example.demo.service.adapter.cache.CacheService;
import com.example.demo.service.impl.security.CachePermissionsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.model.constants.CacheDbNameConstants.PERMISSION_CACHE;

@Service
@RequiredArgsConstructor
public class PermissionCacheProxyService {
    private final CacheService cacheService;

    public void saveToCache(Object key, Object value) {
        cacheService.saveToCache(key, value, PERMISSION_CACHE);
    }

    public Optional<CachePermissionsModel> get(Object key) {
        Object o = cacheService.get(key, PERMISSION_CACHE);
        if (o != null)
            return Optional.of((CachePermissionsModel) o);
        return Optional.empty();
    }


}
