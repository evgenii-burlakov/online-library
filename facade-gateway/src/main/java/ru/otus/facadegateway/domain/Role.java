package ru.otus.facadegateway.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class Role {
    private Long id;

    private String role;

    private User user;
}
