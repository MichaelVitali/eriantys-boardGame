package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.*;

import javax.management.ConstructorParameters;
import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 40000;
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
                connections++;
                System.out.println("Ready for the new connection number : " + connections);
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
        if (match == null)
            pendingMatches.add(new Match(nextMatchId++, gameMode, numberOfPlayers, playerNickname, clientConnection));
        else {
            match.addPlayer(clientConnection);
            if (match.getNumberOfPlayers() == match.getSockets().size()) {
                View[] playerView = new RemoteView[match.getNumberOfPlayers()];
                for (int i = 0; i < numberOfPlayers; i++) {
                    playerView[i] = new RemoteView(i, match.getPlayerNicknames().get(i), match.getSockets().get(i));
                }
                if (match.getGameMode() == GameMode.NORMAL) {
                    Game model = new Game(match.getNumberOfPlayers(), (String[]) match.getPlayerNicknames().toArray());
                    Controller controller = new Controller(model);
                    for (int i = 0; i < numberOfPlayers; i++) {
                        model.addObserver(playerView[i]);
                        playerView[i].addObserver(controller);
                        match.getSockets().get(i).asyncSend(model); /////////////// Da fare - mando la situazione iniziale
                    }
                } else {
                    ExpertGame model = new ExpertGame(match.getNumberOfPlayers(), (String[]) match.getPlayerNicknames().toArray());
                    Controller controller = new Controller(model);
                    for (int i = 0; i < numberOfPlayers; i++) {
                        model.addObserver(playerView[i]);
                        playerView[i].addObserver(controller);
                        match.getSockets().get(i).asyncSend(model); /////////////// Da fare - mando la situazione iniziale
                    }
                }
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
        for (Match match : pendingMatches) {
            if (match.getGameMode() == gameMode && match.getNumberOfPlayers() == numberOfPlayers)
                return match;
        }
        return null;
    }

    public int getMyId(Socket clientSocketConnection) {
        int playerId = -1;
        for (Match match : runningMatches) {
            if (match.getSockets().contains(clientSocketConnection)) {
               playerId = match.getSockets().indexOf(clientSocketConnection);
            }
        }
        return playerId;
    }
}
