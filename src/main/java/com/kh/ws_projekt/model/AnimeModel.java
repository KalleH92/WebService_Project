package com.kh.ws_projekt.model;
import jakarta.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class AnimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
