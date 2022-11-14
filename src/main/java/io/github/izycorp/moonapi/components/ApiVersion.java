package io.github.izycorp.moonapi.components;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>ApiVersion</h2>
 * <p>This is an enumeration that contains all the existing possibilities for the ApiVersion parameter in a HTTP request to call of duty server</p>
 * <br>
 * <p>Local identifier field must be use in http request as part of a valid URL</p>
 * <hr>
 * <strong>What about ApiVersion ?</strong>
 * <p>Api Version is the version of the call of duty API, it should be used according to what request you want to do.</p>
 *
 */
public enum ApiVersion {

    /**
     * Version 1.0 of the API
     */
    V1("v1"),

    /**
     * Version 2.0 of the API
     */
    V2("v2"),

    /**
     * Version 3.0 of the API
     */
    V3("v3");

    /**
     * Identifier is the valid string value of the Api version in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    ApiVersion(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
