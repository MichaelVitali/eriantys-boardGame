package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.exception.InvalidIndexException;

public class DoYoutJobMessage extends PlayerMessage{
    private int parameter;

    public DoYoutJobMessage(int playerId, int indexCard) {
        super(playerId);
        this.parameter = indexCard;
    }

    @Override
    public void performMove(Game game) {
        try {
            game.getRound().doYourJob(getPlayerId(), parameter);
            game.sendGame();
        } catch (InvalidIndexException e) {
        }
    }
}
