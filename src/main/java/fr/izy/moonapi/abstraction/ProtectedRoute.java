package fr.izy.moonapi.abstraction;

import fr.izy.moonapi.components.Gamemode;
import fr.izy.moonapi.components.Opus;
import fr.izy.moonapi.components.Platform;
import fr.izy.moonapi.exceptions.MoonViolationException;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This interface contains all methods that are available for the protected API.
 */
@Route(requestRoute = RequestRoute.PROTECTED)
public interface ProtectedRoute {

    JSONObject searchPlayer(String playerName, Platform platform) throws MoonViolationException;

    JSONObject getUserProfile(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException;

    void getUserMatches() throws NotImplementedException;

    JSONObject getUserMatchesHistory(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException;
}
