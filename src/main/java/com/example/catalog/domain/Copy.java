package com.example.catalog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
@Entity
public class Copy implements AggregateRoot<Copy, Copy.CopyIdentifier> {
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

    @ValueObject
    public record CopyIdentifier(UUID id) implements Identifier {

        public CopyIdentifier {
            Assert.notNull(id, "id must not be null");
        }

        public CopyIdentifier() {
            this(UUID.randomUUID());
        }
    }

    @ValueObject
    public record BarCode(String code) {

        public BarCode {
            Assert.notNull(code, "code must not be null");
        }

    }


}
