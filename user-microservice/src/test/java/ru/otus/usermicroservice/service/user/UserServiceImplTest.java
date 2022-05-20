package ru.otus.usermicroservice.service.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.usermicroservice.domain.Role;
import ru.otus.usermicroservice.domain.User;
import ru.otus.usermicroservice.exeption.ApplicationException;
import ru.otus.usermicroservice.repository.RoleRepository;
import ru.otus.usermicroservice.repository.UserRepository;
import ru.otus.usermicroservice.service.string.StringService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@DisplayName("Сервис для работы с пользователями должен ")
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private StringService stringService;


    @Test
    @DisplayName("корректно возвращать пользователя по имени")
    void shouldCorrectGetUserByName() {
        Mockito.when(userRepository.findByUsername("PUSHKIN")).thenReturn(Optional.of(new User(1L, "PUSHKIN", "PASSWORD")));
        Optional<User> actualUser = userService.getByUserName("PUSHKIN");
        assertThat(actualUser).isEqualTo(Optional.of(new User(1L, "PUSHKIN", "PASSWORD")));
    }

    @Test
    @DisplayName("корректно создавать пользователя")
    void shouldCorrectCreateUser() {
        Mockito.when(stringService.beautifyStringName("lermontov")).thenReturn("LERMONTOV");
        Mockito.when(stringService.beautifyStringName("password")).thenReturn("PASSWORD");

        Mockito.when(stringService.verifyNotBlank("LERMONTOV", "PASSWORD")).thenReturn(true);

        Mockito.when(userRepository.findByUsername("LERMONTOV")).thenReturn(Optional.empty());
        userService.createWithUserRole("lermontov", "password");
        Mockito.verify(userRepository, Mockito.times(1)).save(new User(null, "LERMONTOV", "PASSWORD"));
        Mockito.verify(roleRepository, Mockito.times(1)).save(new Role(null, "USER", userRepository.save(new User(null, "LERMONTOV", "PASSWORD"))));
    }

    @Test
    @DisplayName("выкидывать ошибку при создании пользователя с не валидным паролем")
    void shouldNotCreateUserWithIncorrectPassword() {
        Mockito.when(stringService.beautifyStringName("lermontov")).thenReturn("LERMONTOV");
        Mockito.when(stringService.beautifyStringName(" ")).thenReturn("");

        Mockito.when(stringService.verifyNotBlank("LERMONTOV", "")).thenReturn(false);

        assertThatThrownBy(() -> userService.createWithUserRole("lermontov", " ")).isInstanceOf(ApplicationException.class);
    }

    @Test
    @DisplayName("выкидывать ошибку при создании не валидного пользователя")
    void shouldNotCreateUserWithNonUniqueName() {
        Mockito.when(stringService.beautifyStringName("lermontov")).thenReturn("LERMONTOV");
        Mockito.when(stringService.beautifyStringName("password")).thenReturn("PASSWORD");

        Mockito.when(stringService.verifyNotBlank("LERMONTOV", "PASSWORD")).thenReturn(true);

        Mockito.when(userRepository.findByUsername("LERMONTOV")).thenReturn(Optional.of(new User(1L, "LERMONTOV", "PASSWORD1")));

        assertThatThrownBy(() -> userService.createWithUserRole("lermontov", "password")).isInstanceOf(ApplicationException.class);
    }
}