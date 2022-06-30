package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;
import java.util.List;

public class ActivateEffectMessage extends PlayerMessage implements Serializable {
    private static final long serialVersionUID = 6L;
    private int indexCard;

    /**
     * message which permits activating character effects
     * @param playerId
     * @param indexCard
     */
    public ActivateEffectMessage(int playerId, int indexCard) {
        super(playerId);
        this.indexCard = indexCard;
    }

    @Override
    public void performMove(Game game) {
        game.getRound().activateEffect(getPlayerId(), indexCard);
        game.sendGame();
    }
}