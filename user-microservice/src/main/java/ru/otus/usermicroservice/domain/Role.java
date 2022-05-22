package ru.otus.usermicroservice.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
@Entity
@IdClass(RoleId.class)
@Table(name = "ROLES")
public class Role {
    @Id
    @Column(name = "ROLE")
    private String role;

    @Id
    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private User user;
}
