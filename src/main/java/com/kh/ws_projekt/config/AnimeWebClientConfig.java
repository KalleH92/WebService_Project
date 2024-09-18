package com.kh.ws_projekt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AnimeWebClientConfig {

    @Bean
    public WebClient.Builder animeWebClientBuilder() {

        return WebClient.builder();
    }
}
