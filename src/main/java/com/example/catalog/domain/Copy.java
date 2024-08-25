package com.example.catalog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
public class Copy {
    @EmbeddedId
    private CopyIdentifier id;
    @Embedded
    private BarCode barCode;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "book_id"))
    private Book.BookIdentifier bookId;
    private boolean available;

    protected Copy() {
    }

    public Copy(Book.BookIdentifier bookId, BarCode barCode) {
        Assert.notNull(bookId, "BookIdentifier must not be null");
        Assert.notNull(barCode, "BarCode must not be null");
        this.id = new CopyIdentifier();
        this.bookId = bookId;
        this.barCode = barCode;
        this.available = true;
    }

    public void makeAvailable() {
        this.available = true;
    }

    public void makeUnavailable() {
        this.available = false;
    }

    public record CopyIdentifier(UUID id) implements Serializable {

        public CopyIdentifier {
            Assert.notNull(id, "id must not be null");
        }

        public CopyIdentifier() {
            this(UUID.randomUUID());
        }
    }

    public record BarCode(String code) {

        public BarCode {
            Assert.notNull(code, "code must not be null");
        }

    }


}
