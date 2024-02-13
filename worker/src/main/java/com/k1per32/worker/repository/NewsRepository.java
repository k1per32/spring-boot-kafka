package com.k1per32.worker.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


public interface NewsRepository {
    Mono<Boolean> saveNews(String date, Object newsObject) throws JsonProcessingException;
}
