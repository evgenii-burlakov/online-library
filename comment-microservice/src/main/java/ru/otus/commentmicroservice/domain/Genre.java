package ru.otus.commentmicroservice.domain;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private Long id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
