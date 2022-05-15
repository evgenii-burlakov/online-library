package ru.otus.librarymicroservice.repositories.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.librarymicroservice.domain.Book;
import ru.otus.librarymicroservice.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByBook(Book book);

    void deleteById(long id);

    Comment save(Comment comment);

    Optional<Comment> findById(long id);
}
