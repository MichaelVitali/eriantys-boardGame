package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class ChangeMotherNaturePositionMessage implements Serializable, PlayerMessage {
    private int playerId;
    private int islandIndex;

    public ChangeMotherNaturePositionMessage(int playerId, int islandIndex) {
        this.playerId = playerId;
        this.islandIndex = islandIndex;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public void performMove(Game game) { game.getRound().changeMotherNaturePosition(playerId, islandIndex); }
}
