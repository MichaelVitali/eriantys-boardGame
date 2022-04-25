package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class GetStudentsFromCloudsMessage extends PlayerMessage implements Serializable {
    private int cloudIndex;

    public GetStudentsFromCloudsMessage(int playerId, int cloudIndex) {
        super(playerId);
        this.cloudIndex = cloudIndex;
    }

    @Override
    public void performMove(Game game) { game.getRound().getStudentsFromCloud(getPlayerId(), cloudIndex); }

}
