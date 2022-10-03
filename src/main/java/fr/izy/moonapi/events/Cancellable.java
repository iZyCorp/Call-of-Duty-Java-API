package fr.izy.moonapi.events;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This interface is used in case an event is cancellable and provides methods to handle it.
 */
public interface Cancellable {

    /**
     * This method is used to check if the event is cancelled
     * @return true if the event is cancelled, false otherwise
     */
    boolean isCancelled();

    /**
     * This method is used to cancel the event
     */
    void setCancelled(boolean cancelled);
}
