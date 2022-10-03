package fr.izy.moonapi.events;

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
