package com.example.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Book.BookIdentifier> {
}
