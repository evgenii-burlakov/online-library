package ru.otus.commentmicroservice.service.comment;

import ru.otus.commentmicroservice.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByBookId(Long bookId);

    Comment getById(String username, Long id);

    void deleteById(String username, long id);

    void deleteAllByBookId(long bookId);

    void update(String username, long id, String stringComment);

    Comment create(String username, String comment, Long bookId);
}
