package fr.izy.moonapi.events;

import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This class is used to listen to events
 */
public abstract class Listener {

    private final ConcurrentLinkedQueue<Method> handlers = new ConcurrentLinkedQueue<>();

    public Listener() {
        registerHandlers();
    }

    /**
     * This method is used to register an event handler
     * @param method The method to register
     */
    private void registerHandler(Method method) {
        handlers.add(method);
    }

    /**
     * This method retrieves all methods that are annotated with EventHandler and register them
     */
    public void registerHandlers() {

        Queue<Method> methodsHighPriority = new ConcurrentLinkedQueue<>();
        Queue<Method> methodsNormalPriority = new ConcurrentLinkedQueue<>();
        Queue<Method> methodsLowPriority = new ConcurrentLinkedQueue<>();

        for (Method method : getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                if (method.getParameterCount() == 1) {
                    if (method.getParameterTypes()[0].getSuperclass() == Event.class) {
                        switch (method.getAnnotation(EventHandler.class).priority()) {
                            case HIGH:
                                methodsHighPriority.add(method);
                                break;
                            case NORMAL:
                                methodsNormalPriority.add(method);
                                break;
                            case LOW:
                                methodsLowPriority.add(method);
                                break;
                        }
                    }
                }
            }
        }

        handlers.addAll(methodsHighPriority);
        handlers.addAll(methodsNormalPriority);
        handlers.addAll(methodsLowPriority);
    }

    /**
     * This method is used to unregister an event handler
     * @param method The method to unregister
     */
    private void unregisterHandler(Method method) {
        handlers.remove(method);
    }

    /**
     * This method is used to get all registered event handlers
     * @return A ConcurrentHashMap containing all registered event handlers
     */
    public ConcurrentLinkedQueue<Method> getHandlers() {
        return handlers;
    }

    /**
     * Methods that will call all methods with @EventHandler annotation and an
     * Event object as parameter that are the same as the object passed in parameter.
     * @param event The event to call
     */
    public void callEventFromClass(Class<? extends Event> event) {
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

    /**
     * Methods that will call event in registered handlers that are the same as the event class passed in parameter.
     * Event object as parameter that are the same as the object passed in parameter.
     * @param event The event to call
     */
    public void callEvent(Event event) {
        for (java.lang.reflect.Method method : handlers) {
            if (method.getParameterTypes()[0].equals(event.getClass())) {
                try {
                    method.invoke(this, event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


