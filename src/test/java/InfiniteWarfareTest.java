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
import io.github.izycorp.codapi.title.InfiniteWarfare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InfiniteWarfareTest {

    private InfiniteWarfare infiniteWarfare;
    private Dotenv dotenv;

    @BeforeEach
    void setUp() {
        dotenv = Dotenv.load();
        final RequestManager requestManager = new RequestManager(new TestListener());
        requestManager.authenticate(dotenv.get("SSO_TOKEN"));
        infiniteWarfare = new InfiniteWarfare(requestManager);
    }

    @Test
    void testGetUserProfile() throws CodRequestException {
        final Page page = infiniteWarfare.getUserProfile(
                Gamemode.MULTIPLAYER,
                Platform.STEAM,
                dotenv.get("ACT_USERNAME")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetUserMatches() throws CodRequestException {
        final Page page = infiniteWarfare.getUserMatches(
                Gamemode.MULTIPLAYER,
                Platform.STEAM,
                dotenv.get("ACT_USERNAME"),
                10,
                0,
                0
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetLeaderboard() throws CodRequestException {
        final Page page = infiniteWarfare.getLeaderboard(
                Platform.STEAM,
                TimeFrame.ALLTIME,
                Gamemode.CAREER,
                GameType.CORE,
                1
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }
}