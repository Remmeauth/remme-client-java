package io.remme.java.error;

/**
 * Exceptions specific to REMME keys processing
 */
public class RemmeKeyException extends RuntimeException {
    public RemmeKeyException(String message) {
        super(message);
    }

    public RemmeKeyException(Throwable e) {
        super(e);
    }
}
