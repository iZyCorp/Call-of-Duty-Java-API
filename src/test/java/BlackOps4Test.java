import components.TestListener;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.RequestStatus;
import io.github.izycorp.codapi.components.GameType;
import io.github.izycorp.codapi.components.Gamemode;
import io.github.izycorp.codapi.components.Platform;
import io.github.izycorp.codapi.components.TimeFrame;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;
import io.github.izycorp.codapi.title.BlackOps4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackOps4Test {

    private BlackOps4 blackOps4;
    private Dotenv dotenv;

    @BeforeEach
    void setUp() {
        dotenv = Dotenv.load();
        final RequestManager requestManager = new RequestManager(new TestListener());
        requestManager.authenticate(dotenv.get("SSO_TOKEN"));
        blackOps4 = new BlackOps4(requestManager);
    }

    @Test
    void testGetUserProfile() throws CodRequestException {
        final Page page = blackOps4.getUserProfile(
                Gamemode.MULTIPLAYER,
                Platform.PLAYSTATION,
                dotenv.get("ACT_USERNAME"),
                dotenv.get("SSO_TOKEN")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetUserMatches() throws CodRequestException {
        final Page page = blackOps4.getUserMatches(
                Gamemode.MULTIPLAYER,
                Platform.PLAYSTATION,
                dotenv.get("ACT_USERNAME"),
                10,
                0,
                0,
                dotenv.get("SSO_TOKEN")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetLeaderboard() throws CodRequestException {
        final Page page = blackOps4.getLeaderboard(
                Platform.PLAYSTATION,
                TimeFrame.ALLTIME,
                Gamemode.CAREER,
                GameType.CORE,
                1
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetPlayerLeaderboard() throws CodRequestException {
        final Page page = blackOps4.getPlayerLeaderboard(
                Gamemode.CAREER,
                GameType.CORE,
                Platform.PLAYSTATION,
                TimeFrame.ALLTIME,
                dotenv.get("ACT_USERNAME")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }
}