package io.github.izycorp.moonapi.components;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 * <h2>TimeFrame</h2>
 * <p>This is an enumeration that contains all the existing possibilities for the Game type parameter in a HTTP request to call of duty server.</p>
 */
public enum GameType {

    CORE("core"),
    HARDCORE("hc"),
    ARENA("arena");

    /**
     * Identifier is the valid string value of a game type in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    GameType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
