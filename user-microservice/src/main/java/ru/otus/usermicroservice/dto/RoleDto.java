package ru.otus.usermicroservice.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.otus.usermicroservice.domain.Role;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class RoleDto {
    private String role;

    public RoleDto() {
    }

    public RoleDto(String role) {
        this.role = role;
    }

    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .role(role.getRole())
                .build();
    }
}
