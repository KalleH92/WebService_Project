package com.kh.ws_projekt.controller;


import com.kh.ws_projekt.model.AnimeModel;
import com.kh.ws_projekt.model.Data;
import com.kh.ws_projekt.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

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
            return ResponseEntity.status(201).body(animeModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeModel> animeTitleUpdate (
            @PathVariable Long id, @RequestBody String animeTitel) {
        Optional<AnimeModel> animeDB = animeRepository.findById(id);
        AnimeModel anime = animeDB.get();
        anime.setData(new Data(animeTitel));
        animeRepository.save(anime);
        return ResponseEntity.status(200).body(anime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> animeTitleDelete (
            @PathVariable Long id) {
                animeRepository.deleteById(id);
                return ResponseEntity.status(200).body("removed");
    }


}
