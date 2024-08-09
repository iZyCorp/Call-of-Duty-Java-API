package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TitleEndpoints;
import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.MoonViolationException;
import io.github.izycorp.codapi.query.RequestManager;

public class BlackOpsColdWar extends TitleEndpoints {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public BlackOpsColdWar(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username, String ssoToken) throws MoonViolationException {
        return super.getUserProfile(Opus.COLD_WAR, mode, platform, username, ssoToken);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp, String ssoToken) throws MoonViolationException {
        return super.getUserMatches(Opus.COLD_WAR, gamemode, platform, username, limit, startTimeStamp, endTimestamp, ssoToken);
    }

    public Page getAvailableMaps(Gamemode mode, Platform platform) throws MoonViolationException {
        return super.getAvailableMaps(Opus.COLD_WAR, mode, platform);
    }
}
