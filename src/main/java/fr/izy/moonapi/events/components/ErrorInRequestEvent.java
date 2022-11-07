package fr.izy.moonapi.events.components;

import fr.izy.moonapi.events.Event;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This event is executed when an error occurred during a request.
 */
public class ErrorInRequestEvent extends Event {

    private final String errorCode;

    public ErrorInRequestEvent(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getEventName() {
        return "ErrorInRequestEvent";
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
