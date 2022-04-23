package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public abstract class PlayerMessage implements Serializable {

    private int playerId;

    public PlayerMessage(int playerId) {
        this.playerId = playerId;
    }

    public abstract void performMove(Game game);

    public int getPlayerId() {
        return playerId;
    }
}