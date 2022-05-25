package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.model.Game;

public class DoYoutJobMessage extends PlayerMessage{
    private int indexCard;

    public DoYoutJobMessage(int playerId, int indexCard) {
        super(playerId);
        this.indexCard = indexCard;
    }

    @Override
    public void performMove(Game game) {
        game.getRound().activateEffect(getPlayerId(), indexCard);
    }
}
