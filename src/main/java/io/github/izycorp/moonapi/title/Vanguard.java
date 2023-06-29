package io.github.izycorp.moonapi.title;

import io.github.izycorp.moonapi.abstraction.Page;
import io.github.izycorp.moonapi.abstraction.TitleEndpoint;
import io.github.izycorp.moonapi.components.*;
import io.github.izycorp.moonapi.exceptions.MoonViolationException;
import io.github.izycorp.moonapi.query.RequestManager;

public class Vanguard extends TitleEndpoint {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public Vanguard(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username, String ssoToken) throws MoonViolationException {
        return super.getUserProfile(Opus.VANGUARD, mode, platform, username, ssoToken);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp, String ssoToken) throws MoonViolationException {
        return super.getUserMatches(Opus.VANGUARD, gamemode, platform, username, limit, startTimeStamp, endTimestamp, ssoToken);
    }

    public Page getAvailableMaps(Gamemode mode, Platform platform) throws MoonViolationException {
        return super.getAvailableMaps(Opus.VANGUARD, mode, platform);
    }

    public Page getLootSeason(Platform platform, String language, int seasonNumber) throws MoonViolationException {
        return super.getLootSeason(Opus.VANGUARD, platform, language, seasonNumber);
    }
}
