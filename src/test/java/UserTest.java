import components.TestListener;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.RequestStatus;
import io.github.izycorp.codapi.abstraction.User;
import io.github.izycorp.codapi.components.FriendAction;
import io.github.izycorp.codapi.components.Platform;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest {

    private User user;
    private Dotenv dotenv;

    @BeforeEach
    void setUp() {
        dotenv = Dotenv.load();
        final RequestManager requestManager = new RequestManager(new TestListener());
        requestManager.authenticate(dotenv.get("SSO_TOKEN"));
        user = new User(requestManager);
    }

    @Test
    public void testSearchPlayer() throws CodRequestException {
        final Page result = user.searchPlayer("ZRK", Platform.PLAYSTATION);
        Assertions.assertEquals(RequestStatus.SUCCESS, result.getStatus());
    }

    @Test
    public void testGetIdentities() throws CodRequestException {
        final Page result = user.getIdentities(dotenv.get("ACT_ID"));
        Assertions.assertEquals(RequestStatus.SUCCESS, result.getStatus());
        Assertions.assertNotNull(result.getData().get("titleIdentities"));
    }

    @Test
    public void testGetFriends() throws CodRequestException {
        final Page result = user.getFriends();
        Assertions.assertEquals(RequestStatus.SUCCESS, result.getStatus());
        Assertions.assertNotNull(result.getData().get("uno"));
    }

    @Test
    public void testPerformFriendAction() throws CodRequestException {
        final Page result = user.performFriendAction(FriendAction.UNBLOCK, Platform.ALL, Platform.ALL.getLookupType(), dotenv.get("ACT_USERNAME"), dotenv.get("SSO_TOKEN"));
        Assertions.assertEquals(RequestStatus.SUCCESS, result.getStatus());
    }

    @Test
    public void testGetUserInfo() throws CodRequestException {
        final Page result = user.getUserInfo(dotenv.get("SSO_TOKEN"));
        Assertions.assertEquals(RequestStatus.SUCCESS, result.getStatus());
    }

    @Test
    public void testGetPlatforms() throws CodRequestException {
        final Page result = user.getPlatforms(Platform.UNO, dotenv.get("BATTLE_FULL_ID_ENCODED"));
        Assertions.assertEquals(RequestStatus.SUCCESS, result.getStatus());
        Assertions.assertNotNull(result.getData().get("battle"));
    }
}
