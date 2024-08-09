package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TelescopeEndpoints;
import io.github.izycorp.codapi.components.TelescopeOpus;
import io.github.izycorp.codapi.exceptions.CodServerException;
import io.github.izycorp.codapi.query.RequestManager;

public class ModernWarfare2022 extends TelescopeEndpoints {

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public ModernWarfare2022(RequestManager request) {
        super(request);
    }

    @Override
    protected Page getLifetime(TelescopeOpus opus, String unoId) throws CodServerException {
        return super.getLifetime(opus, unoId);
    }

    @Override
    protected Page getMatch(TelescopeOpus opus, String unoId, String matchId) throws CodServerException {
        return super.getMatch(opus, unoId, matchId);
    }

    @Override
    public Page getUserMatches(final TelescopeOpus opus, final String unoId) throws CodServerException {
        return super.getUserMatches(opus, unoId);
    }

}
