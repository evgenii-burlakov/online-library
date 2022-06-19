package ru.otus.commentmicroservice.service.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.commentmicroservice.client.LibraryClient;
import ru.otus.commentmicroservice.domain.Comment;
import ru.otus.commentmicroservice.exception.ApplicationException;
import ru.otus.commentmicroservice.repositories.CommentRepository;
import ru.otus.commentmicroservice.service.string.StringService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static ru.otus.commentmicroservice.CommentUnitTestData.*;

@SpringBootTest
@DisplayName("Сервис для работы с комментариями должен ")
class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private StringService stringService;

    @MockBean
    private LibraryClient libraryClient;

    @MockBean
    private CommentRepository commentRepository;


    @Test
    @DisplayName("корректно возвращать список комментариев для книги")
    void shouldCorrectGetAllComments() {
        List<Comment> expectedComments = List.of(COMMENT1, COMMENT2);
        Mockito.when(commentRepository.getAllByBookId(1L)).thenReturn(List.of(COMMENT1, COMMENT2));
        List<Comment> actualComments = commentService.getAllByBookId(1L);
        assertThat(actualComments).usingRecursiveComparison().isEqualTo(expectedComments);
    }

    @Test
    @DisplayName("корректно удалять комментарий")
    void shouldCorrectDeleteComment() {
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(COMMENT1));
        commentService.deleteById("USER", 1L);
        Mockito.verify(commentRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("корректно удалять комментарий, оставленный другим пользователем")
    void shouldNotDeleteNotOwnComment() {
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(COMMENT1));
        assertThatThrownBy(() -> commentService.deleteById("ADMIN", 1L)).isInstanceOf(ApplicationException.class);
    }

    @Test
    @DisplayName("корректно удалять все комментарий по ИД книги")
    void shouldCorrectAllDeleteCommentByBookId() {
        commentService.deleteAllByBookId(1L);
        Mockito.verify(commentRepository, Mockito.times(1)).deleteByBookId(1L);
    }

    @Test
    @DisplayName("корректно возвращать комментарий")
    void shouldCorrectGetComment() {
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(COMMENT1));
        Comment actualComment = commentService.getById("USER", 1L);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(COMMENT1);
    }

    @Test
    @DisplayName("не возвращать комментарий, созданный другим пользователем")
    void shouldNotGetNotOwnComment() {
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(COMMENT1));
        assertThatThrownBy(() -> commentService.getById("ADMIN", 1L)).isInstanceOf(ApplicationException.class);
    }


    @Test
    @DisplayName("корректно обновлять комментарий")
    void shouldCorrectUpdateComment() {
        Mockito.when(stringService.verifyNotBlank("Это что за книга?")).thenReturn(true);
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(COMMENT1));

        commentService.update("USER", 1L, "Это что за книга?");

        Mockito.verify(commentRepository, Mockito.times(1)).save(new Comment(1L, "Это что за книга?", 1L, "USER"));
    }

    @Test
    @DisplayName("не обновлять комментарий при не валидном значении")
    void shouldNotUpdateBlankComment() {
        Mockito.when(stringService.verifyNotBlank("    ")).thenReturn(false);

        assertThatThrownBy(() -> commentService.update("USER", 1L, "Это что за книга?")).isInstanceOf(ApplicationException.class);

        Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("не обновлять комментарий, созданный другим пользователем")
    void shouldNotUpdateNotOwnComment() {
        Mockito.when(stringService.verifyNotBlank("Это что за книга?")).thenReturn(true);
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(COMMENT1));

        assertThatThrownBy(() -> commentService.update("ADMIN", 1L, "Это что за книга?")).isInstanceOf(ApplicationException.class);

        Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("не обновлять не существующий комментарий")
    void shouldNotUpdateNotExistComment() {
        Mockito.when(stringService.verifyNotBlank("Это что за книга?")).thenReturn(true);
        Mockito.when(commentRepository.findById(5L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.update("USER", 5L, "Это что за книга?")).isInstanceOf(ApplicationException.class);

        Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("корректно создавать комментарий")
    void shouldCorrectCreateComment() {
        Mockito.when(stringService.verifyNotBlank("Это что за книга?")).thenReturn(true);
        Mockito.when(libraryClient.getBookById(1L)).thenReturn(BOOK1);

        commentService.create("USER", "Это что за книга?", 1L);

        Mockito.verify(commentRepository, Mockito.times(1)).save(new Comment(null, "Это что за книга?", 1L, "USER"));
    }

    @Test
    @DisplayName("не создавать комментарий при не валидном значении")
    void shouldNotCreateBlankComment() {
        Mockito.when(stringService.verifyNotBlank("    ")).thenReturn(false);

        assertThatThrownBy(() -> commentService.create("USER", "    ", 1L)).isInstanceOf(ApplicationException.class);

        Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("не создавать комментарий при отсутствии книги")
    void shouldNotCreateCommentNotExistBook() {
        Mockito.when(stringService.verifyNotBlank("Это что за книга?")).thenReturn(true);
        Mockito.when(libraryClient.getBookById(1L)).thenReturn(null);

        assertThatThrownBy(() -> commentService.create("USER", "Это что за книга?", 1L)).isInstanceOf(ApplicationException.class);

        Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any());
    }
}