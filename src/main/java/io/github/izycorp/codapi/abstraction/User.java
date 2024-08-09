package io.github.izycorp.codapi.abstraction;

import io.github.izycorp.codapi.components.ApiVersion;
import io.github.izycorp.codapi.components.FriendAction;
import io.github.izycorp.codapi.components.Platform;
import io.github.izycorp.codapi.exceptions.MoonViolationException;
import io.github.izycorp.codapi.query.RequestManager;
import org.json.JSONObject;

public class User {



    private final RequestManager request;
    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public User(RequestManager request) {
        this.request = request;
    }

    /**
     * This method is used to search a player on the wanted platform
     * @param playerName - The name of the player you want to search
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @return A valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    public JSONObject searchPlayer(String playerName, Platform platform, String ssoToken) throws MoonViolationException {
        final String responseBody = request.sendRequestWithAuthentication("crm/cod/"+ ApiVersion.V2.getIdentifier() + "/platform/" + platform.getIdentifier() + "/username/" + playerName + "/search", request.authenticate(ssoToken), "POST");
        return new JSONObject(responseBody);
    }

    /**
     * This method is used to get the identities of a user
     * @param unoId - The unoId of the user you want to get the identities (name)
     * @param ssoToken - any SSOToken
     * @return a valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    public JSONObject getIdentities(String unoId, String ssoToken) throws MoonViolationException {
        final String responseBody = request.sendRequestWithAuthentication("crm/cod/"+ ApiVersion.V2.getIdentifier() + "/identities/" + unoId, request.authenticate(ssoToken), "POST");
        return new JSONObject(responseBody);
    }

    /**
     * This method is used to return every friend of a Call of Duty account,
     * you need a SSOToken of the account to fetch this data
     * @param ssoToken - any SSOToken
     * @return a valid JSONObject
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    public JSONObject getFriends(String ssoToken) throws MoonViolationException {
        final String responseBody = request.sendRequestWithAuthentication("codfriends/v1/compendium/", request.authenticate(ssoToken), "POST");
        return new JSONObject(responseBody);
    }

    /**
     * This method is used to perform an action on a friend of a Call of Duty account,
     * @param friendAction - The action you want to perform see {@link io.github.izycorp.codapi.components.FriendAction}
     * @param platform - The platform of the player you want to search see {@link Platform}
     * @param lookupType - The lookupType of the player you want to perform the action see {@link Platform#getLookupType()}
     * @param gamerTag - The gamerTag of the targeted friend
     * @param ssoToken - The SSOToken of the account where you want to perform the action
     * @throws MoonViolationException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    public JSONObject performFriendAction(FriendAction friendAction, Platform platform, String lookupType, String gamerTag, String ssoToken) throws MoonViolationException {
        final String responseBody = request.sendRequestWithAuthentication("codfriends/v1/" + friendAction.name().toLowerCase() + "/" + platform.getIdentifier() + "/" + lookupType + "/" + gamerTag, request.authenticate(ssoToken), "POST");
        return new JSONObject(responseBody);
    }


}
