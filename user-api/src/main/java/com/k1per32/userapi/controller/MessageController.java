package com.k1per32.userapi.controller;

import com.k1per32.userapi.dto.response.DataResponse;
import com.k1per32.userapi.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Mono<ResponseEntity<DataResponse<Object>>> getNews(
            @RequestParam(name = "q") String keyword
    ) {
        return messageService.getNews(keyword).flatMap(data -> Mono.just(ResponseEntity.status(HttpStatus.OK)
                        .body(new DataResponse<>("data found", true, data))))
                .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new DataResponse<>("data not found", false, null)))));
    }

}
