package io.github.izycorp.codapi.abstraction;

import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.exceptions.ErrorResponse;
import io.github.izycorp.codapi.query.RequestManager;

/**
 * @author iZy
 * @version 1.1
 * @since 1.0
 *
 * <h2>TitleEndpoint</h2>
 * <p>This class is an abstract class that handle all requests related to Call of Duty titles; It should be used as inheritance to access those</p>
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
    public TitleEndpoint(final RequestManager request) {
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
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    protected Page getUserProfile(final Opus opus, final Gamemode mode, final Platform platform, final String username) throws CodRequestException {
        final String rawResponseBody = request.sendRequestWithAuthentication("stats/cod/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/" + platform.getLookupType() + "/" + username + "/profile/type/" + mode.getIdentifier());
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

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
     * @throws CodRequestException If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
        protected Page getUserMatches(final Opus opus, final Gamemode gamemode, final Platform platform, final String username, final int limit, final int startTimestamp, final int endTimestamp) throws CodRequestException {
        final String rawResponseBody = request.sendRequestWithAuthentication("crm/cod/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/" + username + "/matches/" + gamemode.getIdentifier() + "/start/" + startTimestamp + "/end/" + endTimestamp + "?limit=" + limit);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

        return page;
    }

    /*
     * PUBLIC ROUTE
     */

    /**
     * This method retrieves the available maps for a specific opus and game mode
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param mode     - The mode of the game you want to search see {@link Gamemode}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @return A valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getAvailableMaps(final Opus opus, final Gamemode mode, final Platform platform) throws CodRequestException {
        final String rawResponseBody = request.sendRequest("ce/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gameType/" + mode.getIdentifier() + "/communityMapData/availability");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

        return page;
    }

    /**
     * <p>
     *     This method retrieves the loot of the current season (This is the Battle Pass)
     * </p>
     * <b>Surprisingly, this doesn't return anything from Modern Warfare 2019 and Cold War</b>
     *
     * @param opus         - The opus of the game you want to search see {@link Opus}
     * @param platform     - The platform of the player you want to search see {@link Platform}
     * @param language     - The language of the game
     * @param seasonNumber - The season number of the game you want to search
     * @return A valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getLootSeason(final Opus opus, final Platform platform, final String language, final int seasonNumber) throws CodRequestException {
        final String rawResponseBody = request.sendRequest("loot/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/list/loot_season_" + seasonNumber + "/" + language);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

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
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PRIVATE)
    protected Page getPlayerLoadout(final Opus opus, final Gamemode mode, final Platform platform, final String username) throws CodRequestException {
        final String rawResponseBody = request.sendRequest("loadouts/" + ApiVersion.V3.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/gamer/" + username + "/mode/" + mode.getIdentifier());
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

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
     *     Not every Call of Duty title is available for this method.
     *     I won't list them here in case of a change. If a CodRequestException is thrown with "Invalid leaderboard title"
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
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getLeaderboards(final Opus opus, final Platform platform, final TimeFrame timeFrame, final Gamemode mode, final GameType gameType, final int page) throws CodRequestException {
        final String rawResponseBody = request.sendRequest("leaderboards/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/time/" + timeFrame.getIdentifier() + "/type/" + gameType.getIdentifier() + "/mode/" + mode.getIdentifier() + "/page/" + page);
        final Page pageObj = new Page(rawResponseBody);

        if (pageObj.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(pageObj));

        return pageObj;
    }

    /**
     * This method retrieves a specific match with the id passed in parameter of a specific opus in a specific platform
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param matchId  - The match id of the match you want to search
     * @return A valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getMatch(final Opus opus, final Platform platform, final int matchId) throws CodRequestException {
        final String rawResponseBody = request.sendRequestWithAuthentication("ce/" + ApiVersion.V1.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/match/" + matchId + "/matchMapEvents");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

        return page;
    }

    /**
     * This method is used to retrieve a more detailed object of the wanted match
     *
     * @param opus     - The opus of the game you want to search see {@link Opus}
     * @param gamemode - The gamemode of the game you want to search see {@link Gamemode}
     * @param matchId  - The match id of the game you want to search
     * @return A valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    protected Page getMatchDetails(final Opus opus, final Gamemode gamemode, final int matchId) throws CodRequestException {
        final String rawResponseBody = request.sendRequest("crm/cod/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + gamemode.getIdentifier() + "/fullMatch/" + matchId + "/it");
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

        return page;
    }

    /*
     * PRIVATE ROUTE
     */

    /**
     * This method retrieves the player leaderboards according to a specific opus, game mode and is specific to the authenticated user
     *
     * @param opus      - The opus of the game you want to search see {@link Opus}
     * @param mode      - The mode of the game you want to search see {@link Gamemode}
     * @param type      - The type of the game you want to search see {@link GameType}
     * @param platform  - The platform of the player you want to search see {@link Platform}
     * @param timeFrame - The time frame of the game you want to search see {@link TimeFrame}
     * @param username  - The name in String of the player you want
     * @return A valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PRIVATE)
    protected Page getPlayerLeaderboard(final Opus opus, final Gamemode mode, final GameType type, final Platform platform, final TimeFrame timeFrame, final String username) throws CodRequestException {
        final String rawResponseBody = request.sendRequestWithAuthentication("leaderboards/" + ApiVersion.V2.getIdentifier() + "/title/" + opus.getIdentifier() + "/platform/" + platform.getIdentifier() + "/time/" + timeFrame.getIdentifier() + "/type/" + type.getIdentifier() + "/mode/" + mode.getIdentifier() + "/gamer/" + username);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodRequestException(new ErrorResponse(page));

        return page;
    }
}
