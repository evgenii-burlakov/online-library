package ru.otus.commentmicroservice;

import ru.otus.commentmicroservice.domain.Author;
import ru.otus.commentmicroservice.domain.Book;
import ru.otus.commentmicroservice.domain.Comment;
import ru.otus.commentmicroservice.domain.Genre;

public final class CommentUnitTestData {
    public static final Comment COMMENT1 = new Comment(1L, "NiCe", 1L, "USER");
    public static final Comment COMMENT2 = new Comment(2L, "BaD", 1L, "ADMIN");

    public static final Author AUTHOR1 = new Author(1L, "PUSHKIN");
    public static final Genre GENRE1 = new Genre(1L, "POEM");
    public static final Book BOOK1 = new Book(1L, "EVGENII ONEGIN", AUTHOR1, GENRE1);
}
