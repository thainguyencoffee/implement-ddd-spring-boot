package com.example.lending.application;

import com.example.lending.domain.CopyId;
import com.example.lending.domain.Loan;
import com.example.lending.domain.LoanRepository;
import com.example.lending.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class RentBookUseCase {
    private final LoanRepository loanRepository;

    public RentBookUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(CopyId copyId, UserId userId) {
        // TODO: ensure rented copy is not rented again
        loanRepository.save(new Loan(copyId, userId, loanRepository));
    }

}
