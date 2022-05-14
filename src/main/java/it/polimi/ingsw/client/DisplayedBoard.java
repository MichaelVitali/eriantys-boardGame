package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Assistant;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameMode;
import it.polimi.ingsw.model.GameTable;

import java.io.Serializable;
import java.util.List;

public class DisplayedBoard implements Serializable {
    private static final long serialVersionUID = 100L;
    private int state;
    private int playerId;
    private String playerMessage;
    private GameTable gametable;
    private List<Assistant> assistants;
    private int playerOnTurn;
    private int numberOfPLayer;
    private GameMode gameMode;
    private boolean alreadyPlayedCharacter;

    public DisplayedBoard(Game model, int playerId) {
        state = model.getRound().getRoundState();
        this.playerId = playerId;
        playerMessage = model.getPlayer(playerId).getPlayerMessage();
        gametable = model.getGameTable();
        assistants = model.getPlayerAssistant(playerId);
        playerOnTurn = model.getRound().getPlayerOnTurn();
        numberOfPLayer = model.getNumberOfPlayers();
        gameMode = model.getGameMode();
        alreadyPlayedCharacter = model.getRound().getAlreadyPLayedCharacter();
    }

    public GameTable getGametable() {
        return gametable;
    }

    public List<Assistant> getAssistants() {
        return assistants;
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



    public void printDefaultOnCli() {
        System.out.println("Use comand 'board' to show you board");
        System.out.println("Use command 'Show other' to show other schoolboard");
        if (gameMode == GameMode.EXPERT && playerOnTurn == playerId && state != 0 && !alreadyPlayedCharacter) System.out.println("Use command 'character' to play a character");
        System.out.println(playerMessage);
    }

    public int getNumberOfPLayer() {
        return numberOfPLayer;
    }

    public boolean getAlreadyPLayedCharacter() {return alreadyPlayedCharacter; }

    public int getPlayerOnTurn() { return playerOnTurn; }

    public GameMode getGameMode() { return gameMode; }

    public void printStateOnCli() {
        System.out.println("The current round state is: " + state);
    }
}
