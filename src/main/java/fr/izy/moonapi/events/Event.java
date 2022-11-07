package fr.izy.moonapi.events;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This abstract class is used as a model for all events
 */
public abstract class Event {

    protected boolean cancelled;

    protected String eventName;

    protected abstract String getEventName();
}
