package ru.otus.commentmicroservice.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.commentmicroservice.service.comment.CommentService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.commentmicroservice.CommentUnitTestData.COMMENT1;
import static ru.otus.commentmicroservice.CommentUnitTestData.COMMENT2;

@WebMvcTest(CommentController.class)
@DisplayName("Контроллер для работы с комментариями должен ")
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("корректно возвращать комментарий по ИД")
    void correctReturnCommentById() throws Exception {
        given(commentService.getById("USER", 1L)).willReturn(COMMENT1);

        mvc.perform(get("/api/comments/1").header("user", "USER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("comment", is("NiCe")))
                .andExpect(jsonPath("username", is("USER")))
                .andExpect(jsonPath("bookId", is(1)));
    }

    @Test
    @DisplayName("корректно возвращать комментарий по ИД книги")
    void correctReturnCommentByBookId() throws Exception {
        given(commentService.getAllByBookId(1L)).willReturn(List.of(COMMENT1, COMMENT2));

        mvc.perform(get("/api/comments?bookId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].comment", is("NiCe")))
                .andExpect(jsonPath("$[0].username", is("USER")))
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("ADMIN")))
                .andExpect(jsonPath("$[1].comment", is("BaD")))
                .andExpect(jsonPath("$[1].bookId", is(1)));
    }

    @Test
    @DisplayName("корректно создавать комментарий")
    void correctCreateComment() throws Exception {
        mvc.perform(post("/api/comments").header("user", "USER")
                        .param("comment", "NiCe")
                        .param("bookId", "1"))
                .andExpect(status().isOk());

        Mockito.verify(commentService, times(1)).create("USER", "NiCe", 1L);
    }


    @Test
    @DisplayName("корректно редактировать комментарий")
    void correctUpdateComment() throws Exception {
        mvc.perform(patch("/api/comments/1").header("user", "USER")
                        .param("id", "1")
                        .param("comment", "Nice"))
                .andExpect(status().isOk());

        Mockito.verify(commentService, times(1)).update("USER", 1L, "Nice");
    }

    @Test
    @DisplayName("корректно удалять комментарий")
    void correctDeleteCommentById() throws Exception {
        mvc.perform(delete("/api/comments/1").header("user", "USER"))
                .andExpect(status().isOk());

        Mockito.verify(commentService, times(1)).deleteById("USER", 1L);
    }

    @Test
    @DisplayName("корректно удалять все комментарий по ИД книги")
    void correctDeleteAllCommentByBookId() throws Exception {
        mvc.perform(delete("/api/comments/deleteAllByBookId/1"))
                .andExpect(status().isOk());

        Mockito.verify(commentService, times(1)).deleteAllByBookId(1L);
    }
}