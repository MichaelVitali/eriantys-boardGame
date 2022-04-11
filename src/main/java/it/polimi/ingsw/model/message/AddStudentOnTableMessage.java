package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class AddStudentOnTableMessage implements Serializable, PlayerMessage {
    private int playerId;
    private int studentIndex;

    public AddStudentOnTableMessage(int playerId, int studentIndex) {
        this.playerId = playerId;
        this.studentIndex = studentIndex;
    }

    @Override
    public void performMove(Game game) {
        game.getRound().playAssistant(playerId, studentIndex);
    }
}
