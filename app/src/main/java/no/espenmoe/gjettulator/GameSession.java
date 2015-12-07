package no.espenmoe.gjettulator;

/**
 * Created by Espen on 29.11.2015.
 */
public class GameSession {

    private String playerName;
    private String playerTitle;
    private long millisecondsUsed;

    public GameSession(String name, String title, long time) {
        playerName          = name;
        playerTitle         = title;
        millisecondsUsed    = time;
    }

    public void setPlayerName(String pn) {
        playerName = pn;
    }

    public String getPlayername() {
        return playerName;
    }

    public void setPlayerTitle(String pt) {
        playerTitle = pt;
    }

    public String getPlayerTitle() {
        return playerTitle;
    }

    public void setMillisecondsUsed(int ms) {
        millisecondsUsed = ms;
    }

    public long getMillisecondsUsed() {
        return millisecondsUsed;
    }
}
