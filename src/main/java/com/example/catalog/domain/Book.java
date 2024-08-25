package com.example.catalog.domain;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.Getter;
import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
public class Book {
    @EmbeddedId
    private BookIdentifier id;
    private String title;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "isbn"))
    private Isbn isbn;

    protected Book() {
    }

    public Book(String title, Isbn isbn) {
        Assert.notNull(title, "title must not be null");
        Assert.notNull(isbn, "isbn must not be null");
        this.id = new BookIdentifier();
        this.title = title;
        this.isbn = isbn;
    }

    public record BookIdentifier(UUID id) implements Serializable {
        public BookIdentifier {
            Assert.notNull(id, "id must not be null");
        }

        public BookIdentifier() {
            this(UUID.randomUUID());
        }

    }

    public record Isbn(String value) {
        private static final ISBNValidator VALIDATOR = new ISBNValidator();

        public Isbn {
            if (!VALIDATOR.isValid(value)) {
                throw new IllegalArgumentException("invalid isbn : " + value);
            }
        }
    }

}
