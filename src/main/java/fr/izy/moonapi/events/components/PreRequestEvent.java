package fr.izy.moonapi.events.components;

import fr.izy.moonapi.events.Cancellable;
import fr.izy.moonapi.events.Event;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This event is executed before a request is sent.
 */
public class PreRequestEvent extends Event implements Cancellable {
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String getEventName() {
        return "PreRequestEvent";
    }
}
