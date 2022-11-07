package fr.izy.moonapi.abstraction;

import fr.izy.moonapi.components.*;
import fr.izy.moonapi.exceptions.MoonViolationException;
import fr.izy.moonapi.query.RequestManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h1>Title</h1>
 * <p>This class is a model of a Call of Duty title, It should be used as inheritance</p>
 */
public abstract class Title {

    /**
     * RequestManager Object used to send requests to the API
     */
    protected final RequestManager request;

    /**
     * Initialize the Title Object with a RequestManager object
     * @param request - A valid RequestManager Object
     */
    public Title(RequestManager request) {
        this.request = request;
    }

    /*
     * PROTECTED ROUTE
     */

    /**
     * This method is used to return a specific user profile depending on the opus, the platform and the game mode
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param mode - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param username - The name of the player you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    protected JSONObject getUserProfile(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("stats/cod/"+ ApiVersion.V1 + "/title/"+ opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/"+ username + "/profile/type/" + mode.getIdentifier(), request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    @Route(requestRoute = RequestRoute.PROTECTED)
    protected void getUserMatches(Opus opus, Gamemode mode, Platform platform, String username, int limit, int startTimestamp, int endTimestamp) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("crm/cod/" + ApiVersion.V2 + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/" + username + "/matches/" + mode.getIdentifier() + "/start/" + startTimestamp + "/end/" + endTimestamp + "?limit=" + limit, request.authenticate("izy"));
        System.out.println(responseBody);
    }

    /**
     * This method is used to retrieve the Matches history of a user
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param mode - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param username - The name in String of the player you want
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    protected JSONObject getUserMatchesHistory(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("/crm/cod/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/${lookupType}/" + username + "profile/type/mp", request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    /*
     * PUBLIC ROUTE
     */

    /**
     * This method retrieve the available maps for a specific opus and game mode
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param mode - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected JSONObject getAvailableMaps(Opus opus, Gamemode mode, Platform platform) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("ce/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gameType/" + mode.getIdentifier() + "/communityMapData/availability", request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    /**
     * This method retrieve the loot of the current season
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param language - The language of the game
     * @param seasonNumber - The season number of the game you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected JSONObject getLootSeason(Opus opus, Platform platform, String language, int seasonNumber) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("loot/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/list/loot_season_" + seasonNumber + "/"+ language, request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    /**
     * This method retrieve the player loadout
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param mode - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param username - The name in String of the player you want
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected JSONObject getPlayerLoadout(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("loadouts/" + ApiVersion.V3.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/" + username + "/mode/" + mode.getIdentifier(), request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    /**
     * This method retrieve the player leaderboards according to a specific opus and game mode
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param mode - The mode of the game you want to search see {@link Gamemode}
     * @param type - The type of the game you want to search see {@link GameType}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param timeFrame - The time frame of the game you want to search see {@link TimeFrame}
     * @param username - The name in String of the player you want
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected JSONObject getPlayerLeaderboard(Opus opus, Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("leaderboards/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/time/" + timeFrame.getIdentifier() + "/type/" + type.getIdentifier() + "/mode/" + mode.getIdentifier() + "/gamer/" + username, request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    /**
     * This method is used to retrieve the Leaderboard of a specific opus and a specific game mode
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param timeFrame - The time frame of the leaderboard you want to search see {@link TimeFrame}
     * @param gameType - The game type of the leaderboard you want to search see {@link GameType}
     * @param page - The page of the leaderboard you want to retrieve
     * @return A valid JSONObject
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected JSONObject getLeaderboards(Opus opus, Platform platform, TimeFrame timeFrame, Gamemode mode, GameType gameType, int page) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequest("leaderboards/"+ ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/time/" + timeFrame.getIdentifier() + "/type/" + gameType.getIdentifier() + "/mode/" + mode.getIdentifier() + "/page/" + page);
        return new JSONObject(responseBody);
    }

    /**
     * This method retrieve a specific match with the id passed in parameter of a specific opus in a specific platform
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param matchId - The match id of the match you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected JSONObject getMatch(Opus opus, Platform platform, int matchId) throws MoonViolationException {
        if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("ce/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/match/" + matchId + "/matchMapEvents", request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    /**
     * This method is used to retrieve a more detailed object of the wanted match
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param gamemode - The gamemode of the game you want to search see {@link Gamemode}
     * @param matchId - The match id of the game you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected JSONObject getMatchDetails(Opus opus, Gamemode gamemode, int matchId) throws MoonViolationException {
        if(!checkGamemodeCompatibility(opus, gamemode)) throw new MoonViolationException(opus, gamemode);
        String responseBody = request.sendRequest("crm/cod/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + gamemode.getIdentifier() + "/fullMatch/" + matchId + "/it");
        return new JSONObject(responseBody);
    }

    /**
     * This method retrieve matches history of a specific player in a specific opus and platform
     * @param opus - The opus of the game you want to search see {@link Opus}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param gamemode - The gamemode of the game you want to search see {@link Gamemode}
     * @param username - The name in String of the player you want
     * @param startTimestamp - The start timestamp of the match you want to search
     * @param endTimestamp - The end timestamp of the match you want to search
     * @param limit - The limit of the match you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected  JSONObject getUserMatchesDetailed(Opus opus, Platform platform, Gamemode gamemode, String username, int startTimestamp, int endTimestamp, int limit) throws MoonViolationException {
        if(!checkGamemodeCompatibility(opus, gamemode)) throw new MoonViolationException(opus, gamemode);
        else if(!checkPlatformCompatibility(opus, platform)) throw new MoonViolationException(opus, platform);
        String responseBody = request.sendRequestWithAuthentication("crm/cod/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/" + username + "/matches/" + gamemode.getIdentifier() + "/start/" + startTimestamp + "/end/" + endTimestamp + "/details?limit=" + limit, request.authenticate("izy"));
        return new JSONObject(responseBody);
    }

    /*
     * UTILS
     */
    private boolean checkGamemodeCompatibility(Opus opus, Gamemode gamemode) {
        return Arrays.asList(opus.getCompatibleGamemodes()).contains(gamemode);
    }

    private boolean checkPlatformCompatibility(Opus opus, Platform platform) {
        return Arrays.asList(opus.getCompatiblePlatforms()).contains(platform);
    }
}
