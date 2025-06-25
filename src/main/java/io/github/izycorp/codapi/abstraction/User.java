package io.github.izycorp.codapi.abstraction;

import io.github.izycorp.codapi.components.ApiVersion;
import io.github.izycorp.codapi.components.FriendAction;
import io.github.izycorp.codapi.components.Platform;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;

public class User {

    private final RequestManager request;

    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public User(final RequestManager request) {
        this.request = request;
    }

    /**
     * This method is used to search for a player on the wanted platform
     *
     * @param playerName - The name of the player you want to search
     * @param platform   - The platform of the player you want to search see {@link Platform}
     * @return A valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PUBLIC)
    public Page searchPlayer(final String playerName, final Platform platform) throws CodRequestException {
        final String responseBody = request.sendRequestWithAuthentication("crm/cod/" + ApiVersion.V2.getIdentifier() + "/platform/" + platform.getIdentifier() + "/username/" + playerName + "/search");
        return new Page(responseBody);
    }

    /**
     * This method is used to get the identities of a user
     *
     * @param unoId    - The unoId of the user you want to get the identities (name)
     * @return a valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PRIVATE)
    public Page getIdentities(final String unoId) throws CodRequestException {
        final String responseBody = request.sendRequestWithAuthentication("crm/cod/" + ApiVersion.V2.getIdentifier() + "/identities/" + unoId);
        return new Page(responseBody);
    }

    /**
     * This method is used to return every friend of a Call of Duty account,
     * you need a SSOToken of the account to fetch this data
     *
     * @return a valid JSONObject
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    public Page getFriends() throws CodRequestException {
        final String responseBody = request.sendRequestWithAuthentication("codfriends/v1/compendium/");
        return new Page(responseBody);
    }

    /**
     * This method is used to perform an action on a friend of a Call of Duty account.
     *
     * @param friendAction - The action you want to perform see {@link io.github.izycorp.codapi.components.FriendAction}
     * @param platform     - The platform of the player you want to search see {@link Platform}
     * @param lookupType   - The lookupType of the player you want to perform the action see {@link Platform#getLookupType()}
     * @param gamerTag     - The gamerTag of the targeted friend
     * @param ssoToken     - The SSOToken of the account where you want to perform the action
     * @throws CodRequestException - If the request is not valid
     */
    @Route(requestRoute = RequestRoute.PROTECTED)
    public Page performFriendAction(final FriendAction friendAction, final Platform platform, final String lookupType, final String gamerTag, final String ssoToken) throws CodRequestException {
        final String responseBody = request.sendRequestWithAuthentication("codfriends/v1/" + friendAction.name().toLowerCase() + "/" + platform.getIdentifier() + "/" + lookupType + "/" + gamerTag);
        return new Page(responseBody);
    }

    /**
     * Retrieve game and platform identification for the authenticated client
     * @param ssoToken The SSOToken of the account where you want to perform the action
     */
    @Route(requestRoute = RequestRoute.PRIVATE)
    public Page getUserInfo(final String ssoToken) throws CodRequestException {
        final String responseBody = request.sendRequestWithAuthentication(RequestManager.PROFILE_BASE_URL, "cod/userInfo/" + ssoToken);
        return new Page(responseBody);
    }

    /**
     * Retrieve the user's username for each platform
     */
    @Route(requestRoute = RequestRoute.PRIVATE)
    public Page getPlatforms(final Platform platform, final String username) throws CodRequestException {
        final String responseBody = request.sendRequestWithAuthentication("crm/cod/" + ApiVersion.V2.getIdentifier() + "/accounts/platform/" + platform.getIdentifier() + "/gamer/" + username);
        return new Page(responseBody);
    }


}
