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

    private final Exception catchedException;

    public ErrorInRequestEvent(Exception catchedException) {
        this.catchedException = catchedException;
    }

    @Override
    public String getEventName() {
        return "ErrorInRequestEvent";
    }

    public Exception getCatchedException() {
        return this.catchedException;
    }
}
