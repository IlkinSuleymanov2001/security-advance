package com.example.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.model.constants.CacheDbNameConstants.PERMISSION_CACHE;
import static com.example.demo.model.constants.CacheDbNameConstants.TOKEN_CACHE;

@Configuration
@EnableCaching
public class InMemoryCacheConfig {


    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(TOKEN_CACHE, PERMISSION_CACHE);
    }

}
