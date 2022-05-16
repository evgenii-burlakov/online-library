package ru.otus.usermicroservice.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.otus.usermicroservice.domain.Role;
import ru.otus.usermicroservice.domain.User;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private List<Role> roles;

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
