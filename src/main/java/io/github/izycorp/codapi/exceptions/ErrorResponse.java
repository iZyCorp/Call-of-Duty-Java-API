package io.github.izycorp.codapi.exceptions;

import io.github.izycorp.codapi.abstraction.Page;

/**
 * This class is used to model an error response from the distant server
 */
public class ErrorResponse {

    private final String exceptionType;

    private final String message;

    public ErrorResponse(Page page) {
        this.exceptionType = page.getData().getString("type");
        this.message = page.getData().getString("message");
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getMessage() {
        return message;
    }
}
