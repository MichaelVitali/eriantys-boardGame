package it.polimi.ingsw.model;

import java.io.Serializable;

public class DisplayedBoard implements Serializable {
    private static final long serialVersionUID = 100L;
    private int state;
    private String playerMessage;

    public DisplayedBoard(Game model, int playerId) {
        state = model.getRound().getRoundState();
        playerMessage = model.getPlayer(playerId).getErrorMessage();
    }

    public void printDefaultOnCli() {
        System.out.println(playerMessage);
    }
}
