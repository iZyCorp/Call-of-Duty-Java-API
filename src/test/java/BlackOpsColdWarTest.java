import components.TestListener;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.RequestStatus;
import io.github.izycorp.codapi.components.Gamemode;
import io.github.izycorp.codapi.components.Platform;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;
import io.github.izycorp.codapi.title.BlackOpsColdWar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackOpsColdWarTest {

    private BlackOpsColdWar coldWar;
    private Dotenv dotenv;

    @BeforeEach
    void setUp() {
        dotenv = Dotenv.load();
        final RequestManager requestManager = new RequestManager(new TestListener());
        requestManager.authenticate(dotenv.get("SSO_TOKEN"));
        coldWar = new BlackOpsColdWar(requestManager);
    }

    @Test
    void testGetUserProfile() throws CodRequestException {
        final Page page = coldWar.getUserProfile(
                Gamemode.MULTIPLAYER,
                Platform.PLAYSTATION,
                dotenv.get("ACT_USERNAME"),
                dotenv.get("SSO_TOKEN")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetUserMatches() throws CodRequestException {
        final Page page = coldWar.getUserMatches(
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
}