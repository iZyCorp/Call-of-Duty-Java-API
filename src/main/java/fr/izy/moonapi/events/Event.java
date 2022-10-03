package fr.izy.moonapi.events;

public abstract class Event {

    protected boolean cancelled;

    protected String eventName;

    abstract String getEventName();
}
