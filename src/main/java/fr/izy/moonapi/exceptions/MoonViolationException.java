package fr.izy.moonapi.exceptions;

import fr.izy.moonapi.components.Gamemode;
import fr.izy.moonapi.components.Opus;
import fr.izy.moonapi.components.Platform;

import java.util.Arrays;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>MoonViolationException</h2>
 * <p>This exception is thrown when a violation is detected in a request.</p>
 */
public class MoonViolationException extends Exception {

    public MoonViolationException(String message) {
        super(message);
    }

    public MoonViolationException(Opus opus, Gamemode gamemode) {
        super("The opus " + opus.getIdentifier() + " is inconsistent with the gamemode " + gamemode.getIdentifier() + ". Please use the correct opus for this gamemode. Compatible opus for this gamemode are : " + Arrays.toString(opus.getCompatibleGamemodes()));
    }

    public MoonViolationException(Opus opus, Platform platform) {
        super("The opus " + opus.getIdentifier() + " is inconsistent with the platform " + platform.getIdentifier() + ". Please use the correct opus for this platform. Compatible opus for this platform are : " + Arrays.toString(opus.getCompatiblePlatforms()));
    }

    public MoonViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
