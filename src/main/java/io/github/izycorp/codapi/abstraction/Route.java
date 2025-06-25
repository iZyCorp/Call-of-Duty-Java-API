package io.github.izycorp.codapi.abstraction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This annotation is used to define which route a method is linked to
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {

    /**
     * RequestRoute object
     * @return The RequestRoute of the method
     */
    RequestRoute requestRoute();

}