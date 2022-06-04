package it.polimi.ingsw.server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameMode;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private final int matchId;
    private final GameMode gameMode;
    private int numberOfPlayers;
    private final List<String> playerNicknames = new ArrayList<>();
    private final List<ClientConnection> sockets = new ArrayList<>();
    private final List<Wizard> alreadyChosenWizards = new ArrayList<>();
    private Game model;

    /**
     * Creates a match instance - maybe to manage reconnection and related issues
     * @param matchId match identifier
     * @param gameMode match mode
     * @param numberOfPlayers number of players playing match
     * @param socket first player' socket
     * /*@param wizard*/

    public Match(int matchId, GameMode gameMode, int numberOfPlayers, String playerNickname, ClientConnection socket) {
        this.matchId = matchId;
        this.gameMode = gameMode;
        this.numberOfPlayers = numberOfPlayers;
        playerNicknames.add(playerNickname);
        sockets.add(socket);
        model = null;
    }

    public boolean assertValidWizard(Wizard wizard){
        boolean result = true;
        for (Wizard w : alreadyChosenWizards)
            if (w.getIndex() == wizard.getIndex()) {
                result = false;
                break;
            }

        return result;
    }

    public List<Wizard> getAlreadyChosenWizards() {
        return alreadyChosenWizards;
    }

    public void setModel(Game model){
        this.model = model;
    }

    public Game getModel(){
        return model;
    }

    /**
     * @return match id
     */
    public int getMatchId() {
        return matchId;
    }

    /**
     * @return this match
     */
    public Match getMatch() {
        return this;
    }

    /**
     * @return match mode
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * @return number of player playing the match
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * @return list containing the players' nicknames
     */
    public List<String> getPlayerNicknames() {
        return new ArrayList<>(playerNicknames);
    }

    /**
     * @return list containing the players' chosen wizards
     */

    public void setAlreadyChosenWizards(Wizard w){
        alreadyChosenWizards.add(w);
    }

    public List<Wizard> getChosenWizards() {
        return new ArrayList<>(alreadyChosenWizards);
    }

    /**
     * @return list containing the players' sockets
     */
    public List<ClientConnection> getSockets() {
        return new ArrayList<>(sockets);
    }

    public void addPlayer(ClientConnection socket, String playerNickname) {
        playerNicknames.add(playerNickname);
        sockets.add(socket);
    }
}
