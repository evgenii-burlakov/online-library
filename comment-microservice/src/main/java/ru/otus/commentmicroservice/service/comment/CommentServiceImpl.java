package ru.otus.commentmicroservice.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.commentmicroservice.client.LibraryMicroserviceClient;
import ru.otus.commentmicroservice.domain.Book;
import ru.otus.commentmicroservice.domain.Comment;
import ru.otus.commentmicroservice.exception.ApplicationException;
import ru.otus.commentmicroservice.repositories.CommentRepository;
import ru.otus.commentmicroservice.service.string.StringService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final StringService stringService;
    private final LibraryMicroserviceClient libraryMicroserviceClient;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByBookId(Long bookId) {
        return commentRepository.getAllByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteById(String username, long id) {
        commentRepository.findById(id)
                .filter(c -> c.getUsername().equals(username))
                .ifPresentOrElse(c -> commentRepository.deleteById(id),
                        () -> {
                            throw new ApplicationException("Only the user who created it can delete a comment");
                        });
    }

    @Override
    @Transactional
    public void deleteAllByBookId(long bookId) {
        commentRepository.deleteByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(String username, Long id) {
        return commentRepository.findById(id)
                .filter(c -> c.getUsername().equals(username))
                .orElseThrow(() -> new ApplicationException("Only the user who created it can get a comment"));
    }

    @Override
    @Transactional
    public void update(String username, long id, String stringComment) {
        if (stringService.verifyNotBlank(stringComment)) {
            commentRepository.findById(id).ifPresentOrElse(comment -> {
                if (!comment.getUsername().equals(username)) {
                    throw new ApplicationException("Only the user who created it can change a comment");
                }
                comment.setComment(stringComment);
                commentRepository.save(comment);
            }, () -> {
                throw new ApplicationException("Invalid comment id");
            });
        } else {
            throw new ApplicationException("Invalid comment");
        }
    }

    @Override
    @Transactional
    public Comment create(String username, String comment, Long bookId) {
        if (stringService.verifyNotBlank(comment)) {
            Book book = libraryMicroserviceClient.getBookById(bookId);
            if (book != null) {
                Comment newComment = new Comment(null, comment, bookId, username);
                return commentRepository.save(newComment);
            } else {
                throw new ApplicationException("Invalid book id");
            }
        } else {
            throw new ApplicationException("Invalid comment");
        }
    }
}
