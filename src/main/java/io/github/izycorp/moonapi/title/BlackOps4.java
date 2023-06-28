package io.github.izycorp.moonapi.title;

import io.github.izycorp.moonapi.abstraction.Page;
import io.github.izycorp.moonapi.abstraction.TitleEndpoint;
import io.github.izycorp.moonapi.components.*;
import io.github.izycorp.moonapi.exceptions.MoonViolationException;
import io.github.izycorp.moonapi.query.RequestManager;

public class BlackOps4 extends TitleEndpoint {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public BlackOps4(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username, String ssoToken) throws MoonViolationException {
        return super.getUserProfile(Opus.BO4, mode, platform, username, ssoToken);
    }

    public Page getUserMatches(Gamemode gamemode, String username, int limit, int startTimeStamp, int endTimestamp, String ssoToken) throws MoonViolationException {
        return super.getUserMatches(Opus.BO4, gamemode, username, limit, startTimeStamp, endTimestamp, ssoToken);
    }

    public Page getLeaderboard(Platform platform, TimeFrame timeFrame, Gamemode gamemode, GameType gameType, int page) throws MoonViolationException {
        return super.getLeaderboards(Opus.BO4, platform, timeFrame, gamemode, gameType, page);
    }

    public Page getAvailableMaps(Gamemode mode, Platform platform) throws MoonViolationException {
        return super.getAvailableMaps(Opus.BO4, mode, platform);
    }

    public Page getPlayerLeaderboard(Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws MoonViolationException {
        return super.getPlayerLeaderboard(Opus.BO4, mode, type, platform, timeFrame, username);
    }
}
