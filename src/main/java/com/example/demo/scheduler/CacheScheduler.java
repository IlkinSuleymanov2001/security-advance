package com.example.demo.scheduler;


import com.example.demo.service.adapter.cache.CacheService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CacheScheduler {

    CacheService cacheService;
    Long EXPIRATION_TIME_MILLIS = 86_700_000l; // 1 gün 5 dəqiqə

    @Scheduled(fixedDelayString = "P1DT5M")
    @SchedulerLock(name = "clearExpiredCache", lockAtLeastFor = "PT1S", lockAtMostFor = "PT3M")
    void clearExpiredCache() {
        cacheService.clearExpiredAuthCache(EXPIRATION_TIME_MILLIS);
        log.info("expired token cache cleaned");
    }
}


