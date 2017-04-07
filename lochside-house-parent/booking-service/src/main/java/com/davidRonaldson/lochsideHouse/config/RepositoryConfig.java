package com.davidRonaldson.lochsideHouse.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.davidRonaldson.lochsideHouse.domain"})
@EnableJpaRepositories(basePackages = {"com.davidRonaldson.lochsideHouse.repositories"})
@EnableTransactionManagement
public class RepositoryConfig {
}
