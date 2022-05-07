package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameTable;

import java.io.Serializable;

public class DisplayedBoard implements Serializable {
    private static final long serialVersionUID = 100L;
    private int state;

    private int playerId;
    private String playerMessage;
    private Game model;

    public DisplayedBoard(Game model, int playerId) {
        this.model = model;
        state = model.getRound().getRoundState();
        this.playerId = playerId;
        playerMessage = model.getPlayer(playerId).getErrorMessage();
    }

    public int getState() {
        return state;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerMessage() {
        return playerMessage;
    }

    public Game getModel() { return model;}

    public void printDefaultOnCli() {
        System.out.println(playerMessage);System.out.println("Ciao bello");
    }
}
