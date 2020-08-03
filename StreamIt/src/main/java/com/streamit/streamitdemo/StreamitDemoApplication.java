package com.streamit.streamitdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StreamitDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamitDemoApplication.class, args);
    }

}
