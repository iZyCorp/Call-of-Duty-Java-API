package io.github.izycorp.codapi.components;

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


    /**
     * Will be removed in the next update, no data are populated anymore. Warzone 1 API has shutdown
     */
    @Deprecated
    WARZONE("wz"),

    WARZONE2("wz2"),

    /**
     * This must be used to retrieve user matches
     */
    MULTIPLAYER("mp"),

    /**
     * Same as {@link Gamemode#MULTIPLAYER}
     */
    ZOMBIES("zm"),

    /**
     *
     */
    OUTBREAK("ob"),

    /**
     * Use that to fetch multiplayer leaderboard
     */
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
}
