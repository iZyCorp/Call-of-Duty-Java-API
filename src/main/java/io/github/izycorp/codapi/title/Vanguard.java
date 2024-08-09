package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TitleEndpoints;
import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.CodServerException;
import io.github.izycorp.codapi.query.RequestManager;

public class Vanguard extends TitleEndpoints {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public Vanguard(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username, String ssoToken) throws CodServerException {
        return super.getUserProfile(Opus.VANGUARD, mode, platform, username, ssoToken);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp, String ssoToken) throws CodServerException {
        return super.getUserMatches(Opus.VANGUARD, gamemode, platform, username, limit, startTimeStamp, endTimestamp, ssoToken);
    }

    public Page getAvailableMaps(Gamemode mode, Platform platform) throws CodServerException {
        return super.getAvailableMaps(Opus.VANGUARD, mode, platform);
    }

    public Page getLootSeason(Platform platform, String language, int seasonNumber) throws CodServerException {
        return super.getLootSeason(Opus.VANGUARD, platform, language, seasonNumber);
    }
}
