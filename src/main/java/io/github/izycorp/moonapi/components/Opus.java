package io.github.izycorp.moonapi.components;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 * <h2>Opus</h2>
 * <p>This is an enumeration that contains all the existing possibilities for the Opus(Game) parameter in a HTTP request to call of duty server</p>
 * <br>
 * <p>Local identifier field must be use in http request as part of a valid URL</p>
 * <br>
 * <p>If you want to target Warzone, please be aware that Activision consider Warzone as a gamemode not as an Opus. </p>
 * @see Gamemode#WARZONE
 *
 */
public enum Opus {

    BO3("bo3",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.ZOMBIES, Gamemode.CAREER},
            new Platform[]{Platform.STEAM, Platform.XBOX, Platform.PLAYSTATION}),
    INFINITE_WARFARE("iw",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.ZOMBIES, Gamemode.CAREER},
            new Platform[]{Platform.STEAM, Platform.XBOX, Platform.PLAYSTATION}),
    WWII("wwii",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.ZOMBIES, Gamemode.CAREER},
            new Platform[]{Platform.STEAM, Platform.XBOX, Platform.PLAYSTATION}),
    BO4("bo4",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.ZOMBIES, Gamemode.CAREER},
            new Platform[]{Platform.XBOX, Platform.BATTLE_NET, Platform.PLAYSTATION}),
    MW2019("mw",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.WARZONE, Gamemode.CAREER},
            new Platform[]{Platform.XBOX, Platform.BATTLE_NET, Platform.PLAYSTATION}),

    COLD_WAR("cw",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.ZOMBIES, Gamemode.CAREER},
            new Platform[]{Platform.ALL, Platform.UNO, Platform.XBOX, Platform.BATTLE_NET, Platform.PLAYSTATION, Platform.STEAM}),

    VANGUARD("vg",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.ZOMBIES, Gamemode.CAREER},
            new Platform[]{Platform.ALL, Platform.UNO, Platform.XBOX, Platform.BATTLE_NET, Platform.PLAYSTATION}),

    MW2("mw2",
            new Gamemode[]{Gamemode.MULTIPLAYER, Gamemode.CAREER},
            new Platform[]{Platform.STEAM, Platform.XBOX, Platform.PLAYSTATION, Platform.UNO, Platform.ACTIVISION, Platform.BATTLE_NET});

    /**
     * Identifier is the valid string value of an opus in an HTTP request to Call Of Duty server
     */
    private final String identifier;

    private final Gamemode[] compatibleGamemodes;

    private final Platform[] compatiblePlatforms;

    Opus(String identifier, Gamemode[] compatibleGamemodes, Platform[] compatiblePlatforms) {
        this.identifier = identifier;
        this.compatibleGamemodes = compatibleGamemodes;
        this.compatiblePlatforms = compatiblePlatforms;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Gamemode[] getCompatibleGamemodes() {
        return compatibleGamemodes;
    }

    public Platform[] getCompatiblePlatforms() {
        return compatiblePlatforms;
    }
}
