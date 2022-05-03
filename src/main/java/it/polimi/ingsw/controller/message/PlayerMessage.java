package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public abstract class PlayerMessage implements Serializable {
    private static final long serialVersionUID = 0L;
    private int playerId;

    public PlayerMessage(int playerId) {
        this.playerId = playerId;
    }

    public abstract void performMove(Game game);

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}