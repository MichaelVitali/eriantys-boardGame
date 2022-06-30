package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class AddStudentOnTableMessage extends PlayerMessage implements Serializable {
    private static final long serialVersionUID = 3L;
    private int studentIndex;

    public AddStudentOnTableMessage(int playerId, int studentIndex) {
        super(playerId);
        this.studentIndex = studentIndex;
    }

    /**
     * Calls the function on round to add the chosen student to his table
     * @param game
     */
    @Override
    public void performMove(Game game) {
        game.getRound().addStudentOnTable(getPlayerId(), studentIndex);
    }
}
