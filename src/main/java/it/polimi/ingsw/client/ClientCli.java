package it.polimi.ingsw.client;

import it.polimi.ingsw.controller.message.*;

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

    private int playerId = 0;
    private DisplayedBoard actualBoard;

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
                            actualBoard = ((DisplayedBoard) inputObject);
                            if(!configurationDone) {
                                configurationDone = true;
                                playerId = actualBoard.getPlayerId();
                                System.out.println("The configuration is done. Get ready to play...");
                            }
                            if (actualBoard != null)
                                actualBoard.printDefaultOnCli();

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
                        } else {
                            PlayerMessage playerMessage = null;
                            try {
                                int playerParameter = Integer.parseInt(playerInput);
                                    switch(actualBoard.getState()) {
                                        case 0:
                                            playerMessage = new PlayAssistantMessage(playerId, playerParameter);
                                            break;
                                        case 1:
                                            if (playerParameter == 1) {
                                                playerParameter = readLineAndParseInteger(stdin);
                                                playerMessage = new AddStudentOnTableMessage(playerId, playerParameter);
                                            } else if (playerParameter == 2) {
                                                int targetStudent = readLineAndParseInteger(stdin);
                                                int targetIsland = readLineAndParseInteger(stdin);
                                                playerMessage = new AddStudentOnIslandMessage(playerId, targetStudent, targetIsland);
                                            } else {
                                                System.out.println("Make your move:");
                                                System.out.println("1 : Move a student from entrance to table");
                                                System.out.println("2 : Move a student from entrance to an island");
                                            }
                                            break;
                                        case 2:
                                            playerMessage = new ChangeMotherNaturePositionMessage(playerId, playerParameter);
                                            break;
                                        case 3:
                                            playerMessage = new GetStudentsFromCloudsMessage(playerId, playerParameter);
                                            break;
                                    }
                            } catch (NumberFormatException e) {
                                actualBoard.printDefaultOnCli();
                            }
                            if(playerMessage != null)
                                socketOut.writeObject(playerMessage);
                            else
                                System.out.println("You insert something wrong");
                        }
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
        } catch(InterruptedException | NoSuchElementException e) {
            System.out.println("Connection closed from the client side");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }

    private int readLineAndParseInteger(final Scanner inputStream) {
        boolean isParameterSet = false;
        int parameter = -1;
        do {
            String playerInput = inputStream.nextLine();
            playerInput.replace("\n", "");
            try {
                parameter = Integer.parseInt(playerInput);
                isParameterSet = true;
            } catch (NumberFormatException e) {
                System.out.println("You insert the wrong value");
            }
        } while(!isParameterSet);
        return parameter;
    }
}