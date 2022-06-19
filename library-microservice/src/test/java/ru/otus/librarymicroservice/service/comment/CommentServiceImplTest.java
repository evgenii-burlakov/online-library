package ru.otus.librarymicroservice.service.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import ru.otus.librarymicroservice.client.CommentMicroserviceClient;
import ru.otus.librarymicroservice.repositories.book.BookRepository;
import ru.otus.librarymicroservice.service.author.AuthorService;
import ru.otus.librarymicroservice.service.book.BookServiceImpl;
import ru.otus.librarymicroservice.service.genre.GenreService;
import ru.otus.librarymicroservice.service.string.StringService;

import java.util.List;

import static ru.otus.librarymicroservice.LibraryUnitTestData.*;

@SpringBootTest
@DisplayName("Сервис для работы с комментариями должен ")
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;

    @MockBean
    private StringService stringService;

    @MockBean
    private CommentMicroserviceClient commentMicroserviceClient;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    @DisplayName("корректно удалять все комментарии по ИД жанра")
    void shouldCorrectDeleteAllCommentByGenre() {
        Mockito.when(bookRepository.findAllByGenreId(1)).thenReturn(List.of(BOOK1));
        commentService.deleteAllCommentByGenre(1L);
        Mockito.verify(commentMicroserviceClient, Mockito.times(1)).deleteAllCommentByBookId(1L);
    }

    @Test
    @DisplayName("корректно удалять все комментарии по ИД автора")
    void shouldCorrectDeleteAllCommentByAuthor() {
        Mockito.when(bookRepository.findAllByAuthorId(1)).thenReturn(List.of(BOOK1, BOOK2));
        commentService.deleteAllCommentByAuthor(1L);
        Mockito.verify(commentMicroserviceClient, Mockito.times(1)).deleteAllCommentByBookId(1L);
        Mockito.verify(commentMicroserviceClient, Mockito.times(1)).deleteAllCommentByBookId(2L);
    }

    @Test
    @DisplayName("корректно удалять все комментарии по ИД книги")
    void shouldCorrectDeleteAllBookComment() {
        commentService.deleteAllCommentByBookId(1L);
        Mockito.verify(commentMicroserviceClient, Mockito.times(1)).deleteAllCommentByBookId(1L);
    }
}