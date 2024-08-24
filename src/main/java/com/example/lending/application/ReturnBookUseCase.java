package com.example.lending.application;

import com.example.lending.domain.Loan;
import com.example.lending.domain.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class ReturnBookUseCase {
    private final LoanRepository loanRepository;

    public ReturnBookUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(Loan.LoanIdentifier loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.returned();
    }

}
