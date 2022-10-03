package fr.izy.moonapi.components;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>Gamemode</h2>
 * <p>This is an enumeration that contains all the existing possibilities for the Game mode parameter in a HTTP request to call of duty server</p>
 * <br>
 * <p>Local identifier field must be use in http request as part of a valid URL</p>
 */
public enum Gamemode {

    WARZONE("wz"),
    MULTIPLAYER("mp"),
    ZOMBIES("zm"),

    CAREER("career");

    /**
     * Identifier is the valid string value of a game mode in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    Gamemode(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Gamemode fromIdentifier(String identifier) {
        for (Gamemode gamemode : Gamemode.values()) {
            if (gamemode.getIdentifier().equals(identifier)) {
                return gamemode;
            }
        }
        return null;
    }
}
