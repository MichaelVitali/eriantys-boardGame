package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.client.gui.guiControllers.BoardController;
import it.polimi.ingsw.model.Game;

public class PostGetStudentsFromCloudsMessage extends GameMessage {

    public PostGetStudentsFromCloudsMessage(Game model, int playerId) {
        super(model, playerId);
    }

    @Override
    public void renderWhatNeeded(BoardController controller) {
        controller.displayMyEntrance();
        controller.displayEnemyEntrance();
        controller.displayClouds();
        controller.displayAssistants();
        controller.showGameMessage();
    }
}

