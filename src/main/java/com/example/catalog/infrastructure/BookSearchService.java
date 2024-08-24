package com.example.catalog.infrastructure;

import com.example.catalog.application.BookInformation;
import com.example.catalog.application.IsbnSearchService;
import com.example.catalog.domain.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
class BookSearchService implements IsbnSearchService {
    private final RestClient restClient;

    public BookSearchService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://openlibrary.org/").build();
    }

    public BookInformation search(Book.Isbn isbn) {
        OpenLibraryIsbnSearchResult result = restClient.get()
                .uri("isbn/{isbn}", isbn.value())
                .retrieve()
                .body(OpenLibraryIsbnSearchResult.class);
        return new BookInformation(result.title());
    }

}
