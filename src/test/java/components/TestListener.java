package components;

import io.github.izycorp.codapi.events.EventHandler;
import io.github.izycorp.codapi.events.Listener;
import io.github.izycorp.codapi.events.ListenerPriority;
import io.github.izycorp.codapi.events.components.PreRequestEvent;

public class TestListener extends Listener {

    @EventHandler(priority = ListenerPriority.HIGH)
    public void onPreRequest(final PreRequestEvent event) {
        System.out.println(event.getGeneratedUrl());
    }
}
