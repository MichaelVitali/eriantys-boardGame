package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.*;
import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientCli {

    private String ip;
    private int port;
    private boolean configurationDone = false;
    private boolean active = true;

    private int playerId = 0;
    private GameMessage actualBoard;

    public ClientCli(String ip, int port) {
        this.ip = ip;
        this.port = port;
        printLogo();
    }

    public void printLogo() {
        int a = 3;
        int b = 6;
        System.out.print("▉▉▉▉▉▉ ▉▉▉▉▉  ▉▉   ▉▉   ▉▉   ▉▉ ▉▉▉▉▉▉ ▉▉  ▉▉ ▉▉▉▉▉\n" +
                         "▉▉     ▉▉  ▉▉ ▉▉  ▉▉▉▉  ▉▉▉  ▉▉   ▉▉   ▉▉  ▉▉ ▉▉   \n" +
                         "▉▉▉▉   ▉▉▉▉▉▉ ▉▉ ▉▉  ▉▉ ▉▉ ▉ ▉▉   ▉▉   ▉▉▉▉▉▉ ▉▉▉▉▉\n" +
                         "▉▉     ▉▉ ▉▉  ▉▉ ▉▉▉▉▉▉ ▉▉  ▉▉▉   ▉▉     ▉▉      ▉▉\n" +
                         "▉▉▉▉▉▉ ▉▉  ▉▉ ▉▉ ▉▉  ▉▉ ▉▉   ▉▉   ▉▉     ▉▉   ▉▉▉▉▉\n\n\n");
        System.out.println("Welcome to the magical world of Eriantys\nTry to win the game and become the king of Eriantys\nPress Enter to continue...");
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
                            clearAll();
                            System.out.println((String)inputObject);
                        } else if (inputObject instanceof GameMessage){
                            actualBoard = ((GameMessage) inputObject);
                            if(!configurationDone) {
                                configurationDone = true;
                                playerId = actualBoard.getPlayerId();
                            }
                            if (actualBoard != null) {
                                clearAll();
                                actualBoard.printDefaultOnCli();
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
                                if (playerId == actualBoard.getPlayerOnTurn()) {
                                    if (playerInput.equals("character") && actualBoard.getState() != 0 && !actualBoard.getAlreadyPLayedCharacter() && actualBoard.getGameMode() == GameMode.EXPERT) {
                                        int cardIndex = -1;
                                        do {
                                            System.out.println("Which character do you want to play: ");
                                            cardIndex = Integer.parseInt(stdin.nextLine().replace("\n", ""));
                                        } while (cardIndex < 0 || cardIndex >= 3);
                                        playerMessage = new ActivateEffectMessage(playerId, cardIndex);
                                    } else {
                                        int playerParameter = Integer.parseInt(playerInput);
                                        switch (actualBoard.getState()) {
                                            case 0:
                                                playerMessage = new PlayAssistantMessage(playerId, playerParameter);
                                                break;
                                            case 1:
                                                if (playerParameter == 1) {
                                                    System.out.println("Select the index of the student");
                                                    playerParameter = readLineAndParseInteger(stdin);
                                                    playerMessage = new AddStudentOnTableMessage(playerId, playerParameter);
                                                } else if (playerParameter == 2) {
                                                    System.out.println("Select the index of the student");
                                                    int targetStudent = readLineAndParseInteger(stdin);
                                                    System.out.println("Select the index of the island");
                                                    int targetIsland = readLineAndParseInteger(stdin);
                                                    playerMessage = new AddStudentOnIslandMessage(playerId, targetStudent, targetIsland);
                                                }
                                                break;
                                            case 2:
                                                playerMessage = new ChangeMotherNaturePositionMessage(playerId, playerParameter);
                                                break;
                                            case 3:
                                                playerMessage = new GetStudentsFromCloudsMessage(playerId, playerParameter);
                                                break;
                                            case 4:
                                                break;
                                            case 10:
                                                playerMessage = new ChooseWizardMessage(playerId, playerParameter);
                                                break;
                                        }
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("You insert a wrong formatted input, insert only numbers");
                                actualBoard.printDefaultOnCli();
                            }
                            if(playerMessage != null)
                                socketOut.writeObject(playerMessage);
                            else if (!playerInput.equals("board") && !playerInput.equals("show others") && !playerInput.equals("do action") && !playerInput.equals("character"))
                                System.out.println("You insert something wrong");
                        }
                        socketOut.flush();
                        socketOut.reset();
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

    /**
     * Clears the part of command line the players can see, so the players will always see the drawings of the board in the upper part of the cli
     */
    public void clearAll() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
