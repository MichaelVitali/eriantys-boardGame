package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.client.gui.guiControllers.BoardController;
import it.polimi.ingsw.model.Game;

public class PostAddStudentOnTableMessage extends GameMessage {

    /**
     * message which permits changing the displayed situation after adding student on tables
     * @param model
     * @param playerId
     */
    public PostAddStudentOnTableMessage(Game model, int playerId) {
        super(model, playerId);
    }

    /**
     * displays the updated entrances ,islands, professor and shows the next game message
     * @param controller
     */
    @Override
    public void renderWhatNeeded(BoardController controller) {
        if (getPlayerOnTurn() == getPlayerId()) {
            controller.displayMyEntrance();
            controller.displayMyTables();
        } else {
            controller.displayEnemyEntrance();
            controller.displayEnemyTables();
        }
        controller.displayMyProfessors();
        controller.displayEnemyProfessors();
        controller.displayIslandEffect();
        controller.showGameMessage();
    }
}
