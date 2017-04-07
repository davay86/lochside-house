package com.davidRonaldson.lochsideHouse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Import({DatabaseConfig.class, RepoConfig.class, SwaggerConfig.class, SecurityConfig.class})
@ComponentScan("com.davidRonaldson.lochsideHouse")
@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {
}
