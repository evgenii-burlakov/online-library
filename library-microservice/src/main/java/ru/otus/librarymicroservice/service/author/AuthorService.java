package ru.otus.librarymicroservice.service.author;

import ru.otus.librarymicroservice.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author getById(long id);

    Author getByName(String author);

    void deleteById(long id);

    void update(long id, String name);

    Author create(String name);
}
