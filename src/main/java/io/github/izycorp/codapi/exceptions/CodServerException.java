package io.github.izycorp.codapi.exceptions;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>CodServerException</h2>
 * <p>This exception is thrown when a violation is detected in a request.</p>
 */
public class CodServerException extends Exception {

    public CodServerException(String message) {
        super(message);
    }

    public CodServerException(ErrorResponse errorResponse) {
        super("Error has been catch from distant server: " + errorResponse.getMessage());
    }

    public CodServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
