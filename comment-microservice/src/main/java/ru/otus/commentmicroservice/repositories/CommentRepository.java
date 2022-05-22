package ru.otus.commentmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.commentmicroservice.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByBookId(Long bookId);

    void deleteById(Long id);

    void deleteByBookId(Long bookId);

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
