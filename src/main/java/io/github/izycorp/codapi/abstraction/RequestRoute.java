package io.github.izycorp.codapi.abstraction;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h1>RequestRoute</h1>
 * <p>
 *     This enum contains all existing routes according to Call Of Duty API. It is mainly used to classify methods for better readability.
 * </p>
 */
public enum RequestRoute {

    /**
     * <p>
     *     Protected routes require an authenticated client but may supply data for any given player.
     * </p>
     * <b>Note:</b>
     * <p>If the targeted user hasn't set his profile to public,
     * the request will fail with <strong>"Not Permitted: not allowed"</strong> error code.
     * </p>
     *
     *
     */
    PROTECTED,

    /**
     * Public routes require no authenticated or initialization and can be interfaced without prior consideration.
     */
    PUBLIC,

    /**
     * Private routes may only be accessed by the authenticated client as they contain data specific to the client's account.
     * /!\ Private methods are not implemented yet.
     */
    PRIVATE
}
