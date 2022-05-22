package ru.otus.librarymicroservice.service.comment;

public interface CommentService {

    void deleteAllCommentByBookId(Long bookId);

    void deleteAllCommentByGenre(Long genreId);

    void deleteAllCommentByAuthor(Long authorId);
}
