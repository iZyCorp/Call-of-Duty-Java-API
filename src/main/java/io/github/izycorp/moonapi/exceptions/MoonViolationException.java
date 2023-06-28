package io.github.izycorp.moonapi.exceptions;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>MoonViolationException</h2>
 * <p>This exception is thrown when a violation is detected in a request.</p>
 */
public class MoonViolationException extends Exception {

    public MoonViolationException(String message) {
        super(message);
    }

    public MoonViolationException(ErrorResponse errorResponse) {
        super("Error has been catch from distant server: " + errorResponse.getMessage());
    }

    public MoonViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
