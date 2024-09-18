package com.kh.ws_projekt.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    private final WebClient animeWebClientConfig;

    public AnimeController(WebClient.Builder webClient) {
        this.animeWebClientConfig = webClient
                .baseUrl("https://api.jikan.moe/v4/")
                .build();
    }

    @GetMapping("/{id}")
        public Mono<String> fetchAnimeApi(@PathVariable("id") String id) {

        return animeWebClientConfig.get()
                .uri(anime -> anime
                        .path("anime/" + id)
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class);

    }

    @PostMapping
    public Mono<String> insertUserToUsers (
            @RequestBody AnimeController animeController
    ) {
        return ResponseEntity.ok(AnimeController.super(fetchAnimeApi(super)));
    }

}
