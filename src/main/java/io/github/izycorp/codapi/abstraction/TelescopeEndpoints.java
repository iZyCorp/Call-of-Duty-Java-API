package io.github.izycorp.codapi.abstraction;

import io.github.izycorp.codapi.components.TelescopeOpus;
import io.github.izycorp.codapi.exceptions.ErrorResponse;
import io.github.izycorp.codapi.exceptions.CodServerException;
import io.github.izycorp.codapi.query.RequestManager;

public class TelescopeEndpoints {

    /**
     * RequestManager Object used to send requests to the API
     */
    protected final RequestManager request;

    public TelescopeEndpoints(RequestManager request) {
        this.request = request;
    }

    protected Page getLifetime(final TelescopeOpus opus, final String unoId) throws CodServerException {
        final String rawResponseBody = request.sendTelescopeRequest("cr/v1/title/" + opus.getIdentifier() + "/lifetime?language=english&unoId=" + unoId);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodServerException(new ErrorResponse(page));

        return page;
    }

    protected Page getUserMatches(final TelescopeOpus opus, final String unoId) throws CodServerException {
        final String rawResponseBody = request.sendTelescopeRequest("cr/v1/title/" + opus.getIdentifier() + "/matches?language=english&unoId=" + unoId);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodServerException(new ErrorResponse(page));

        return page;
    }

    protected Page getMatch(final TelescopeOpus opus, final String unoId, final String matchId) throws CodServerException {
        final String rawResponseBody = request.sendTelescopeRequest("cr/v1/title/" + opus.getIdentifier() + "/match/" + matchId  + "?language=english&unoId=" + unoId);
        final Page page = new Page(rawResponseBody);

        if (page.getStatus() == RequestStatus.ERROR) throw new CodServerException(new ErrorResponse(page));

        return page;
    }
}
