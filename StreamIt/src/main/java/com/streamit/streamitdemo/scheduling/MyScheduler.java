package com.streamit.streamitdemo.scheduling;

import com.streamit.streamitdemo.service.ShowService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyScheduler {

    private final ShowService showService;

    public MyScheduler(ShowService showService) {
        this.showService = showService;
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteAllPastShows() {
        this.showService.deleteAllPastShows();

    }

}