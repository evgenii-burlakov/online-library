package ru.otus.usermicroservice.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.otus.usermicroservice.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserDto {

    private String username;
    private String password;
    private List<RoleDto> roles;

    public UserDto() {
    }

    public UserDto(String username, String password, List<RoleDto> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(RoleDto::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
