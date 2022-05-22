package ru.otus.facadegateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private String password;

    private List<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
