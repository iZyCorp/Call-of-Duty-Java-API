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

    public Page getUserProfile(Gamemode mode, Platform platform, String username) throws MoonViolationException {
        return super.getUserProfile(Opus.BO3, mode, platform, username);
    }

    public Page getUserMatches(Gamemode gamemode, String username, int limit, int startTimeStamp, int endTimestamp) throws MoonViolationException {
        return super.getUserMatches(Opus.BO3, gamemode, username, limit, startTimeStamp, endTimestamp);
    }

    public Page getLeaderboard(Platform platform, TimeFrame timeFrame, Gamemode gamemode, GameType gameType, int page) throws MoonViolationException {
        return super.getLeaderboards(Opus.BO3, platform, timeFrame, gamemode, gameType, page);
    }
}
