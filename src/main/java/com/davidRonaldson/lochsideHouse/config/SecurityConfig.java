package com.davidRonaldson.lochsideHouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, 'true' as enabled from users where username =?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username =?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/customerBookings").hasRole("ADMIN")
                .antMatchers("/roomBookings").hasRole("ADMIN")
                .antMatchers("/createBooking").hasRole("ADMIN")
                .antMatchers("/availability").hasRole("ADMIN");
    }
}
