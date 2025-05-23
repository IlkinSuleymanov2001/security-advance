package com.example.demo.controller;

import com.example.demo.model.jwt.AuthCacheData;
import com.example.demo.service.adapter.cache.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Controller
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {

    final CacheService cacheService;
    long EXPIRATION_TIME_MILLIS = 2 * 60 * 1000; // 3 minute

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<Object, AuthCacheData> get() {
        return cacheService.getAllAuthCacheDatas();

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete() {
        cacheService.clearExpiredAuthCache(EXPIRATION_TIME_MILLIS);
    }
}
