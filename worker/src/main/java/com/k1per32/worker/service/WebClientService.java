package com.k1per32.worker.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface WebClientService {
    Mono<ResponseEntity<String>> sendRequest(String keyword);
}
