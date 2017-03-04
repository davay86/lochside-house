package com.davidRonaldson.lochsideHouse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Import({DatabaseConfig.class, RepositoryConfig.class, SwaggerConfig.class})
@ComponentScan("com.davidRonaldson.lochsideHouse")
@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {
}
