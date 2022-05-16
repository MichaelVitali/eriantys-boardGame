package it.polimi.ingsw.client;

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
    private int lastState;

    private int playerId = 0;
    private DisplayedBoard actualBoard;

    public ClientCli(String ip, int port) {
        this.ip = ip;
        this.port = port;
        lastState = 0;
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
                            }
                            if (actualBoard != null) {
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
                                if (playerInput.equals("board")) {
                                    int parameter = 0;
                                    do {
                                        System.out.println("Which part do you want to show:\n1:Schoolboard\n2:Islands\n3:Mother nature position\n4:Assistants\n5:Clouds");
                                        parameter = Integer.parseInt(stdin.nextLine().replace("\n", ""));
                                    } while (parameter < 1 || parameter > 5);
                                    if (parameter == 1)
                                        printSchoolboard(actualBoard.getGametable().getSchoolBoards()[playerId]);
                                    else if (parameter == 2) printIslands(actualBoard.getGametable().getIslands());
                                    else if (parameter == 3)
                                        System.out.println("Mother nature is in the island number: " + actualBoard.getGametable().getMotherNaturePosition());
                                    else if (parameter == 4) printAssistants(actualBoard.getAssistants());
                                    else printCloud(actualBoard.getGametable().getClouds());
                                    System.out.print("\n");
                                    actualBoard.printDefaultOnCli();
                                }else if(playerInput.equals("show others")) {
                                    int indexPLayer;
                                    do {
                                        System.out.println("Which player do you want yo show: ");
                                        indexPLayer = Integer.parseInt(stdin.nextLine().replace("\n", ""));
                                    } while (indexPLayer == playerId || indexPLayer >= actualBoard.getNumberOfPLayer());
                                    printSchoolboard(actualBoard.getGametable().getSchoolBoards()[indexPLayer]);
                                    System.out.print("\n");
                                    actualBoard.printDefaultOnCli();
                                } else if (playerId == actualBoard.getPlayerOnTurn()) {
                                    if (playerInput.equals("character") && actualBoard.getState() != 0 && !actualBoard.getAlreadyPLayedCharacter() && actualBoard.getGameMode() == GameMode.EXPERT) {
                                        int cardIndex = -1;
                                        do {
                                            System.out.println("Which character do you want to play: ");
                                            cardIndex = Integer.parseInt(stdin.nextLine().replace("\n", ""));
                                        } while (cardIndex < 0 || cardIndex >= 3);
                                        playerMessage = new ActivateEffectMessage(playerId, cardIndex);
                                    } else if (playerInput.equals("do action")) {
                                        System.out.println(actualBoard.getPlayerMessage());
                                        int playerParameter = Integer.parseInt(stdin.nextLine().replace("\n", ""));
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

    void printIslands(List<Island> islands) {
        for (int i = 0; i < islands.size(); i++) {
            Island island = islands.get(i);
            System.out.println("Island number " + i);
            for (PawnColor color : PawnColor.values()) {
                long numberOfStudents = island.getStudents().stream().filter(s -> (s.getColor() == color)).count();
                System.out.println("Number of " + color + " students is " + numberOfStudents);
            }
        }
    }

    void printSchoolboard(SchoolBoard schoolBoard) {
        System.out.println("Schoolboard");
        for (Student student : schoolBoard.getStudentsFromEntrance()) {
            if(student != null)
                System.out.print(student.getColor() + "\t");
            else
                System.out.print("x" + "\t");
        }
        System.out.print("\n");
        for (PawnColor color : PawnColor.values()) System.out.println("Table " + color + " has " + schoolBoard.getNumberOfStudentsOnTable(color) + " students; " + (schoolBoard.getProfessors().contains(color) ? "There is the professor" : "There isn't the professor"));
        System.out.println("You have " + schoolBoard.getTowers().size() + " towers remaining");
    }

    void printAssistants(List<Assistant> assistants) {
        System.out.println("List of remaining assistants");
        for (Assistant a : assistants) System.out.println("Card value: " + a.getCardValue() + " mother nature moves: " + a.getMotherNatureMoves());
    }

    void printCloud(Cloud[] clouds) {
        for (int i = 0; i < clouds.length; i++) {
            System.out.println("Cloud number " + i);
            for (PawnColor color : PawnColor.values()) {
                long numberOfStudents = clouds[i].getStudents().stream().filter(s -> (s.getColor() == color)).count();
                System.out.println("Number of " + color + " students is " + numberOfStudents);
            }
        }
    }
}