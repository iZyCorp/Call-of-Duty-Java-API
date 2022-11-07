import fr.izy.moonapi.components.*;
import fr.izy.moonapi.events.EventHandler;
import fr.izy.moonapi.events.components.PreRequestEvent;
import fr.izy.moonapi.query.RequestManager;
import org.json.JSONObject;

public class Test {

    public static void main(String[] args) throws Exception {

        new Test();
    }

    public Test() throws Exception {
        ModernWarfare mw = new ModernWarfare(new RequestManager(), Opus.MW2019);
        System.out.println("Playstation");
        mw.getLeaderboards(Platform.PLAYSTATION, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("Xbox");
        mw.getLeaderboards(Platform.XBOX, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("Steam");
        mw.getLeaderboards(Platform.STEAM, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("Activision");
        mw.getLeaderboards(Platform.ACTIVISION, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("BattleNet");
        mw.getLeaderboards(Platform.BATTLE_NET, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
        System.out.println("All");
        mw.getLeaderboards(Platform.ALL, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, 1);
    }

    @EventHandler
    public void onPreRequestEvent(final PreRequestEvent event) {
        System.out.println("pre");
    }

    public void otherGun() throws InterruptedException {
        // Creating Mw object
        ModernWarfare mw = new ModernWarfare(new RequestManager(), Opus.MW2019);

        // For loop to get our 50 000 000 accounts KDA
        for (int pageIndex = 1; pageIndex < 500; pageIndex++) {

            // Passing a variable to a thread
            int finalPageIndex = pageIndex;

            // Pause the thread for 500 ms
            Thread.sleep(1000);

            // ASYNC thread
            Thread requestThread = new Thread(() -> {

                JSONObject leaderboard = null;

                // in case of an error retry
                while (leaderboard == null) {
                    try {
                        leaderboard = mw.getLeaderboards(Platform.BATTLE_NET, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.CORE, finalPageIndex);
                    } catch (Exception e) {
                        System.out.println("Error while getting leaderboard (page " + finalPageIndex + ") retrying...");
                    }
                }

                // Looping through the leaderboard and our 20 players
                for (int userIndex = 0; userIndex < 19; userIndex++) {
                    // retrieving the users kda
                    double kda = leaderboard.getJSONObject("data").getJSONArray("entries").getJSONObject(userIndex).getJSONObject("values").getDouble("kdRatio");
                    //System.out.println(kda);
                }

            });
            // Start the thread
            requestThread.start();

        }
    }


    /*public void testLeaderBoard() {
        moon.getLeaderBoards(ApiVersion.V2, Opus.BO3, Platform.PLAYSTATION, TimeFrame.ALLTIME, GameType.CORE, 1);
        moon.getLeaderBoards(ApiVersion.V2, Opus.INFINITE_WARFARE, Platform.PLAYSTATION, TimeFrame.ALLTIME, GameType.CORE,1); //
        moon.getLeaderBoards(ApiVersion.V2, Opus.WWII, Platform.PLAYSTATION, TimeFrame.ALLTIME, GameType.CORE,1);
        moon.getLeaderBoards(ApiVersion.V2, Opus.BO4, Platform.PLAYSTATION, TimeFrame.ALLTIME, GameType.CORE,1);
        moon.getLeaderBoards(ApiVersion.V2, Opus.MW2019, Platform.PLAYSTATION, TimeFrame.ALLTIME, GameType.CORE,1);
        moon.getLeaderBoards(ApiVersion.V2, Opus.COLD_WAR, Platform.ALL, TimeFrame.ALLTIME, GameType.CORE,2); //
        moon.getLeaderBoards(ApiVersion.V2, Opus.VANGUARD, Platform.ALL, TimeFrame.ALLTIME, GameType.CORE,1); //
    }

    public void testSearchPlayer() throws MoonViolationException {
        moon.searchPlayer(ApiVersion.V2,"iZyerax_", Platform.PLAYSTATION);
        moon.searchPlayer(ApiVersion.V2,"iZy", Platform.UNO);
        moon.searchPlayer(ApiVersion.V2,"iZy", Platform.ALL);
        moon.searchPlayer(ApiVersion.V2,"iZy", Platform.BATTLE_NET);
        moon.searchPlayer(ApiVersion.V2,"iZy", Platform.ACTIVISION);
        moon.searchPlayer(ApiVersion.V2,"iZy", Platform.STEAM);
        moon.searchPlayer(ApiVersion.V2,"Wolfy", Platform.XBOX);
    }

    private void testUserProfile() throws MoonViolationException {
        moon.getUserProfile(ApiVersion.V1, Opus.BO3, Gamemode.CAREER, Platform.PLAYSTATION, "iZyerax_");
        moon.getUserProfile(ApiVersion.V1,Opus.INFINITE_WARFARE, Gamemode.CAREER, Platform.PLAYSTATION, "DexterGaming"); //
        moon.getUserProfile(ApiVersion.V1,Opus.WWII, Gamemode.CAREER, Platform.PLAYSTATION, "iZyerax_");
        moon.getUserProfile(ApiVersion.V1,Opus.BO4, Gamemode.CAREER, Platform.PLAYSTATION, "iZyerax_"); //
        moon.getUserProfile(ApiVersion.V1,Opus.MW2019, Gamemode.CAREER, Platform.PLAYSTATION, "iZyerax_");
        moon.getUserProfile(ApiVersion.V1,Opus.COLD_WAR, Gamemode.CAREER, Platform.UNO, "iZyerax_"); //
        moon.getUserProfile(ApiVersion.V1,Opus.VANGUARD, Gamemode.CAREER, Platform.UNO, "iZyerax_"); //
    }*/
}
