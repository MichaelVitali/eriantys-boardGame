package it.polimi.ingsw.server;

import it.polimi.ingsw.model.GameMode;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.message.PlayerMessage;
import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
            int gameMode = -1;
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            do {
                send("Welcome!\nChoose game mode { 0 : normal mode - 1 : expert mode } :");
                try {
                    buffer = in.readObject();
                    if(buffer instanceof String) {
                        gameMode = Integer.parseInt((String) buffer);
                        //System.out.println("The player inserts " + gameMode);
                    }
                } catch (Exception e) {
                    send("Error : you are not sending the correct information");
                }
            } while (gameMode != 0 && gameMode != 1);
            System.out.println("The player with socket " + toString() + " choose " + (gameMode == 0 ? "normal" : "expert") + " mode");
            int numberOfPlayers = 0;
            do {
                send("Choose number of players { 2 : match with two players - 3 : match with three players - 4 : match with four players} :");
                try {
                    buffer = in.readObject();
                    if(buffer instanceof String) {
                        numberOfPlayers = Integer.parseInt((String) buffer);
                        //System.out.println("The player in playing in a match with " + numberOfPlayers + " players");
                    }
                } catch (Exception e) {
                    send("Error : you are not sending the correct information");
                }
            } while (numberOfPlayers < 2 || numberOfPlayers > 4);
            System.out.println("The player with socket " + toString() + " choose " + numberOfPlayers + " players mode");
            String playerNickname = "";
            send("Insert a nickname");
            do {
                try {
                    buffer = in.readObject();
                    if (buffer instanceof String) {
                        playerNickname = (String) buffer;
                        System.out.println("The player choose is " + playerNickname);
                    }
                } catch (Exception e) {
                    send("Error : you are not sending the correct information");
                }
            } while (playerNickname.equals(""));
            System.out.println("Adding " + playerNickname + " into the lobby (player : " + toString() + ")");
            server.lobby((gameMode == 0 ? GameMode.NORMAL : GameMode.EXPERT),numberOfPlayers, playerNickname, this);
            PlayerMessage clientMessage;
            while (isActive()) {
                buffer = in.readObject();
                if (buffer instanceof String) {
                    System.out.println("The player " + server.getMyId(this) + " of match " + server.getMyMatch(this) +  " sends "+ (String) buffer);
                } else if (buffer instanceof PlayerMessage) {
                    clientMessage = (PlayerMessage) buffer;
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
