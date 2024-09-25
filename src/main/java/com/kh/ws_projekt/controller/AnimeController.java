package com.kh.ws_projekt.controller;


import com.kh.ws_projekt.model.AnimeModel;
import com.kh.ws_projekt.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    private final WebClient animeWebClientConfig;
    @Autowired
    private final AnimeRepository animeRepository;
    public AnimeController(WebClient.Builder webClient, AnimeRepository animeRepository) {
        this.animeWebClientConfig = webClient
                .baseUrl("https://api.jikan.moe/v4/")
                .build();
        this.animeRepository = animeRepository;
    }


    @GetMapping("/{id}")
        public Mono<AnimeModel> fetchAnimeApi(
                @PathVariable("id") String id) {

        return animeWebClientConfig.get()
                .uri(anime -> anime
                        .path("anime/" + id)
                        .build()
                )
                .retrieve()
                .bodyToMono(AnimeModel.class);

    }

    @PostMapping("/{id}")
    public ResponseEntity<AnimeModel> insertAnimeToAnime (
            @PathVariable String id) {
            AnimeModel animeModel = animeWebClientConfig.get()
                .uri(anime -> anime
                        .path("anime/" + id)
                        .build()
                )
                .retrieve()
                .bodyToMono(AnimeModel.class).block();
            animeRepository.save(animeModel);
            return ResponseEntity.ok(animeModel);
    }


    /*
    @PostMapping
    public Mono<String> insertUserToUsers (
            @RequestBody AnimeController animeController
    ) {
        return ResponseEntity.ok(AnimeController.super(fetchAnimeApi(super)));
    }

     */

}
