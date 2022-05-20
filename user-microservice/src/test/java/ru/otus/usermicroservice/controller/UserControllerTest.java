package ru.otus.usermicroservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.usermicroservice.domain.Role;
import ru.otus.usermicroservice.domain.User;
import ru.otus.usermicroservice.service.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@DisplayName("Контроллер для работы с пользователями должен ")
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("корректно возвращать пользователя по имени")
    void getUserByName() throws Exception {
        given(userService.getByUserName("test")).willReturn(Optional.of(new User(1L, "TEST", "TEST", new ArrayList<>())));

        mvc.perform(get("/api/users/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("username", is("TEST")))
                .andExpect(jsonPath("password", is("TEST")))
                .andExpect(jsonPath("roles", hasSize(0)));
    }

    @Test
    @DisplayName("корректно создавать пользователя")
    void createUserWithUserRole() throws Exception {
        mvc.perform(post("/api/users")
                        .param("username", "Pushkin")
                        .param("password", "password"))
                .andExpect(status().isCreated());
        Mockito.verify(userService, times(1)).createWithUserRole("Pushkin", "password");
    }
}