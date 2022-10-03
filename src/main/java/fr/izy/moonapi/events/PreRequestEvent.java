package fr.izy.moonapi.events;

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
