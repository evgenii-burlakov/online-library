package ru.otus.librarymicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.librarymicroservice.domain.Comment;

@FeignClient(name = "comment-microservice")
public interface CommentMicroserviceClient {

    @DeleteMapping("/api/comments")
    ResponseEntity<Comment> deleteAllCommentByBookId(@RequestBody Long bookId);
}
