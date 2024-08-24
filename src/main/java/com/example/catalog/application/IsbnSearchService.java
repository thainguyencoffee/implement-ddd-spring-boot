package com.example.catalog.application;

import com.example.catalog.domain.Book;

public interface IsbnSearchService {
    BookInformation search(Book.Isbn isbn);
}
