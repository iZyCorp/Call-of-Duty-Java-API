package io.github.izycorp.codapi.abstraction;

import io.github.izycorp.codapi.components.Opus;
import io.github.izycorp.codapi.exceptions.ErrorResponse;
import io.github.izycorp.codapi.exceptions.MoonViolationException;
import io.github.izycorp.codapi.query.RequestManager;

public class TelescopeEndpoints {

    /**
     * RequestManager Object used to send requests to the API
     */
    protected final RequestManager request;

    public TelescopeEndpoints(RequestManager request) {
        this.request = request;
    }

    protected Page getLifetime(Opus opus, String unoId) throws MoonViolationException {
        final String rawResponseBody = request.sendTelescopeRequest("cr/v1/title/" + opus.getIdentifier() + "/lifetime?language=english&unoId=" + unoId);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    protected Page getUserMatches(Opus opus, String unoId) throws MoonViolationException {
        final String rawResponseBody = request.sendTelescopeRequest("cr/v1/title/" + opus.getIdentifier() + "/matches?language=english&unoId=" + unoId);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }

    protected Page getMatch(Opus opus, String unoId, String matchId) throws MoonViolationException {
        final String rawResponseBody = request.sendTelescopeRequest("cr/v1/title/" + opus.getIdentifier() + "/match/" + matchId  + "?language=english&unoId=" + unoId);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new MoonViolationException(new ErrorResponse(page));

        return page;
    }
}
