package io.github.izycorp.codapi.components;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 * <h2>Platform</h2>
 * <p>This is an enumeration that contains all the existing possibilities for the Platform parameter in a HTTP request to call of duty server</p>
 * <hr>
 * <p>Local identifier field must be use in http request as part of a valid URL</p>
 */
public enum Platform {

    ALL("all"),
    ACTIVISION("acti"),
    UNO("uno"),
    BATTLE_NET("battle"),
    STEAM("steam"),
    XBOX("xbl"),
    PLAYSTATION("psn");

    /**
     * Identifier is the valid string value of a platform in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    Platform(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLookupType() {
        return this == UNO ? "id" : "gamer";
    }
}
