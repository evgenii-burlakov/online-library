package ru.otus.librarymicroservice.repositories.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.librarymicroservice.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();

    Optional<Author> findById(long Id);

    Optional<Author> findByName(String name);

    void deleteById(long id);

    Author save(Author author);
}
