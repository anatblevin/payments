package com.example.payments.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@Data
public class RestTemplateBeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}

