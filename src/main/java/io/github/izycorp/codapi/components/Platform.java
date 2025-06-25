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

    /**
     * Represents a Uno ID, the unique Activision identifier for a player.
     * <p>
     * It typically follows the format <code>username#1234567</code> and is used to uniquely identify accounts
     * across Call of Duty games and services.
     * </p>
     * <p>
     * You can retrieve your Uno ID in several ways:
     * <ul>
     *     <li>By visiting your profile on the official Call of Duty website:
     *         <a href="https://profile.callofduty.com/cod/info">https://profile.callofduty.com/cod/info</a></li>
     *     <li>In-game, through the "Account & Network" settings menu (e.g., in Black Ops 6)</li>
     * </ul>
     * </p>
     * <p><strong>Warning:</strong> Before using a Uno ID in a URL, you must URL-encode it.
     * In particular, the <code>#</code> character must be replaced with <code>%23</code>.</p>
     * <p>For example: <code>username#1234567</code> becomes <code>username%231234567</code>.</p>
     */
    UNO("uno"),

    /**
     * Represents the Battle.net platform identifier.
     * <p>
     * When using this platform, you must provide the user's Battle.net ID. It follows a format similar to the Uno ID:
     * <code>username#12345</code>. However, note that the Battle.net ID and the Uno ID are distinct and not interchangeable.
     * </p>
     * <p>
     * You can find your Battle.net ID by opening the Battle.net desktop application and clicking on your profile name
     * in the top-right corner. The ID will be displayed just below your username.
     * </p>
     * <p><strong>Warning:</strong> Just like the Uno ID, the Battle.net ID must be URL-encoded when used in a request.
     * For example: <code>username#12345</code> becomes <code>username%2312345</code>.</p>
     */
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
