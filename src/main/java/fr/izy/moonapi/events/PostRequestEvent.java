package fr.izy.moonapi.events;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This event is executed after a request has received a response.
 */
public class PostRequestEvent extends Event {

    @Override
    String getEventName() {
        return "PostRequestEvent";
    }
}
