package ru.otus.commentmicroservice.domain;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;

    private String name;

    public Author(String name) {
        this.name = name;
    }
}
