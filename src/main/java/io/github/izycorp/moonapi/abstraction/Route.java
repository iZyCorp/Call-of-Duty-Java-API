package io.github.izycorp.moonapi.abstraction;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This annotation is used to define which route a method is linked to
 */
public @interface Route {

    /**
     * RequestRoute object
     * @return The RequestRoute of the method
     */
    RequestRoute requestRoute();

}