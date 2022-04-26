package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Round;

import java.io.Serializable;

public class PlayAssistantMessage extends PlayerMessage implements Serializable {

    private static final long serialVersionUID =1L;
    private int assistantPosition;

    public PlayAssistantMessage(int playerId, int assistantPosition) {
        super(playerId);
        this.assistantPosition = assistantPosition;
    }
    
    @Override
    public void performMove(Game game) {
        game.getRound().playAssistant(getPlayerId(), assistantPosition);
    }
}