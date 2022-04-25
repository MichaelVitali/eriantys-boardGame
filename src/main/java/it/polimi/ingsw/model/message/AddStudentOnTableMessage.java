package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class AddStudentOnTableMessage extends PlayerMessage implements Serializable {
    private int studentIndex;

    public AddStudentOnTableMessage(int playerId, int studentIndex) {
        super(playerId);
        this.studentIndex = studentIndex;
    }

    @Override
    public void performMove(Game game) {
        game.getRound().playAssistant(getPlayerId(), studentIndex);
    }
}
