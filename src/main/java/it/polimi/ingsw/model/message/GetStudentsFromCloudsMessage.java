package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class GetStudentsFromCloudsMessage implements Serializable, PlayerMessage {
    private int playerId;
    private int cloudIndex;

    public GetStudentsFromCloudsMessage(int playerId, int cloudIndex) {
        this.playerId = playerId;
        this.cloudIndex = cloudIndex;
    }

    @Override
    public void performMove(Game game) { game.getRound().getStudentsFromCloud(playerId, cloudIndex); }

}
