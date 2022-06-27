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

    private static final int PORT = 50006;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private int nextMatchId;
    private List<Match> pendingMatches = new ArrayList<>();
    private List<Match> runningMatches = new ArrayList<>();
    private boolean wizardsHasBeenChosen = false;

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
/*
    private void startGame(Match match){

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

        match.setModel(model);

        for (int i = 0; i < match.getNumberOfPlayers(); i++) {
            model.addObserver(playerView[i]);
            playerView[i].addObserver(controller);

            GameMessage displayedBoard = new GameMessage(model, i);
            match.getSockets().get(i).send(displayedBoard);
        }

        System.out.println("The match " + match.getMatchId() + " starts");
        System.out.println("The starting order of match " + match.getMatchId() + " is " + model.getRound().getPlayerOrder().toString());

        runningMatches.add(match);
        pendingMatches.remove(match);
    }
*/
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
               /* String availableWizards = "Choose your Wizard: \n";
                for (Wizard w : Wizard.values()) {
                    if (!match.getAlreadyChosenWizards().contains(w))
                        availableWizards += "- " + w.toString() + "\n";
                }
                match.getSockets().get(0).send(new SetupMessage(ConnectionState.WIZARDS, availableWizards));*/

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

                match.setModel(model);

                for (int i = 0; i < match.getNumberOfPlayers(); i++) {
                    model.addObserver(playerView[i]);
                    playerView[i].addObserver(controller);

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
    }/*

    public synchronized void chooseWizard(SetupMessage message, ClientConnection clientConnection){
        Match match = getMyMatch(clientConnection);
        System.out.println("Sono in chooseWizard");
        if (match.getAlreadyChosenWizards().size() == match.getNumberOfPlayers())
            startGame(match);
        else {
            String availableWizards = "Choose your Wizard: \n";
            if (match.assertValidWizard(Wizard.getWizardFromString(message.getMessage()))) {
                    match.getAlreadyChosenWizards().add(Wizard.getWizardFromString(message.getMessage()));
                for (Wizard w : Wizard.values()) {
                    if (!match.getAlreadyChosenWizards().contains(w))
                        availableWizards += "- " + w.toString() + "\n";
                }

                match.getSockets().get(match.getAlreadyChosenWizards().size()).send(new SetupMessage(ConnectionState.WIZARDS, availableWizards));
            } else {
                for (Wizard w : Wizard.values()) {
                    if (!match.getAlreadyChosenWizards().contains(w))
                        availableWizards += "- " + w.toString() + "\n";
                }
                match.getSockets().get(match.getAlreadyChosenWizards().size()).send(new SetupMessage(ConnectionState.WIZARDS, "Error : you are not sending the correct information. " + availableWizards));
            }
        }
    }
*/
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


    public Match getMyMatch(ClientConnection clientSocketConnection){
        Match myMatch = null;
        for (Match m : runningMatches)
            if (m.getSockets().contains(clientSocketConnection))
                myMatch = m.getMatch();
        return myMatch;
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

    public void exitingPlayer(ClientConnection clientSocketConnection) {
        Match myMatch = getMyMatch(clientSocketConnection);

        if (myMatch != null) {
            for (ClientConnection clientConnection : myMatch.getSockets()) {
                if (clientConnection != null && clientConnection != clientSocketConnection)
                    clientConnection.closeConnection();
            }
        }

        pendingMatches.remove(myMatch);
        runningMatches.remove(myMatch);
    }

}
