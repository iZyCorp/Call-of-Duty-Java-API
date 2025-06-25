package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TitleEndpoint;
import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;

public class BlackOps4 extends TitleEndpoint {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public BlackOps4(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username) throws CodRequestException {
        return super.getUserProfile(Opus.BO4, mode, platform, username);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp) throws CodRequestException {
        return super.getUserMatches(Opus.BO4, gamemode, platform, username, limit, startTimeStamp, endTimestamp);
    }

    public Page getLeaderboard(Platform platform, TimeFrame timeFrame, Gamemode gamemode, GameType gameType, int page) throws CodRequestException {
        return super.getLeaderboards(Opus.BO4, platform, timeFrame, gamemode, gameType, page);
    }

    public Page getAvailableMaps(Gamemode mode, Platform platform) throws CodRequestException {
        return super.getAvailableMaps(Opus.BO4, mode, platform);
    }

    public Page getPlayerLeaderboard(Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws CodRequestException {
        return super.getPlayerLeaderboard(Opus.BO4, mode, type, platform, timeFrame, username);
    }
}
