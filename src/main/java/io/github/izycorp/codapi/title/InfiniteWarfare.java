package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TitleEndpoints;
import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.CodServerException;
import io.github.izycorp.codapi.query.RequestManager;

public class InfiniteWarfare extends TitleEndpoints {
    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public InfiniteWarfare(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username, String ssoToken) throws CodServerException {
        return super.getUserProfile(Opus.INFINITE_WARFARE, mode, platform, username, ssoToken);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp, String ssoToken) throws CodServerException {
        return super.getUserMatches(Opus.INFINITE_WARFARE, gamemode, platform, username, limit, startTimeStamp, endTimestamp, ssoToken);
    }

    public Page getLeaderboard(Platform platform, TimeFrame timeFrame, Gamemode gamemode, GameType gameType, int page) throws CodServerException {
        return super.getLeaderboards(Opus.INFINITE_WARFARE, platform, timeFrame, gamemode, gameType, page);
    }
}
