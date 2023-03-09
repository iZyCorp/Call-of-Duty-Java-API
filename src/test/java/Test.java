import io.github.izycorp.moonapi.components.*;
import io.github.izycorp.moonapi.exceptions.MoonViolationException;
import io.github.izycorp.moonapi.query.RequestManager;

public class Test {

    public static void main(String[] args) throws Exception {
        new Test();
    }

    public Test() throws Exception {
        //globalTesting();
        //ColdWarTesting();
        ModernWarfare2019Testing();
    }

    public void ColdWarTesting() throws MoonViolationException {
        RequestManager request = new RequestManager(new ListenerTest());
        ColdWar coldWar = new ColdWar(request);

        System.out.println("--- Leaderboards ---");
        coldWar.getLeaderboards(Platform.ALL, TimeFrame.ALLTIME, Gamemode.WARZONE, GameType.CORE, 1);
        coldWar.getLeaderboards(Platform.STEAM, TimeFrame.ALLTIME, Gamemode.WARZONE, GameType.CORE, 1);
        coldWar.getLeaderboards(Platform.XBOX, TimeFrame.ALLTIME, Gamemode.WARZONE, GameType.CORE, 1);
        coldWar.getLeaderboards(Platform.PLAYSTATION, TimeFrame.ALLTIME, Gamemode.WARZONE, GameType.CORE, 1);
        coldWar.getLeaderboards(Platform.BATTLE_NET, TimeFrame.ALLTIME, Gamemode.WARZONE, GameType.CORE, 1);
        coldWar.getLeaderboards(Platform.ACTIVISION, TimeFrame.ALLTIME, Gamemode.WARZONE, GameType.CORE, 1);

        System.out.println("--- Maps ---");
        coldWar.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.ALL);
        coldWar.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.STEAM);
        coldWar.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.XBOX);
        coldWar.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.PLAYSTATION);
        coldWar.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.BATTLE_NET);
        coldWar.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.ACTIVISION);
    }

    public void ModernWarfare2019Testing() throws MoonViolationException {
        RequestManager request = new RequestManager(new ListenerTest());
        ModernWarfare mw = new ModernWarfare(request, Opus.MW2019);

        System.out.println("--- Leaderboards ---");
        //mw.getLeaderboards(Platform.ALL, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        mw.getLeaderboards(Platform.STEAM, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        mw.getLeaderboards(Platform.XBOX, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        mw.getLeaderboards(Platform.PLAYSTATION, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        mw.getLeaderboards(Platform.BATTLE_NET, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        mw.getLeaderboards(Platform.ACTIVISION, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);

        System.out.println("--- Maps ---");
        mw.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.ALL);
        mw.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.STEAM);
        mw.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.XBOX);
        mw.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.PLAYSTATION);
        mw.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.BATTLE_NET);
        mw.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.ACTIVISION);

    }

    public void bo3Testing() {

    }
    public void globalTesting() throws MoonViolationException {
        ModernWarfare mw = new ModernWarfare(new RequestManager(new ListenerTest()), Opus.MW2019);
        System.out.println("Playstation");
        //mw.getAvailableMaps(Gamemode.MULTIPLAYER, Platform.PLAYSTATION);
        System.out.println("Xbox");
        mw.getLeaderboards(Platform.XBOX, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("Steam");
        mw.getLeaderboards(Platform.STEAM, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("Activision");
        mw.getLeaderboards(Platform.ACTIVISION, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("BattleNet");
        mw.getLeaderboards(Platform.BATTLE_NET, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("All");
        //mw.getLeaderboards(Platform.ALL, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
    }
}
