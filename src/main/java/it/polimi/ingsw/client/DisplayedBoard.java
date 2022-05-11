package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class DisplayedBoard implements Serializable {
    private static final long serialVersionUID = 100L;
    private int state;

    private int playerId;
    private String playerMessage;
    private Game model;

    public DisplayedBoard(Game model, int playerId) {
        this.model = model;
        state = model.getRound().getRoundState();
        this.playerId = playerId;
        playerMessage = model.getPlayer(playerId).getPlayerMessage();
    }

    public int getState() {
        return state;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerMessage() {
        return playerMessage;
    }

    public Game getModel() { return model;}

    public void printDefaultOnCli() {
        System.out.println("Use comand 'board' to show you board");
        System.out.println(playerMessage);
    }

    public void printStateOnCli() {
        System.out.println("The current round state is: " + state);
    }
}
