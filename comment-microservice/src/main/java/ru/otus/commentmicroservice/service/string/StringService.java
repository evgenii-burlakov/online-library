package ru.otus.commentmicroservice.service.string;

public interface StringService {

    String beautifyStringName(String name);

    boolean verifyNotBlank(String... strings);
}
