package io.github.izycorp.moonapi.components;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 * <h2>Opus</h2>
 * <p>This is an enumeration that contains all the existing possibilities for the Opus(Game) parameter in a HTTP request to call of duty server</p>
 * <br>
 * <p>Local identifier field must be use in http request as part of a valid URL</p>
 * <br>
 * <p>If you want to target Warzone, please be aware that Activision consider Warzone as a gamemode not as an Opus. </p>
 * @see Gamemode#WARZONE
 *
 */
public enum Opus {

    BO3("bo3"),
    INFINITE_WARFARE("iw"),
    WWII("wwii"),
    BO4("bo4"),
    MW2019("mw"),

    COLD_WAR("cw"),

    VANGUARD("vg"),

    MW2("mw2");

    /**
     * Identifier is the valid string value of an opus in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    Opus(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
