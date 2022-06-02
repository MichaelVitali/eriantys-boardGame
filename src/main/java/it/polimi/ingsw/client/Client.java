package it.polimi.ingsw.client;

import it.polimi.ingsw.ClientApp;
import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.SetupMessage;
import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Client extends Observable<Message> implements Runnable {

    private String ip;
    private int port;
    private boolean active = true;
    private int playerId = 0;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private GameMessage actualBoard;

    public Client(String ip, int port) throws Exception {
        socket = new Socket(ip, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connection established");
    }

    @Override
    public void run() {
        try {
            Thread inputThread = asyncReadFromSocket(inputStream);
            inputThread.join();
        } catch(InterruptedException | NoSuchElementException e) {
            System.out.println("Connection closed from the client side");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Unable to close the socket");
            }

        }
    }

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
    }

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if(inputObject instanceof SetupMessage) {
                            System.out.println("setup message arrivato client");
                            if(inputObject  != null) {
                                Client.this.notify((SetupMessage) inputObject); //////Vedere se va bene
                            }
                        } else if (inputObject instanceof GameMessage){

                            if(inputObject  != null) {
                                System.out.println("Board arrivata client");
                                actualBoard = ((GameMessage) inputObject);
                                Client.this.notify(actualBoard);
                            } else {
                                System.out.println("Messaggio nullo");
                            }
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    public Thread asyncWriteToSocket(Message playerMessage) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (isActive()) {
                        outputStream.writeObject(playerMessage);
                        outputStream.flush();
                        outputStream.reset();
                    }
                } catch(Exception e) {
                    setActive(false);
                }
            }
        });
        thread.start();
        return thread;
    }
}
