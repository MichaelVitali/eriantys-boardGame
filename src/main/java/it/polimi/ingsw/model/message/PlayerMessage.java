package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

public interface PlayerMessage {

    public void performMove(Game game);

    public int getPlayerId();
}