package com.example.catalog.application;

import com.example.catalog.domain.*;
import org.springframework.stereotype.Service;

@Service
public class RegisterBookCopyUseCase {
    private final CopyRepository copyRepository;

    public RegisterBookCopyUseCase(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    public void execute(Book.BookIdentifier bookId, Copy.BarCode barCode) {
        copyRepository.save(new Copy(bookId, barCode));
    }
}
