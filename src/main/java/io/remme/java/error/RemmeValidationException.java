package io.remme.java.error;

public class RemmeValidationException extends RuntimeException {
    public RemmeValidationException(String message) {
        super(message);
    }

    public RemmeValidationException(Throwable e) {
        super(e);
    }
}
