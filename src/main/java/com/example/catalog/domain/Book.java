package com.example.catalog.domain;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.Getter;
import org.apache.commons.validator.routines.ISBNValidator;
import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
@Entity
public class Book implements AggregateRoot<Book, Book.BookIdentifier> {
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



    @ValueObject
    public record BookIdentifier(UUID id) implements Identifier {
        public BookIdentifier {
            Assert.notNull(id, "id must not be null");
        }

        public BookIdentifier() {
            this(UUID.randomUUID());
        }

    }

    @ValueObject
    public record Isbn(String value) {
        private static final ISBNValidator VALIDATOR = new ISBNValidator();

        public Isbn {
            if (!VALIDATOR.isValid(value)) {
                throw new IllegalArgumentException("invalid isbn : " + value);
            }
        }
    }

}
