package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.client.gui.guiControllers.BoardController;
import it.polimi.ingsw.model.Game;

public class PostPlayAssistantMessage extends GameMessage {

    public PostPlayAssistantMessage(Game model, int playerId) {
        super(model, playerId);
    }

    @Override
    public void renderWhatNeeded(BoardController controller) {
        controller.displayAssistants();
        controller.displayEnemyAssistant();
        //controller.displayMyAssistant();
        controller.displayMyEntrance();
        controller.displayEnemyEntrance();
        controller.displayClouds();
        controller.showGameMessage();
    }

}
