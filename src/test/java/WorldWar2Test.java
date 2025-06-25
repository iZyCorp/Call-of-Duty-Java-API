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
import io.github.izycorp.codapi.title.WorldWar2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorldWar2Test {

    private WorldWar2 worldWar2;
    private Dotenv dotenv;

    @BeforeEach
    void setUp() {
        dotenv = Dotenv.load();
        final RequestManager requestManager = new RequestManager(new TestListener());
        requestManager.authenticate(dotenv.get("SSO_TOKEN"));
        worldWar2 = new WorldWar2(requestManager);
    }

    @Test
    void testGetUserProfile() throws CodRequestException {
        final Page page = worldWar2.getUserProfile(
                Gamemode.MULTIPLAYER,
                Platform.STEAM,
                dotenv.get("ACT_USERNAME"),
                dotenv.get("SSO_TOKEN")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetUserMatches() throws CodRequestException {
        final Page page = worldWar2.getUserMatches(
                Gamemode.MULTIPLAYER,
                Platform.STEAM,
                dotenv.get("ACT_USERNAME"),
                10,
                0,
                0,
                dotenv.get("SSO_TOKEN")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetPlayerLeaderboard() throws CodRequestException {
        final Page page = worldWar2.getPlayerLeaderboard(
                Gamemode.CAREER,
                GameType.CORE,
                Platform.STEAM,
                TimeFrame.ALLTIME,
                dotenv.get("ACT_USERNAME")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }
}