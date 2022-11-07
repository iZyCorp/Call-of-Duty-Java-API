package fr.izy.moonapi.events.components;

import fr.izy.moonapi.events.Event;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This event is executed after a request has received a response.
 */
public class PostRequestEvent extends Event {

    @Override
    public String getEventName() {
        return "PostRequestEvent";
    }
}
