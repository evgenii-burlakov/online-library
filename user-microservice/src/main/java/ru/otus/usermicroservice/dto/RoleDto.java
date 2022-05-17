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
    private Long id;

    private String role;

    public RoleDto() {
    }

    public RoleDto(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();
    }
}
