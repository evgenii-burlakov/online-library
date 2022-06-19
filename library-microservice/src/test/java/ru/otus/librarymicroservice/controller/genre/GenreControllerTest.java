package ru.otus.librarymicroservice.controller.genre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.librarymicroservice.domain.Genre;
import ru.otus.librarymicroservice.service.genre.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.librarymicroservice.LibraryUnitTestData.*;

@WebMvcTest(GenreController.class)
@DisplayName("Контроллер для работы с жанрами должен ")
class GenreControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;

    @Test
    @DisplayName("корректно возвращать все жанры")
    void correctGetAllGenres() throws Exception {
        given(genreService.getAll()).willReturn(List.of(new Genre(1L, "POEM"), new Genre(2L, "NOVEL")));

        mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("POEM")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("NOVEL")));
    }

    @Test
    @DisplayName("корректно удалять жанр")
    void correctDeleteGenreById() throws Exception {
        mvc.perform(delete("/api/genres/1"))
                .andExpect(status().isOk());

        Mockito.verify(genreService, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("корректно редактировать жанр")
    void correctUpdateGenre() throws Exception {
        mvc.perform(patch("/api/genres/1")
                        .param("id", "1")
                        .param("name", "Poem"))
                .andExpect(status().isOk());

        Mockito.verify(genreService, times(1)).update(1, "Poem");
    }

    @Test
    @DisplayName("корректно возвращать жанр по ИД")
    void correctReturnGenreById() throws Exception {
        given(genreService.getById(1)).willReturn(new Genre(1L, "POEM"));

        mvc.perform(get("/api/genres/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("POEM")));
    }

    @Test
    @DisplayName("корректно создавать жанр")
    void correctCreateGenre() throws Exception {
        mvc.perform(post("/api/genres")
                        .param("name", "Poem"))
                .andExpect(status().isCreated());
        Mockito.verify(genreService, times(1)).create("Poem");
    }
}