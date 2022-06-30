package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class ChangeMotherNaturePositionMessage extends PlayerMessage implements Serializable {
    private static final long serialVersionUID = 4L;
    private int islandIndex;

    public ChangeMotherNaturePositionMessage(int playerId, int islandIndex) {
        super(playerId);
        this.islandIndex = islandIndex;
    }

    /**
     * Calls the function on round to change mother nature position on the chosen Island
     * @param game
     */
    @Override
    public void performMove(Game game) { game.getRound().changeMotherNaturePosition(getPlayerId(), islandIndex); }

}
