package io.remme.java.error;

public class RemmeSocketException extends RuntimeException {
    public RemmeSocketException(String message) {
        super(message);
    }

    public RemmeSocketException(Throwable e) {
        super(e);
    }
}
