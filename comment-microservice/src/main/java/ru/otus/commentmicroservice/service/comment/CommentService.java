package ru.otus.commentmicroservice.service.comment;

import ru.otus.commentmicroservice.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByBookId(Long bookId);

    Comment getById(String username, Long id);

    void deleteById(String username, Long id);

    void deleteAllByBookId(Long bookId);

    void update(String username, Long id, String stringComment);

    Comment create(String username, String comment, Long bookId);
}
