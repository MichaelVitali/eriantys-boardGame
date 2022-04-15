package it.polimi.ingsw.server;

import it.polimi.ingsw.model.GameMode;
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

    // da vedere
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
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            int gameMode = -1;
            do {
                send("Welcome!\nChoose game mode { 0 : normal mode - 1 : expert mode } :\n");
                gameMode = in.readInt();
            } while (gameMode != 0 || gameMode != 1);
            int numberOfPlayers = 0;
            do {
                send("Choose number of players { 2 : match with two players - 3 : match with three players - 4 : match with four players} :\n");
                numberOfPlayers = in.readInt();
            } while (numberOfPlayers < 2 || numberOfPlayers > 4);

            String playerNickname;
            send("Insert a nickname\n");
            playerNickname = (String)in.readObject();

            server.lobby(gameMode == 0 ? GameMode.NORMAL : GameMode.EXPERT,numberOfPlayers, playerNickname, this);

            PlayerMessage clientMessage;
            while (isActive()) {
                clientMessage = (PlayerMessage) in.readObject();
                notify(clientMessage);
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
