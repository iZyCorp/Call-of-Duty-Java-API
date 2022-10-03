package fr.izy.moonapi.components;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>TimeFrame</h2>
 * <p>This is an enumeration that contains all the existing possibilities for the TimeFrame parameter in a HTTP request to call of duty server</p>
 * <br>
 * <p>Local identifier field must be use in http request as part of a valid URL. This shall be use in LeaderBoard context.</p>
 */
public enum TimeFrame {

    ALLTIME("alltime"),
    MONTHLY("monthly"),
    WEEKLY("weekly");

    /**
     * Identifier is the valid string value of a TimeFrame in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    TimeFrame(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
