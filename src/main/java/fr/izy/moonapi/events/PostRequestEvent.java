package fr.izy.moonapi.events;

public class PostRequestEvent extends Event {

    @Override
    String getEventName() {
        return "PostRequestEvent";
    }
}
