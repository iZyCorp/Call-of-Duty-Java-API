package fr.izy.moonapi;

import fr.izy.moonapi.abstraction.ProtectedRoute;
import fr.izy.moonapi.abstraction.PublicRoute;
import fr.izy.moonapi.abstraction.Title;
import fr.izy.moonapi.components.*;
import fr.izy.moonapi.exceptions.MoonViolationException;
import fr.izy.moonapi.query.RequestManager;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 * <p>
 *     This class is the main class of the API. It contains all methods of ProtectedRoute and PublicRoute that you can access with a simple instance of Moon Object.
 * </p>
 */
public class Moon extends Title implements ProtectedRoute, PublicRoute {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public Moon(RequestManager request) {
        super(request);
    }

    @Override
    public JSONObject searchPlayer(String playerName, Platform platform) throws MoonViolationException {
        return super.searchPlayer(playerName, platform);
    }

    @Override
    public JSONObject getUserProfile(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException {
        return super.getUserProfile(opus, mode, platform, username);
    }

    @Override
    public void getUserMatches() {
        throw new NotImplementedException();
    }

    @Override
    public JSONObject getUserMatchesHistory(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException {
        return super.getUserMatchesHistory(opus, mode, platform, username);
    }

    @Override
    public JSONObject getAvailableMaps(Opus opus, Gamemode mode, Platform platform) throws MoonViolationException {
        return super.getAvailableMaps(opus, mode, platform);
    }

    @Override
    public JSONObject getLootSeason(Opus opus, Platform platform, String language, int seasonNumber) throws MoonViolationException {
        return super.getLootSeason(opus, platform, language, seasonNumber);
    }

    @Override
    public JSONObject getPlayerLoadout(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException {
        return super.getPlayerLoadout(opus, mode, platform, username);
    }

    @Override
    public JSONObject getPlayerLeaderboard(Opus opus, Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws MoonViolationException {
        return super.getPlayerLeaderboard(opus, mode, type, platform, timeFrame, username);
    }

    @Override
    public JSONObject getLeaderBoards(Opus opus, Platform platform, TimeFrame timeFrame, Gamemode mode, GameType gameType, int page) throws MoonViolationException {
        return super.getLeaderBoards(opus, platform, timeFrame, mode, gameType, page);
    }

    @Override
    public JSONObject getMatch(Opus opus, Platform platform, int matchId) throws MoonViolationException {
        return super.getMatch(opus, platform, matchId);
    }

    @Override
    public JSONObject getMatchDetails(Opus opus, Gamemode gamemode, int matchId) throws MoonViolationException {
        return super.getMatchDetails(opus, gamemode, matchId);
    }

    @Override
    public JSONObject getUserMatchesDetailed(Opus opus, Platform platform, Gamemode gamemode, String username, int startTimestamp, int endTimestamp, int limit) throws MoonViolationException {
        return super.getUserMatchesDetailed(opus, platform, gamemode, username, startTimestamp, endTimestamp, limit);
    }
}
