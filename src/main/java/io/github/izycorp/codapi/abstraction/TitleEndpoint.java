package io.github.izycorp.codapi.abstraction;

import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.ErrorResponse;
import io.github.izycorp.codapi.exceptions.MoonViolationException;
import io.github.izycorp.codapi.query.RequestManager;

/**
 * @author iZy
 * @version 1.1
 * @since 1.0
 *
 * <h1>TitleEndpoint</h1>
 * <p>This class is an abstract class that handle all requests related to Call of Duty titles, It should be used as inheritance to access those</p>
 */
public abstract class TitleEndpoint {

    /**
     * RequestManager Object used to send requests to the API
     */
    protected final RequestManager request;

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public TitleEndpoint(RequestManager request) {
        this.request = request;
    }

    /*
     * PROTECTED ROUTE
     */

    /**
     * This method is used to return a specific user profile depending on the opus, the platform and the game mode
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param mode     - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param username - The name of the player you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    protected Page getUserProfile(Opus opus, Gamemode mode, Platform platform, String username, String ssoToken) throws MoonViolationException {
        final String rawResponseBody = request.sendRequestWithAuthentication("stats/cod/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/" + platform.getLookupType() + "/" + username + "/profile/type/" + mode.getIdentifier(), request.authenticate(ssoToken), "POST");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    /**
     * This method is used to return the matches history of a specific user depending on the opus and the game type
     * Usually the limit is 20, but you can set it to 0 to get all matches
     * <br>
     * <b>NOTE: startTimestamp AND endTimestamp CAN be set to 0 if you don't want to precise a date. </b>
     *
     * @param opus           - The opus of the game you want to search see {@link Opus}
     * @param username       - The name of the player you want to search
     * @param limit          - The limit of the request
     * @param startTimestamp - The start timestamp of the request
     * @param endTimestamp   - The end timestamp of the request
     * @throws MoonViolationException If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
        protected Page getUserMatches(Opus opus, Gamemode gamemode, Platform platform, String username, int limit, int startTimestamp, int endTimestamp, String ssoToken) throws MoonViolationException {
        final String rawResponseBody = request.sendRequestWithAuthentication("crm/cod/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/" + username + "/matches/" + gamemode.getIdentifier() + "/start/" + startTimestamp + "/end/" + endTimestamp + "?limit=" + limit, request.authenticate(ssoToken), "POST");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    /*
     * PUBLIC ROUTE
     */

    /**
     * This method retrieve the available maps for a specific opus and game mode
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param mode     - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getAvailableMaps(Opus opus, Gamemode mode, Platform platform) throws MoonViolationException {
        final String rawResponseBody = request.sendRequest("ce/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gameType/" + mode.getIdentifier() + "/communityMapData/availability");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    /**
     * <p>
     *     This method retrieve the loot of the current season (This is the Battle Pass)
     * </p>
     * <b>Surprisingly this doesn't return anything from Modern Warfare 2019 and Cold War</b>
     *
     * @param opus         - The opus of the game you want to search see {@link Opus}
     * @param platform     - The platform of the player you want to search see {@link Platform}
     * @param language     - The language of the game
     * @param seasonNumber - The season number of the game you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getLootSeason(Opus opus, Platform platform, String language, int seasonNumber) throws MoonViolationException {
        final String rawResponseBody = request.sendRequest("loot/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/list/loot_season_" + seasonNumber + "/" + language);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    /**
     * This method retrieves the player loadout (Doesn't return anything in any game as we know for now)
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param mode     - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param username - The name in String of the player you want
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getPlayerLoadout(Opus opus, Gamemode mode, Platform platform, String username) throws MoonViolationException {
        final String rawResponseBody = request.sendRequest("loadouts/" + ApiVersion.V3.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/" + username + "/mode/" + mode.getIdentifier());
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    /**
     * This method retrieve the player leaderboards according to a specific opus and game mode
     *
     * @param opus      - The opus of the game you want to search see {@link Opus}
     * @param mode      - The mode of the game you want to search see {@link Gamemode}
     * @param type      - The type of the game you want to search see {@link GameType}
     * @param platform  - The platform of the player you want to search see {@link Platform}
     * @param timeFrame - The time frame of the game you want to search see {@link TimeFrame}
     * @param username  - The name in String of the player you want
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getPlayerLeaderboard(Opus opus, Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws MoonViolationException {
        final String rawResponseBody = request.sendRequest("leaderboards/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/time/" + timeFrame.getIdentifier() + "/type/" + type.getIdentifier() + "/mode/" + mode.getIdentifier() + "/gamer/" + username);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    /**
     * <p>
     *    This method is used to retrieve the Leaderboard of a specific opus and a specific game mode
     * </p>
     *
     * <br>
     *
     * <b>Note:</b>
     * <p>
     *     Not every Call of duty title are available for this method
     *     I won't list them here in case of a change. If a MoonViolationException is thrown with "Invalid leaderboard title"
     *     it means that the title you are trying to retrieve is not available for this method (yet).
     * </p>
     *
     * @param opus      - The opus of the game you want to search see {@link Opus}
     * @param platform  - The platform of the player you want to search see {@link Platform}
     * @param timeFrame - The time frame of the leaderboard you want to search see {@link TimeFrame}
     * @param mode      - The mode of the game you want to search see {@link Gamemode}
     * @param gameType  - The game type of the leaderboard you want to search see {@link GameType}
     * @param page      - The page of the leaderboard you want to retrieve
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getLeaderboards(Opus opus, Platform platform, TimeFrame timeFrame, Gamemode mode, GameType gameType, int page) throws MoonViolationException {
        final String rawResponseBody = request.sendRequest("leaderboards/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/time/" + timeFrame.getIdentifier() + "/type/" + gameType.getIdentifier() + "/mode/" + mode.getIdentifier() + "/page/" + page);
        final Page pageObj = new Page(rawResponseBody);

        if (pageObj.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(pageObj));

        return pageObj;
    }

    /**
     * This method retrieve a specific match with the id passed in parameter of a specific opus in a specific platform
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param matchId  - The match id of the match you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getMatch(Opus opus, Platform platform, int matchId, String ssoToken) throws MoonViolationException {
        final String rawResponseBody = request.sendRequestWithAuthentication("ce/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/match/" + matchId + "/matchMapEvents", request.authenticate(ssoToken), "POST");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    /**
     * This method is used to retrieve a more detailed object of the wanted match
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param gamemode - The gamemode of the game you want to search see {@link Gamemode}
     * @param matchId  - The match id of the game you want to search
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getMatchDetails(Opus opus, Gamemode gamemode, int matchId) throws MoonViolationException {
        final String rawResponseBody = request.sendRequest("crm/cod/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + gamemode.getIdentifier() + "/fullMatch/" + matchId + "/it");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }
}
