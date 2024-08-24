package com.example.lending.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.jmolecules.event.types.DomainEvent;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@Entity
public class Loan extends AbstractAggregateRoot<Loan>
        implements AggregateRoot<Loan, Loan.LoanIdentifier> {
    @EmbeddedId
    private LoanIdentifier id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "copy_id"))
    private CopyId copyId;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;
    private Instant createdAt;
    private Instant expectedReturnDate;
    private Instant returnedAt;
    @Version
    private int version;

    protected Loan() {
    }

    public Loan(CopyId copyId, UserId userId, LoanRepository loanRepository) {
        Assert.notNull(copyId, "Copy ID must not be null");
        Assert.notNull(userId, "User ID must not be null");
        Assert.isTrue(loanRepository.isAvailable(copyId), "Copy is not available");
        this.id = new LoanIdentifier();
        this.copyId = copyId;
        this.userId = userId;
        this.createdAt = Instant.now();
        this.expectedReturnDate = this.createdAt.plus(30, ChronoUnit.DAYS);
        this.registerEvent(new LoanCreated(copyId));
    }

    public void returned() {
        this.returnedAt = Instant.now();
        if (this.returnedAt.isAfter(this.expectedReturnDate)) {
            // calculate fee
        }
        this.registerEvent(new LoanClosed(this.copyId));
    }

    @ValueObject
    public record LoanIdentifier(UUID id) implements Identifier {

        public LoanIdentifier {
            Assert.notNull(id, "id must not be null");
        }

        public LoanIdentifier() {
            this(UUID.randomUUID());
        }
    }

    @ValueObject
    public record LoanCreated(CopyId copyId) implements DomainEvent {}

    @ValueObject
    public record LoanClosed(CopyId copyId) implements DomainEvent {}

}
