package io.github.izycorp.codapi.exceptions;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>CodRequestException</h2>
 * <p>This exception is thrown when a violation is detected in a request.</p>
 */
public class CodRequestException extends Exception {

    public CodRequestException(final String message) {
        super(message);
    }

    public CodRequestException(final ErrorResponse errorResponse) {
        super("Error has been catch from distant server: " + errorResponse.getMessage());
    }

    public CodRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
