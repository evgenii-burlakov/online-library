package ru.otus.librarymicroservice.service.genre;

import ru.otus.librarymicroservice.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getById(long id);

    Genre getByName(String name);

    void deleteById(long id);

    void update(long id, String name);

    Genre create(String name);
}
