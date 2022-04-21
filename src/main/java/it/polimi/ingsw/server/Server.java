package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 50000;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private int nextMatchId;
    private List<Match> pendingMatches = new ArrayList<>();
    private List<Match> runningMatches = new ArrayList<>();

    /**
     * Creates a server instance with a server socket accepting connection on port 40000
     * @throws IOException if there is an input/output error
     */
    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    /**
     * Accepts multiple connections from clients and creates threads that manage the clients interactions
     */
    public void run() {
        int connections = 0;
        System.out.println("Server is running");
        while(true) {
            try {
                Socket newSocket = serverSocket.accept();
                System.out.println("Received connection " + connections + "");
                connections++;
                ClientSocketConnection socketConnection = new ClientSocketConnection(newSocket, this);
                executor.submit(socketConnection);
            } catch (IOException e) {
                System.out.println("Connection Error!");
            }
        }
    }

    /**
     *
     * @param clientConnection
     * @param numberOfPlayers
     * @param gameMode
     */
    public synchronized void lobby(GameMode gameMode, int numberOfPlayers, String playerNickname, ClientConnection clientConnection) {
        Match match = searchForMatch(gameMode, numberOfPlayers);
        if (match == null) {
            System.out.println("Just create a match with the id : " + nextMatchId);
            pendingMatches.add(new Match(nextMatchId++, gameMode, numberOfPlayers, playerNickname, clientConnection));
        } else {
            System.out.println("Aggiungo il secondo");
            match.addPlayer(clientConnection, playerNickname);
            if (match.getNumberOfPlayers() == match.getSockets().size()) {

                View[] playerView = new RemoteView[match.getNumberOfPlayers()];
                for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                    playerView[i] = new RemoteView(i, match.getPlayerNicknames().get(i), match.getSockets().get(i));
                }
                for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                    System.out.println("Player : " + i + " " + match.getPlayerNicknames().get(i) + " " + match.getSockets().get(i).toString());
                }

                List<String> prova = new ArrayList<>();
                prova.add("mike");
                prova.add("enri");
                if (match.getGameMode() == GameMode.NORMAL) {
                    Game model = new Game(2, prova);
                    //Controller controller = new Controller(model);
                    System.out.println("Game creation!");
                    /*for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                        model.addObserver(playerView[i]);
                        playerView[i].addObserver(controller);
                        match.getSockets().get(i).send(new DisplayedBoard(model)); /////////////// Da fare - mando la situazione iniziale
                    }*/
                } /*else {
                    ExpertGame model = new ExpertGame(match.getNumberOfPlayers(), match.getPlayerNicknames());
                    Controller controller = new Controller(model);
                    for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                        model.addObserver(playerView[i]);
                        playerView[i].addObserver(controller);
                        match.getSockets().get(i).send(new DisplayedBoard(model)); /////////////// Da fare - mando la situazione iniziale
                    }
                }
                */
                for (ClientConnection connection : match.getSockets()) {
                    connection.asyncSend("The match begins !");
                }
                System.out.println("The match " + match.getMatchId() + " starts");
                runningMatches.add(match);
                pendingMatches.remove(match);
            }
        }
    }

    /**
     * Searches
     * @param gameMode
     * @param numberOfPlayers
     * @return
     */
    private Match searchForMatch(GameMode gameMode, int numberOfPlayers) {
        if(pendingMatches.size() == 0) return null;
        for (Match match : pendingMatches) {
            if (match.getGameMode() == gameMode && match.getNumberOfPlayers() == numberOfPlayers)
                return match;
        }
        return null;
    }

    public int getMyMatch(ClientConnection clientSocketConnection) {
        int matchId = -1;
        for (Match match : runningMatches) {
            if (match.getSockets().contains(clientSocketConnection)) {
                matchId = match.getMatchId();
            }
        }
        return matchId;
    }
    public int getMyId(ClientConnection clientSocketConnection) {
        int playerId = -1;
        for (Match match : runningMatches) {
            if (match.getSockets().contains(clientSocketConnection)) {
                playerId = match.getSockets().indexOf(clientSocketConnection);
            }
        }
        return playerId;
    }
}
