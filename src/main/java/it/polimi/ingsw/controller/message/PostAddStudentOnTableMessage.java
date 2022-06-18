package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.client.gui.guiControllers.BoardController;
import it.polimi.ingsw.model.Game;

public class PostAddStudentOnTableMessage extends GameMessage {

    public PostAddStudentOnTableMessage(Game model, int playerId) {
        super(model, playerId);
    }

    @Override
    public void renderWhatNeeded(BoardController controller) {
        if (getPlayerOnTurn() == getPlayerId())
            controller.displayMySchoolboard();
        else
            controller.displayEnemySchoolboard();
        controller.showGameMessage();
    }
}
