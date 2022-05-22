package ru.otus.commentmicroservice.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.commentmicroservice.domain.Comment;
import ru.otus.commentmicroservice.dto.CommentDto;
import ru.otus.commentmicroservice.service.comment.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/api/comments/{id}")
    public CommentDto getCommentById(@RequestHeader("user") String username, @PathVariable("id") Long id) {
        return CommentDto.toDto(commentService.getById(username, id));
    }

    @GetMapping("/api/comments")
    public List<CommentDto> getCommentByBookId(@RequestParam("bookId") Long bookId) {
        return commentService.getAllByBookId(bookId).stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/comments")
    public ResponseEntity<Comment> createComment(@RequestHeader("user") String username, CommentDto comment) {
        commentService.create(username, comment.getComment(), comment.getBookId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<Comment> updateComment(@RequestHeader("user") String username, CommentDto comment) {
        commentService.update(username, comment.getId(), comment.getComment());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Comment> deleteComment(@RequestHeader("user") String username, @PathVariable("id") Long id) {
        commentService.deleteById(username, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/comments/{bookId}")
    public ResponseEntity<Comment> deleteAllCommentByBookId(@PathVariable("bookId") Long bookId) {
        commentService.deleteAllByBookId(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
