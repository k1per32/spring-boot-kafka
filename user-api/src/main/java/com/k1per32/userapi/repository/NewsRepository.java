package com.k1per32.userapi.repository;

import reactor.core.publisher.Mono;

public interface NewsRepository {
    Mono<Object> getNews(String data);
}
