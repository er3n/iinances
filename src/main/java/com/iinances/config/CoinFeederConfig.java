package com.iinances.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CoinFeederConfig {

    @Bean
    WebClient webClient() {
        return WebClient
                .create("https://www.cryptocompare.com")
                .mutate()
                .build();
    }




}
