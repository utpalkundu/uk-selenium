package com.auto.test.ukselenium.taf.config;

import io.restassured.builder.RequestSpecBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestSpecBuilderConfig {

    @Bean
    public RequestSpecBuilder getBuilder(){
        return new RequestSpecBuilder();
    }
}
