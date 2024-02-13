package com.k1per32.worker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Value("${newsapi.uri}")
    private String apiUri;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiUri)
                .build();
    }
}
