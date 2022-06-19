package ru.otus.commentmicroservice.service.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.commentmicroservice.service.comment.CommentService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Сервис для работы с строками должен ")
class StringServiceImplTest {
    @Autowired
    private StringService stringService;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("корректно форматировать строки")
    void beautifyStringName() {
        String expectedString = "PUSHKIN A. S.";
        String actualString = stringService.beautifyStringName(" PUshKIN   A.    S.    ");
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    @DisplayName("корректно форматировать пустые строки")
    void beautifyBlankStringName() {
        String expectedString = "";
        String actualString = stringService.beautifyStringName("     ");
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    @DisplayName("возвращать null при форматировании null строки")
    void beautifyNullStringName() {
        String actualString = stringService.beautifyStringName(null);
        assertThat(actualString).isNull();
    }

    @Test
    @DisplayName("корректно верифицировать строки в  случае, когда хоть одно значение равно null")
    void correctVerifyNullStrings() {
        boolean actualResult = stringService.verifyNotBlank("SAD", "HAPPY", null);
        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("корректно верифицировать строки в  случае, когда хоть одно значение пустое")
    void correctVerifyBlankStrings() {
        boolean actualResult = stringService.verifyNotBlank("SAD", "HAPPY", "");
        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("корректно верифицировать правильные строки")
    void correctVerifyStrings() {
        boolean actualResult = stringService.verifyNotBlank("SAD", "HAPPY", "NORMAL");
        assertThat(actualResult).isTrue();
    }
}