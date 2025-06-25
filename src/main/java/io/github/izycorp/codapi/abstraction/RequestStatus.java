package io.github.izycorp.codapi.abstraction;

/**
 * Represents the possible outcomes of an HTTP request.
 * <ul>
 *     <li>{@link #SUCCESS} – The request was completed successfully and returned a valid response.</li>
 *     <li>{@link #ERROR} – The request failed due to an error (e.g., network issue, invalid parameters, server error).</li>
 * </ul>
 */
public enum RequestStatus {
    SUCCESS,
    ERROR;
}

