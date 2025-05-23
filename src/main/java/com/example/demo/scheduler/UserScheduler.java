package com.example.demo.scheduler;


import com.example.demo.service.impl.inter.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserScheduler {

    UserService userService;

    @Scheduled(fixedDelayString = "PT1M")
    @SchedulerLock(name = "deleteAllDeActiveUsers", lockAtLeastFor = "PT1S", lockAtMostFor = "PT3M")
    public void deleteAllDraftOrders() {
        userService.deleteAllDeActiveUsers();
    }


}
