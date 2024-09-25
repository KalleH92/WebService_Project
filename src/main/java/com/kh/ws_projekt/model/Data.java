package com.kh.ws_projekt.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Data {

    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
