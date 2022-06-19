package ru.otus.librarymicroservice.service.book;

import ru.otus.librarymicroservice.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book getById(long id);

    void deleteById(long id);

    void update(long id, String name, String authorName, String genreName);

    Book create(String name, String authorName, String genreName);
}
