package ru.otus.commentmicroservice.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
