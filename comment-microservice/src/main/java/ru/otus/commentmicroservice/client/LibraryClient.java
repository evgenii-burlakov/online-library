package ru.otus.commentmicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.commentmicroservice.domain.Book;

@FeignClient(name = "library-microservice")
public interface LibraryClient {

    @GetMapping("/api/books/{id}")
    Book getBookById(@PathVariable("id") Long id);
}
