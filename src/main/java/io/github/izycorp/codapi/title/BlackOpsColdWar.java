package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TitleEndpoint;
import io.github.izycorp.codapi.components.*;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;

public class BlackOpsColdWar extends TitleEndpoint {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public BlackOpsColdWar(RequestManager request) {
        super(request);
    }

    public Page getUserProfile(Gamemode mode, Platform platform, String username) throws CodRequestException {
        return super.getUserProfile(Opus.COLD_WAR, mode, platform, username);
    }

    public Page getUserMatches(Gamemode gamemode, Platform platform, String username, int limit, int startTimeStamp, int endTimestamp) throws CodRequestException {
        return super.getUserMatches(Opus.COLD_WAR, gamemode, platform, username, limit, startTimeStamp, endTimestamp);
    }

    public Page getAvailableMaps(Gamemode mode, Platform platform) throws CodRequestException {
        return super.getAvailableMaps(Opus.COLD_WAR, mode, platform);
    }
}
