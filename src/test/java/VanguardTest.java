import components.TestListener;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.izycorp.codapi.abstraction.Page;
import io.github.izycorp.codapi.abstraction.RequestStatus;
import io.github.izycorp.codapi.components.Gamemode;
import io.github.izycorp.codapi.components.Platform;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import io.github.izycorp.codapi.query.RequestManager;
import io.github.izycorp.codapi.title.Vanguard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VanguardTest {

    private Vanguard vanguard;
    private Dotenv dotenv;

    @BeforeEach
    void setUp() {
        dotenv = Dotenv.load();
        final RequestManager requestManager = new RequestManager(new TestListener());
        requestManager.authenticate(dotenv.get("SSO_TOKEN"));
        vanguard = new Vanguard(requestManager);
    }

    @Test
    void testGetUserProfile() throws CodRequestException {
        final Page page = vanguard.getUserProfile(
                Gamemode.MULTIPLAYER,
                Platform.BATTLE_NET,
                dotenv.get("BATTLE_FULL_ID_ENCODED")
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }

    @Test
    void testGetUserMatches() throws CodRequestException {
        final Page page = vanguard.getUserMatches(
                Gamemode.MULTIPLAYER,
                Platform.BATTLE_NET,
                dotenv.get("BATTLE_FULL_ID_ENCODED"),
                10,
                0,
                0
        );
        assert page.getStatus() == RequestStatus.SUCCESS;
    }
}