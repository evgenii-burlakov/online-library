package ru.otus.commentmicroservice.domain;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;

    private String name;
    private Author author;
    private Genre genre;
}
