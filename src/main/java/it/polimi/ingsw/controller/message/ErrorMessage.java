package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.client.gui.guiControllers.BoardController;
import it.polimi.ingsw.model.Game;

public class ErrorMessage extends GameMessage {

    public ErrorMessage(Game model, int playerId) {
        super(model, playerId);
    }
    @Override
    public void renderWhatNeeded(BoardController controller) {
        controller.showGameMessage();
    }
}
