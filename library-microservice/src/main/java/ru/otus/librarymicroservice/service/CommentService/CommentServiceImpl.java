package ru.otus.librarymicroservice.service.CommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.librarymicroservice.client.CommentMicroserviceClient;
import ru.otus.librarymicroservice.repositories.book.BookRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMicroserviceClient commentMicroserviceClient;
    private final BookRepository bookRepository;

    @Override
    public void deleteAllCommentByBookId(Long bookId) {
        commentMicroserviceClient.deleteAllCommentByBookId(bookId);
    }

    @Override
    public void deleteAllCommentByGenre(Long genreId) {
        bookRepository.findAllByGenre(genreId).forEach(b -> commentMicroserviceClient.deleteAllCommentByBookId(b.getId()));
    }

    @Override
    public void deleteAllCommentByAuthor(Long authorId) {
        bookRepository.findAllByAuthor(authorId).forEach(b -> commentMicroserviceClient.deleteAllCommentByBookId(b.getId()));
    }
}
