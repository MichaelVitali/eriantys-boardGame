package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.client.gui.guiControllers.BoardController;
import it.polimi.ingsw.model.Game;

public class PostGetStudentsFromCloudsMessage extends GameMessage {

    public PostGetStudentsFromCloudsMessage(Game model, int playerId) {
        super(model, playerId);
    }

    @Override
    public void renderWhatNeeded(BoardController controller) {
        if (getPlayerOnTurn() == getPlayerId())
            controller.displayMySchoolboard();
        else
            controller.displayEnemySchoolboard();
        controller.showGameMessage();
        controller.displayClouds();
    }
}

