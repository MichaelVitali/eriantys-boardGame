package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Round;

import java.io.Serializable;

public class PlayAssistantMessage implements Serializable, PlayerMessage {
    private int playerId;
    private int assistantPosition;

    public PlayAssistantMessage(int playerId, int assistantPosition) {
        this.playerId = playerId;
        this.assistantPosition = assistantPosition;
    }

    public int getPlayerId() {
        return playerId;
    }
    
    @Override
    public void performMove(Game game) {
        game.getRound().playAssistant(playerId, assistantPosition);
    }
}
