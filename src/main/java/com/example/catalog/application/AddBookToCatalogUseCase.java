package com.example.catalog.application;

import com.example.catalog.domain.Book;
import com.example.catalog.domain.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class AddBookToCatalogUseCase {
    private final IsbnSearchService bookSearchService;
    private final BookRepository bookRepository;

    public AddBookToCatalogUseCase(IsbnSearchService bookSearchService, BookRepository bookRepository) {
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
    }

    public void execute(Book.Isbn isbn) {
        BookInformation bookInfo = bookSearchService.search(isbn);
        bookRepository.save(new Book(bookInfo.title(), isbn));
    }
}
