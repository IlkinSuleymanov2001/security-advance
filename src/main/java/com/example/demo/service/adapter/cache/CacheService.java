package com.example.demo.service.adapter.cache;


import com.example.demo.model.jwt.AuthCacheData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import static com.example.demo.model.constants.CacheDbNameConstants.TOKEN_CACHE;


@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService {

    private final CacheManager cacheManager;

    public void saveToCache(Object key, Object value, String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public Object get(Object key, String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            var item = cache.get(key);
            return item != null ? item.get() : null;
        }
        return null;
    }

    public void evictAllData(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }

    public void removeDataInCache(Object key, String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.evict(key);

    }

    public void clearExpiredAuthCache(Long expireTime) {
        var items = getAllAuthCacheDatas();
        log.info("all cache data: {}", items);

        for (Map.Entry<Object, AuthCacheData> entry : items.entrySet()) {
            long writtenTime = entry.getValue().getWriteTime();
            if (System.currentTimeMillis() - writtenTime > expireTime) {
                evict(TOKEN_CACHE, entry.getKey());
            }
        }
    }

    public void evict(String cacheName, Object key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }


    public Map<Object, AuthCacheData> getAllAuthCacheDatas() {
        Cache cache = cacheManager.getCache(TOKEN_CACHE);
        if (cache != null && cache.getNativeCache() instanceof ConcurrentMap) {
            var map = (ConcurrentMap<Object, AuthCacheData>) cache.getNativeCache();
            return map;
        }
        return Collections.emptyMap();
    }
}
