package ru.otus.commentmicroservice.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.otus.commentmicroservice.domain.Comment;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class CommentDto {
    private Long id;

    private String comment;
    private Long bookId;
    private String username;

    public CommentDto() {
    }

    public CommentDto(Long id, String comment, Long bookId, String username) {
        this.id = id;
        this.comment = comment;
        this.bookId = bookId;
        this.username = username;
    }

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .bookId(comment.getBookId())
                .username(comment.getUsername())
                .build();
    }

    public Comment toBean() {
        return new Comment(id, comment, bookId, username);
    }
}
