package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.message.ConnectionState;
import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.message.SetupMessage;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.TooManyMovesException;
import it.polimi.ingsw.view.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 50002;
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
    public synchronized void lobby(GameMode gameMode, int numberOfPlayers, String playerNickname, ClientConnection clientConnection) throws TooManyMovesException {
        Match match = searchForMatch(gameMode, numberOfPlayers);
        if (match == null) {
            System.out.println("Just create a match with the id : " + nextMatchId);
            pendingMatches.add(new Match(nextMatchId++, gameMode, numberOfPlayers, playerNickname, clientConnection));
            clientConnection.send(new SetupMessage(ConnectionState.SUCCESS, "Waiting for a match. Get ready to play..."));
        } else {
            match.addPlayer(clientConnection, playerNickname);
            if (match.getNumberOfPlayers() == match.getSockets().size()) {

                View[] playerView = new RemoteView[match.getNumberOfPlayers()];
                for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                    playerView[i] = new RemoteView(i, match.getPlayerNicknames().get(i), match.getSockets().get(i));
                }
                for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                    System.out.println("Player : " + i + " " + match.getPlayerNicknames().get(i) + " " + match.getSockets().get(i).toString());
                }
                System.out.println(match.getGameMode());
                Game model;
                if (match.getGameMode() == GameMode.NORMAL)
                    model = new Game(match.getNumberOfPlayers(), match.getPlayerNicknames());
                else
                    model = new ExpertGame(match.getNumberOfPlayers(), match.getPlayerNicknames());

                Controller controller = new Controller(model);

                List<Wizard> wizards = new ArrayList();
                for(Wizard wizard : Wizard.values()) {
                    wizards.add(wizard);
                    System.out.println(wizard);
                }
                for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                    model.addObserver(playerView[i]);
                    playerView[i].addObserver(controller);
                    String wizardString = "";
                    for(Wizard wizard : wizards)
                        wizardString += (wizard.toString() + " ");
                    /*boolean hasWizardbeenChosen = false;
                    do {
                        match.getSockets().get(i).send(new SetupMessage(ConnectionState.WIZARDS, "Choose your wizard to play Eriantys\nEnter the color : " + wizardString));
                        Object buffer = match.getSockets().get(i).receive();
                        if(buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.WIZARDS && Wizard.getWizardFromString(((SetupMessage) buffer).getMessage()) != null) {
                            model.getPlayer(i).setWizard(Wizard.getWizardFromString(((SetupMessage) buffer).getMessage()));
                            wizards.remove(Wizard.getWizardFromString(((SetupMessage) buffer).getMessage()));
                            hasWizardbeenChosen = true;
                            System.out.println("Ciao");
                        }
                    } while(!hasWizardbeenChosen);*/
                    GameMessage displayedBoard = new GameMessage(model, i);
                    match.getSockets().get(i).send(displayedBoard);
                }

                System.out.println("The match " + match.getMatchId() + " starts");
                System.out.println("The starting order of match " + match.getMatchId() + " is " + model.getRound().getPlayerOrder().toString());
                runningMatches.add(match);
                pendingMatches.remove(match);
            } else {
                clientConnection.send(new SetupMessage(ConnectionState.SUCCESS, "The configuration is done. Get ready to play..."));
            }
        }
    }

    /**
     * Searches
     * @param gameMode
     * @param numberOfPlayers
     * @return
     */
    public Match searchForMatch(GameMode gameMode, int numberOfPlayers) {
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
