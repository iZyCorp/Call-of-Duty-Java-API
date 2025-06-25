package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TitleEndpoint;
import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;

public class InfiniteWarfare extends TitleEndpoint {
    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public InfiniteWarfare(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username) throws CodRequestException {
        return super.getUserProfile(Opus.INFINITE_WARFARE, mode, platform, username);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp) throws CodRequestException {
        return super.getUserMatches(Opus.INFINITE_WARFARE, gamemode, platform, username, limit, startTimeStamp, endTimestamp);
    }

    public Page getLeaderboard(Platform platform, TimeFrame timeFrame, Gamemode gamemode, GameType gameType, int page) throws CodRequestException {
        return super.getLeaderboards(Opus.INFINITE_WARFARE, platform, timeFrame, gamemode, gameType, page);
    }
}
