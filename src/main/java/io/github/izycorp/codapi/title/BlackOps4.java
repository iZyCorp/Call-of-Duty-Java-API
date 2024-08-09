package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TitleEndpoints;
import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.CodServerException;
import io.github.izycorp.codapi.query.RequestManager;

public class BlackOps4 extends TitleEndpoints {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public BlackOps4(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username, String ssoToken) throws CodServerException {
        return super.getUserProfile(Opus.BO4, mode, platform, username, ssoToken);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp, String ssoToken) throws CodServerException {
        return super.getUserMatches(Opus.BO4, gamemode, platform, username, limit, startTimeStamp, endTimestamp, ssoToken);
    }

    public Page getLeaderboard(Platform platform, TimeFrame timeFrame, Gamemode gamemode, GameType gameType, int page) throws CodServerException {
        return super.getLeaderboards(Opus.BO4, platform, timeFrame, gamemode, gameType, page);
    }

    public Page getAvailableMaps(Gamemode mode, Platform platform) throws CodServerException {
        return super.getAvailableMaps(Opus.BO4, mode, platform);
    }

    public Page getPlayerLeaderboard(Gamemode mode, GameType type, Platform platform, TimeFrame timeFrame, String username) throws CodServerException {
        return super.getPlayerLeaderboard(Opus.BO4, mode, type, platform, timeFrame, username);
    }
}
