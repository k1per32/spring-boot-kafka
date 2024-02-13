package com.k1per32.worker.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.k1per32.worker.repository.NewsRepository;
import com.k1per32.worker.service.WebClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class KafkaListeners {

    private final WebClientService webClientService;
    private final NewsRepository newsRepository;

    public KafkaListeners(WebClientService webClientService, NewsRepository newsRepository) {
        this.webClientService = webClientService;
        this.newsRepository = newsRepository;
    }

    @KafkaListener(topics = "news", groupId = "message-group")
    void listener(String keyword) {
        System.out.printf("Listener received: %s%n", keyword);

        Mono<ResponseEntity<String>> responseEntity = webClientService.sendRequest(keyword);
        responseEntity.subscribe(response -> {
            HttpStatus status = (HttpStatus) response.getStatusCode();
            if (status.equals(HttpStatus.OK)) {
                try {
                    newsRepository.saveNews(keyword, response.getBody()).subscribe(isSaved ->{
                        if(isSaved){
                            System.out.println("Data successfully saved in cache");
                        }else
                            System.out.println("Data save failed");
                    });
                }catch (JsonProcessingException e){
                    throw new RuntimeException(e);
                }
            }
            System.out.println(status.value());
        });
    }
}
