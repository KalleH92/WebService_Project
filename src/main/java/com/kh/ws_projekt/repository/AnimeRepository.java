package com.kh.ws_projekt.repository;

import com.kh.ws_projekt.model.AnimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeModel, Long> {

}
