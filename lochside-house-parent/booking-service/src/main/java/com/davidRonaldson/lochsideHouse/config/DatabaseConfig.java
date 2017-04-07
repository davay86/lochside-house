package com.davidRonaldson.lochsideHouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:db-config.properties")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = (new EmbeddedDatabaseBuilder())
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/schema.sql")
                .addScript("classpath:db/data.sql").build();

        return dataSource;
    }
}