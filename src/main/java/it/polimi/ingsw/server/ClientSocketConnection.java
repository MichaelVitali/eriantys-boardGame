package it.polimi.ingsw.server;

import com.sun.jdi.connect.spi.ClosedConnectionException;
import it.polimi.ingsw.controller.message.ConnectionState;
import it.polimi.ingsw.controller.message.SetupMessage;
import it.polimi.ingsw.controller.message.TerminatorMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameMode;
import it.polimi.ingsw.controller.message.PlayerMessage;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.model.exception.AlreadyChosenWizardException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.SocketInactiveException;
import it.polimi.ingsw.model.exception.TooManyMovesException;
import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class ClientSocketConnection extends Observable<PlayerMessage> implements ClientConnection, Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Server server;
    private boolean active = true;

    public ClientSocketConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    private synchronized boolean isActive(){
        return active;
    }

    public synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
            out.reset();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public synchronized Object receive() {
        Object buffer = null;
        try {
            buffer = in.readObject();
        } catch(IOException e){
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return buffer;
    }

    public synchronized void closeConnectionNotifier() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
        System.out.println("Connection " + this.toString() + " closed");
    }

    private void closeAndNotify() {
        closeConnectionNotifier();
        /////// eventuale controllo delle disconnessioni
        server.exitingPlayer(this);
        System.out.println("Connection " + this.toString() + " closed");

    }

    @Override
    public synchronized void closeConnection() {
        send(new TerminatorMessage("The game is over"));
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
        System.out.println("Connection " + this.toString() + " closed");
    }

    public void chooseWizards(Match match){
        String availableWizards = "Choose your Wizard: \n";
        for (Wizard w : Wizard.values()) {
            if (!match.getAlreadyChosenWizards().contains(w))
                availableWizards += "- " + w.toString() + "\n";
        }
        String choose = "";

        send(new SetupMessage(ConnectionState.WIZARDS, availableWizards));
        do {
            try {
                Object buffer = in.readObject();
                if(buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.WIZARDS && ((SetupMessage) buffer).getMessage() != null)
                    choose = (String) ((SetupMessage) buffer).getMessage();
                else send(new SetupMessage(ConnectionState.WIZARDS, "Error : you are not sending the correct information\n"+availableWizards));
            } catch (Exception e) {
                send(new SetupMessage(ConnectionState.WIZARDS, "Error : you are not sending the correct information\n"+availableWizards));
            }
        } while (match.assertValidWizard(Wizard.getWizardFromString(choose)));

        match.getAlreadyChosenWizards().add(Wizard.getWizardFromString(choose));
    }

    //// da vedere
    @Override
    public void asyncSend(final Object message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        }).start();
    }

    @Override
    public void run() {
        try {
            Object buffer = null;

            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            send(new SetupMessage(ConnectionState.LOGIN, "Choose your nickname"));
            String playerNickname = "";
            boolean flag = true;
            do {
                //if (!isActive()) throw new SocketInactiveException();
                try {
                    buffer = in.readObject();
                    flag = true;
                    if(buffer instanceof TerminatorMessage) throw new SocketInactiveException();
                    if (buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.LOGIN && !((SetupMessage) buffer).getMessage().equals(""))
                        playerNickname = ((SetupMessage) buffer).getMessage();
                    for(Match m : server.getAllMatchesOnServer()) {
                        for(String s : m.getPlayerNicknames()) {
                            if (s.equals(playerNickname)) flag = false;
                            send(new SetupMessage(ConnectionState.LOGIN, "The nickname is already chosen!\nChoose your nickname"));
                        }
                    }
                } catch (Exception e) {
                    send(new SetupMessage(ConnectionState.LOGIN, "Error : you are not sending the correct information\nChoose a nickname"));
                }
            } while (playerNickname.equals("") || !flag);
            System.out.println("The player choose is " + playerNickname);

            //if (!isActive()) throw new SocketInactiveException();
            send(new SetupMessage(ConnectionState.MATCHMODE, "Welcome " + playerNickname + "\nChoose game mode { 0 : normal mode - 1 : expert mode } :"));
            int gameMode = -1;
            do {
                //if (!isActive()) throw new SocketInactiveException();
                try {
                    buffer = in.readObject();
                    if (buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.MATCHMODE && ((SetupMessage) buffer).getMessage() != null)
                        gameMode = Integer.parseInt((String) ((SetupMessage) buffer).getMessage());
                    else
                        send(new SetupMessage(ConnectionState.MATCHMODE, "Error : you are not sending the correct information\nChoose game mode { 0 : normal mode - 1 : expert mode } :"));
                } catch (Exception e) {
                    send(new SetupMessage(ConnectionState.MATCHMODE, "Error : you are not sending the correct information\nChoose game mode { 0 : normal mode - 1 : expert mode } :"));
                }
            } while (gameMode != 0 && gameMode != 1);
            System.out.println("The player with socket " + toString() + " choose " + (gameMode == 0 ? "normal" : "expert") + " mode");

            send(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "Choose number of players { 2 : match with two players - 3 : match with three players - 4 : match with four players} :"));
            int numberOfPlayers = 0;
            do {
                //if (!isActive()) throw new SocketInactiveException();
                try {
                    buffer = in.readObject();
                    if (buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.NUMBEROFPLAYERS)
                        numberOfPlayers = Integer.parseInt((String) ((SetupMessage) buffer).getMessage());
                    else
                        send(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "Error : you are not sending the correct information\nChoose number of players { 2 : match with two players - 3 : match with three players - 4 : match with four players} :"));
                } catch (Exception e) {
                    send(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "Error : you are not sending the correct information"));
                }
            } while (numberOfPlayers < 2 || numberOfPlayers > 4);
            System.out.println("The player with socket " + toString() + " choose " + numberOfPlayers + " players mode");

            System.out.println("Adding " + playerNickname + " into the lobby (player : " + toString() + ")");

            server.lobby((gameMode == 0 ? GameMode.NORMAL : GameMode.EXPERT), numberOfPlayers, playerNickname, this);

            while (isActive()) {
                buffer = in.readObject();
                if (buffer instanceof SetupMessage) {
                    /*
                    SetupMessage message = (SetupMessage) buffer;
                    if (message.getConnectionState() == ConnectionState.WIZARDS){
                        System.out.println("passo check su connectionState");
                        server.chooseWizard(message, this);
                    }
                    */
                } else if (buffer instanceof PlayerMessage) {
                    PlayerMessage clientMessage = (PlayerMessage) buffer;
                    if (server.getMyId(this) == clientMessage.getPlayerId())
                        notify(clientMessage);
                    else
                        //Invia l'errore al client
                        System.out.println(toString() + " is sending a message with an other's player id");
                } else {
                    //Manda un oggetto sbagliato
                    System.out.println(toString() + " is sending a wrong object");
                }
            }
        } catch (IOException | NoSuchElementException e) {
            System.err.println("Error! " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (TooManyMovesException e) {
            e.printStackTrace();
        } /*catch (SocketInactiveException e) {
        }*/ finally {
            closeAndNotify();
        }
    }
}
