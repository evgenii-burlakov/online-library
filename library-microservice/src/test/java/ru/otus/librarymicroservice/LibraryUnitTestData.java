package ru.otus.librarymicroservice;

import ru.otus.librarymicroservice.domain.Author;
import ru.otus.librarymicroservice.domain.Book;
import ru.otus.librarymicroservice.domain.Genre;

public final class LibraryUnitTestData {
    public static final Author AUTHOR1 = new Author(1L, "PUSHKIN");
    public static final Author AUTHOR2 = new Author(2L, "MONTGOMERY");

    public static final Genre GENRE1 = new Genre(1L, "POEM");
    public static final Genre GENRE2 = new Genre(2L, "NOVEL");

    public static final Book BOOK1 = new Book(1L, "EVGENII ONEGIN", AUTHOR1, GENRE1);
    public static final Book BOOK2 = new Book(2L, "ANNE OF GREEN GABLES", AUTHOR2, GENRE2);
    public static final Book BOOK3 = new Book(3L, "ANNE OF GREEN GABLES POEM EDITION", AUTHOR2, GENRE1);
}
