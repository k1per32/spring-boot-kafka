package com.k1per32.userapi.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<Void> publishToMessageBroker(String date);
    Mono<Object> getNews(String date);
}
