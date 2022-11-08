package fr.izy.moonapi.events.components;

import fr.izy.moonapi.events.Cancellable;
import fr.izy.moonapi.events.Event;
import okhttp3.Headers;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This event is executed before a request is sent.
 */
public class PreRequestEvent extends Event implements Cancellable {

    /**
     * This is the generated url used to retrieve data from the API
     */
    private final String url;

    /**
     * This is an header object that has been used to send the request
     */
    private final Headers authHeader;

    public PreRequestEvent(String url, Headers authHeader) {
        this.url = url;
        this.authHeader = authHeader;
    }

    public String getGeneratedUrl() {
        return url;
    }

    public Headers getAuthHeader() {
        return authHeader;
    }

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
