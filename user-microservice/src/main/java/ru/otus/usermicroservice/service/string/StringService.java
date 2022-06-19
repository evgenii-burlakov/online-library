package ru.otus.usermicroservice.service.string;

public interface StringService {

    String beautifyStringName(String name);

    boolean verifyNotBlank(String... strings);
}
