package com.example.catalog.application;

import org.springframework.util.Assert;

public record BookInformation(String title) {

    public BookInformation {
        Assert.notNull(title, "Title must not be null");
    }

}
