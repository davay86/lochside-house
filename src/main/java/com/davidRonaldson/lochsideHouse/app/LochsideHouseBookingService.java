package com.davidRonaldson.lochsideHouse.app;

import com.davidRonaldson.lochsideHouse.config.DatabaseConfig;
import com.davidRonaldson.lochsideHouse.config.RepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DatabaseConfig.class, RepositoryConfig.class})
@ComponentScan("com.davidRonaldson.lochsideHouse")
public class LochsideHouseBookingService {

    public static void main(String[] args) {

        SpringApplication.run(LochsideHouseBookingService.class, args);
    }
}
