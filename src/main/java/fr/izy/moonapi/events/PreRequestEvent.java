package fr.izy.moonapi.events;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This event is executed before a request is sent.
 */
public class PreRequestEvent extends Event implements Cancellable{
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    String getEventName() {
        return "PreRequestEvent";
    }
}
