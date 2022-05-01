package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameTable;

import java.io.Serializable;

public class DisplayedBoard implements Serializable {
    private static final long serialVersionUID = 100L;
    private int state;
    private int playerId;

    public DisplayedBoard(Game model) {
        state = model.getRound().getRoundState();
    }

    public void printDefaultOnCli() {

    }
}
