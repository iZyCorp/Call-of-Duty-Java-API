package fr.izy.moonapi.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This annotation is used to mark a method as an event handler and listen to it.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

    /**
     * This field is used to specify a priority for the event, by default it is set to NORMAL
     * see {@link fr.izy.moonapi.events.ListenerPriority} for more information
     * @return a valid ListenerPriority Object
     */
    ListenerPriority priority() default ListenerPriority.NORMAL;
}
