package it.polimi.ingsw.server;

import it.polimi.ingsw.model.GameMode;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private int matchId;
    private GameMode gameMode;
    private int numberOfPlayers;
    private List<String> playerNicknames = new ArrayList<>();
    private List<ClientConnection> sockets = new ArrayList<>();

    /**
     * Creates a match instance - maybe to manage reconnection and related issues
     * @param matchId match identifier
     * @param gameMode match mode
     * @param numberOfPlayers number of players playing match
     * @param socket first player' socket
     */
    public Match(int matchId, GameMode gameMode, int numberOfPlayers, String playerNickname, ClientConnection socket) {
        this.matchId = matchId;
        this.gameMode = gameMode;
        this.numberOfPlayers = numberOfPlayers;
        playerNicknames.add(playerNickname);
        sockets.add(socket);
    }

    /**
     * @return match id
     */
    public int getMatchId() {
        return matchId;
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
