package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.message.AddStudentOnIslandMessage;
import it.polimi.ingsw.model.message.PlayerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientCli {

    private String ip;
    private int port;
    private boolean configurationDone = false;
    private boolean active = true;

    public ClientCli(String ip, int port){
        this.ip = ip;
        this.port = port;
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
                        if(inputObject instanceof String) {
                            // initial configuration
                            System.out.println((String)inputObject);
                        } else if (inputObject instanceof DisplayedBoard){
                            if(!configurationDone) {
                                configurationDone = true;
                                System.out.println("Get ready to play...");
                            }

                            /////// displayare la board

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

    public Thread asyncWriteToSocket(final Scanner stdin, final ObjectOutputStream socketOut){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        String playerInput = stdin.nextLine();
                        playerInput.replace("\n", "");
                        if (!configurationDone) {
                            socketOut.writeObject(playerInput);
                        }/* else {
                            System.out.println("Lets move");

                            /////// ci sarà un certo flusso di esecuzione
                            //// sarà da gestire l'esecuzione
                            ////Problema: non può essere il client a scrivere che player è
                            //PlayerMessage playerMessage = new AddStudentOnIslandMessage(0,0,0);

                            //socketOut.writeObject((PlayerMessage)playerMessage);
                        }*/
                        socketOut.flush();
                    }
                } catch(Exception e) {
                    setActive(false);
                }
            }
        });
        thread.start();
        return thread;
    }

    public void run() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
        Scanner stdin = new Scanner(System.in);

        try{
            Thread t0 = asyncReadFromSocket(socketIn);
            Thread t1 = asyncWriteToSocket(stdin, socketOut);
            t0.join();
            t1.join();
        } catch(InterruptedException | NoSuchElementException e){
            System.out.println("Connection closed from the client side");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}
