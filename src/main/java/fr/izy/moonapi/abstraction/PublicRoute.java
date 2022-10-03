package fr.izy.moonapi.abstraction;

import fr.izy.moonapi.components.*;
import fr.izy.moonapi.exceptions.MoonViolationException;
import org.json.JSONObject;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * This interface contains all methods that are available for the public API.
 */
@Route(requestRoute = RequestRoute.PUBLIC)
public interface PublicRoute {

    JSONObject getAvailableMaps(Opus opus, Gamemode mode, Platform platform) throws MoonViolationException;

    JSONObject getLootSeason(Opus opus, Platform platform, String language, int seasonNumber) throws MoonViolationException;

    JSONObject getPlayerLoadout(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException;

    JSONObject getPlayerLeaderboard(Opus opus, Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws MoonViolationException;

    JSONObject getLeaderBoards(Opus opus, Platform platform, TimeFrame timeFrame, Gamemode mode, GameType gameType, int page) throws MoonViolationException;

    JSONObject getMatch(Opus opus, Platform platform, int matchId) throws MoonViolationException;

    JSONObject getMatchDetails(Opus opus, Gamemode gamemode, int matchId) throws MoonViolationException;

    JSONObject getUserMatchesDetailed(Opus opus, Platform platform, Gamemode gamemode, String username, int startTimestamp, int endTimestamp, int limit) throws MoonViolationException;
}
