package ru.otus.librarymicroservice.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.librarymicroservice.domain.Book;
import ru.otus.librarymicroservice.dto.AuthorDto;
import ru.otus.librarymicroservice.dto.BookDto;
import ru.otus.librarymicroservice.dto.CommentDto;
import ru.otus.librarymicroservice.dto.GenreDto;
import ru.otus.librarymicroservice.service.author.AuthorService;
import ru.otus.librarymicroservice.service.book.BookService;
import ru.otus.librarymicroservice.service.comment.CommentService;
import ru.otus.librarymicroservice.service.genre.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/api/books/{id}")
    public ResponseEntity<Book> updateBook(BookDto book) {
        bookService.update(book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBookById(@PathVariable("id") Long id) {
        return BookDto.toDto(bookService.getById(id));
    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> createBook(BookDto book) {
        bookService.create(book.getName(), book.getAuthor().getName(), book.getGenre().getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
