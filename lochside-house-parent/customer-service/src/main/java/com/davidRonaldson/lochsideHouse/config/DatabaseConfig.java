package com.davidRonaldson.lochsideHouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = (new EmbeddedDatabaseBuilder())
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/schema.sql").build();

        return dataSource;
    }
}
