package com.example.catalog.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BookInitializer {

    @Bean
    public CommandLineRunner init(BookRepository bookRepository, CopyRepository copyRepository) {
        return args -> {
            bookRepository.deleteAll();
            copyRepository.deleteAll();

            bookRepository.save(new Book("Clean code", new Book.Isbn("9780132350884")));
            bookRepository.save(new Book("Clean architecture", new Book.Isbn("9780134494166")));
            bookRepository.save(new Book("Domain-Driven Design", new Book.Isbn("9780321125217")));
            bookRepository.save(new Book("The Pragmatic Programmer", new Book.Isbn("9780201616224")));
            bookRepository.save(new Book("Refactoring", new Book.Isbn("9780201485677")));
            bookRepository.save(new Book("Design Patterns", new Book.Isbn("9780201633610")));

            List<Book> books = bookRepository.findAll();
            books.forEach(book -> {
                copyRepository.save(new Copy(book.getId(), new Copy.BarCode(UUID.randomUUID().toString())));
                copyRepository.save(new Copy(book.getId(), new Copy.BarCode(UUID.randomUUID().toString())));
                copyRepository.save(new Copy(book.getId(), new Copy.BarCode(UUID.randomUUID().toString())));
            });
        };
    }

}
