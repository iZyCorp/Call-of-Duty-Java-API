package io.github.izycorp.codapi.title;

import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.TelescopeEndpoints;
import io.github.izycorp.codapi.components.TelescopeOpus;
import io.github.izycorp.codapi.exceptions.CodServerException;
import io.github.izycorp.codapi.query.RequestManager;

public class WarzoneMobile extends TelescopeEndpoints {

    public WarzoneMobile(RequestManager request) {
        super(request);
    }

    @Override
    protected Page getLifetime(TelescopeOpus opus, String unoId) throws CodServerException {
        return super.getLifetime(opus, unoId);
    }

    @Override
    protected Page getUserMatches(TelescopeOpus opus, String unoId) throws CodServerException {
        return super.getUserMatches(opus, unoId);
    }

    @Override
    protected Page getMatch(TelescopeOpus opus, String unoId, String matchId) throws CodServerException {
        return super.getMatch(opus, unoId, matchId);
    }
}
