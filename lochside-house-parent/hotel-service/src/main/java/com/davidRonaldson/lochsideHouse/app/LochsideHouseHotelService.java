package com.davidRonaldson.lochsideHouse.app;

import com.davidRonaldson.lochsideHouse.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class LochsideHouseHotelService {

    public static void main(String[] args) {
        SpringApplication.run(LochsideHouseHotelService.class, args);
    }
}
