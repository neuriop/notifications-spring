package com.example.usernotifs.config;

import com.example.usernotifs.errors.DatabaseResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DatabaseResponseErrorHandler());
        return restTemplate;
    }
}