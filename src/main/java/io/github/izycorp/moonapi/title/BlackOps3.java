package io.github.izycorp.moonapi.title;

import io.github.izycorp.moonapi.abstraction.Page;
import io.github.izycorp.moonapi.abstraction.TitleEndpoint;
import io.github.izycorp.moonapi.components.*;
import io.github.izycorp.moonapi.exceptions.MoonViolationException;
import io.github.izycorp.moonapi.query.RequestManager;

public class BlackOps3 extends TitleEndpoint {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public BlackOps3(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username, String ssoToken) throws MoonViolationException {
        return super.getUserProfile(Opus.BO3, mode, platform, username, ssoToken);
    }

    public Page getUserMatches(Gamemode gamemode, String username, int limit, int startTimeStamp, int endTimestamp, String ssoToken) throws MoonViolationException {
        return super.getUserMatches(Opus.BO3, gamemode, username, limit, startTimeStamp, endTimestamp, ssoToken);
    }

    public Page getLeaderboard(Platform platform, TimeFrame timeFrame, Gamemode gamemode, GameType gameType, int page) throws MoonViolationException {
        return super.getLeaderboards(Opus.BO3, platform, timeFrame, gamemode, gameType, page);
    }

    public Page getPlayerLeaderboard(Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws MoonViolationException {
        return super.getPlayerLeaderboard(Opus.BO3, mode, type, platform, timeFrame, username);
    }
}
