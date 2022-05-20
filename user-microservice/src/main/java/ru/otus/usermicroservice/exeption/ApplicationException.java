package ru.otus.usermicroservice.exeption;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
