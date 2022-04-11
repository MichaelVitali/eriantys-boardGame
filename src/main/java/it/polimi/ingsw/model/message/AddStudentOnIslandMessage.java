package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class AddStudentOnIslandMessage implements Serializable, PlayerMessage {
    private int playerId;
    private int studentIndex;
    private int islandIndex;

    public AddStudentOnIslandMessage(int playerId, int studentIndex, int islandIndex) {
        this.playerId = playerId;
        this.studentIndex = studentIndex;
        this.islandIndex = islandIndex;
    }

    @Override
    public void performMove(Game game) {
        game.getRound().addStudentOnIsland(playerId, studentIndex, islandIndex);
    }
}
