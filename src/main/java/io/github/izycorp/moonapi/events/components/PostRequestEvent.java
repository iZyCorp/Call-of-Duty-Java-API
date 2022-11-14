package io.github.izycorp.moonapi.events.components;

import io.github.izycorp.moonapi.events.Event;
import okhttp3.Response;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This event is executed after a request has received a response.
 */
public class PostRequestEvent extends Event {

    /**
     * This is a response object built from what the request has received
     */
    private final Response response;

    public PostRequestEvent(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String getEventName() {
        return "PostRequestEvent";
    }
}
