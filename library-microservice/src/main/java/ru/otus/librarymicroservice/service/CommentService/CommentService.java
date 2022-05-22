package ru.otus.librarymicroservice.service.CommentService;

public interface CommentService {

    void deleteAllCommentByBookId(Long bookId);

    void deleteAllCommentByGenre(Long genreId);

    void deleteAllCommentByAuthor(Long authorId);
}
