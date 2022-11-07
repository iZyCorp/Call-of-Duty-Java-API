package fr.izy.moonapi.events;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This class is used to listen to events
 */
public abstract class Listener {
    /**
     * Methods that will call all methods with @EventHandler annotation and an
     * Event object as parameter that are the same as the object passed in parameter.
     * @param event The event to call
     */
    public void callEvent(Class<? extends Event> event) {
        for (java.lang.reflect.Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                if (method.getParameterCount() == 1) {
                    if (method.getParameterTypes()[0].equals(event)) {
                        try {
                            method.invoke(this, event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}


