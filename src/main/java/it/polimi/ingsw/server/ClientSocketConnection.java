package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.message.ConnectionState;
import it.polimi.ingsw.controller.message.SetupMessage;
import it.polimi.ingsw.model.GameMode;
import it.polimi.ingsw.controller.message.PlayerMessage;
import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class ClientSocketConnection extends Observable<PlayerMessage> implements ClientConnection, Runnable {

    private Socket socket;
    private ObjectOutputStream out;
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
    public synchronized void closeConnection() {
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
    }

    private void close() {
        closeConnection();
        /////// eventuale controllo delle disconnessioni
        System.out.println("Done!");
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
        ObjectInputStream in;
        try{
            Object buffer = null;

            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            send(new SetupMessage(ConnectionState.LOGIN, "Choose your nickname"));
            String playerNickname = "";
            do {
                try {
                    buffer = in.readObject();
                    if (buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.LOGIN && !((SetupMessage) buffer).getMessage().equals(""))
                        playerNickname = ((SetupMessage) buffer).getMessage();
                } catch (Exception e) {
                    send(new SetupMessage(ConnectionState.LOGIN, "Error : you are not sending the correct information\nChoose a nickname"));
                }
            } while (playerNickname.equals(""));
            System.out.println("The player choose is " + playerNickname);

            send(new SetupMessage(ConnectionState.MATCHMODE, "Welcome " + playerNickname + "\nChoose game mode { 0 : normal mode - 1 : expert mode } :"));
            int gameMode = -1;
            do {
                try {
                    buffer = in.readObject();
                    if(buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.MATCHMODE && ((SetupMessage) buffer).getMessage() != null)
                        gameMode = Integer.parseInt((String) ((SetupMessage) buffer).getMessage());
                    else send(new SetupMessage(ConnectionState.MATCHMODE, "Error : you are not sending the correct information\nChoose game mode { 0 : normal mode - 1 : expert mode } :"));
                } catch (Exception e) {
                    send(new SetupMessage(ConnectionState.MATCHMODE, "Error : you are not sending the correct information\nChoose game mode { 0 : normal mode - 1 : expert mode } :"));
                }
            } while (gameMode != 0 && gameMode != 1);
            System.out.println("The player with socket " + toString() + " choose " + (gameMode == 0 ? "normal" : "expert") + " mode");

            send(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "Choose number of players { 2 : match with two players - 3 : match with three players - 4 : match with four players} :"));
            int numberOfPlayers = 0;
            do {
                try {
                    buffer = in.readObject();
                    if(buffer instanceof SetupMessage && ((SetupMessage) buffer).getConnectionState() == ConnectionState.NUMBEROFPLAYERS)
                        numberOfPlayers = Integer.parseInt((String) ((SetupMessage) buffer).getMessage());
                    else send(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "Error : you are not sending the correct information\nChoose number of players { 2 : match with two players - 3 : match with three players - 4 : match with four players} :"));
                } catch (Exception e) {
                    send(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "Error : you are not sending the correct information"));
                }
            } while (numberOfPlayers < 2 || numberOfPlayers > 4);
            System.out.println("The player with socket " + toString() + " choose " + numberOfPlayers + " players mode");

            System.out.println("Adding " + playerNickname + " into the lobby (player : " + toString() + ")");
            server.lobby((gameMode == 0 ? GameMode.NORMAL : GameMode.EXPERT), numberOfPlayers, playerNickname, this);

            while (isActive()) {
                buffer = in.readObject();
                if (buffer instanceof String) {
                    System.out.println("The player " + server.getMyId(this) + " of match " + server.getMyMatch(this) +  " sends "+ (String) buffer);
                } else if (buffer instanceof PlayerMessage) {
                    PlayerMessage clientMessage = (PlayerMessage) buffer;
                    if (server.getMyId(this) == clientMessage.getPlayerId())
                        notify(clientMessage);
                    else
                        // invia l'errore al client
                        System.out.println(toString() + " is sending a message with an other's player id");
                } else {
                    System.out.println(toString() + " is sending a wrong object");
                }
            }
        } catch (IOException | NoSuchElementException e) {
            System.err.println("Error! " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
