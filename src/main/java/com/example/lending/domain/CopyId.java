package com.example.lending.domain;

import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

import java.util.UUID;

@ValueObject
public record CopyId(UUID id) {

    public CopyId {
        Assert.notNull(id, "id must not be null");
    }

}
