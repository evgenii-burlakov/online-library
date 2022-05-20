package ru.otus.commentmicroservice.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;
}
