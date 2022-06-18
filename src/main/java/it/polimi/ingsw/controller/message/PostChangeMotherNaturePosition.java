package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.client.gui.guiControllers.BoardController;
import it.polimi.ingsw.model.Game;

public class PostChangeMotherNaturePosition extends GameMessage {

    public PostChangeMotherNaturePosition(Game model, int playerId) {
        super(model, playerId);
    }

    @Override
    public void renderWhatNeeded(BoardController controller) {
        if (getPlayerOnTurn() == getPlayerId())
            controller.displayMyTowers();
        else
            controller.displayEnemyTowers();
        controller.displayIslands();
        controller.showGameMessage();
    }
}
