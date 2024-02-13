package com.k1per32.worker.service.impl;

import com.k1per32.worker.service.WebClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientServiceImpl implements WebClientService {


    @Value("${newsapi.apiKey}")
    private String apiKey;

    @Value("${newsapi.language}")
    private String language;

    @Value("${newsapi.pageSize}")
    private String pageSize;

    private final WebClient webClient;

    public WebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ResponseEntity<String>> sendRequest(String keyword) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apiKey", apiKey)
                        .queryParam("language", language)
                        .queryParam("pageSize", pageSize)
                        .queryParam("q", keyword)
                        .build())
                .retrieve()
                .toEntity(String.class);
    }
}
