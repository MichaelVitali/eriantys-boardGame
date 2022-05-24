package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.FullTableException;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static java.time.Clock.system;

public class DisplayedBoard implements Serializable {
    private static final long serialVersionUID = 100L;
    private int state;
    private int playerId;
    private String playerMessage;
    private GameTable gametable;
    private List<Assistant> assistants;
    //private List<Wizard> wizards;
    private int playerOnTurn;
    private int numberOfPLayer;
    private GameMode gameMode;
    private boolean alreadyPlayedCharacter;
    private SchoolBoard[] schoolBoards;
    public DisplayedBoard(Game model, int playerId) {
        state = model.getRound().getRoundState();
        this.playerId = playerId;
        playerMessage = model.getPlayer(playerId).getPlayerMessage();
        gametable = model.getGameTable();
        assistants = model.getPlayerAssistant(playerId);
        //wizards = model.getAlreadyChosenWizards();
        playerOnTurn = model.getRound().getPlayerOnTurn();
        numberOfPLayer = model.getNumberOfPlayers();
        gameMode = model.getGameMode();
        alreadyPlayedCharacter = model.getRound().getAlreadyPLayedCharacter();
        schoolBoards = model.getGameTable().getSchoolBoards();
    }

    public GameTable getGametable() {
        return gametable;
    }

    public List<Assistant> getAssistants() {
        return assistants;
    }

    public int getState() {
        return state;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerMessage() {
        return playerMessage;
    }

    //public List<Wizard> getWizards(){ return wizards; }

    public void printDefaultOnCli() {
        printIslands(gametable.getIslands());
        printAssistants(assistants);
        printAllSchoolboards();
        System.out.println("Use comand 'board' to show you board");
        System.out.println("Use command 'Show other' to show other schoolboard");
        if (gameMode == GameMode.EXPERT && playerOnTurn == playerId && state != 0 && !alreadyPlayedCharacter) System.out.println("Use command 'character' to play a character");
        if (playerId == playerOnTurn) System.out.println("Use command 'do action' to do the next possible action");
        else System.out.println("You're not the player on turn... Wait for other player!");
    }

    public int getNumberOfPLayer() {
        return numberOfPLayer;
    }

    public boolean getAlreadyPLayedCharacter() {return alreadyPlayedCharacter; }

    public int getPlayerOnTurn() { return playerOnTurn; }

    public GameMode getGameMode() { return gameMode; }

    public void printStateOnCli() {
        System.out.println("The current round state is: " + state);
    }


    void printIslands(List<Island> islands) {

        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            System.out.print("  /\u203E\u203E\u203E\u203E\u203E\u203E\\    ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.RED);
            String circle = returnCircleUnicodeForColor(PawnColor.RED);
            if (numeberOfStudents < 10) System.out.print(" /  0" + numeberOfStudents + circle +  "  \\   ");
            else System.out.print(" /  " + numeberOfStudents + circle + "  \\   ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.BLUE);
            String circle = returnCircleUnicodeForColor(PawnColor.BLUE);
            if (numeberOfStudents < 10) System.out.print("/   0" + numeberOfStudents + circle + "   \\  ");
            else System.out.print("/   " + numeberOfStudents + circle + "   \\  ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.YELLOW);
            String circle = returnCircleUnicodeForColor(PawnColor.YELLOW);
            if (numeberOfStudents < 10) System.out.print("\u258F   0" + numeberOfStudents + circle + "   \u2595  ");
            else System.out.print("\u258F   " + numeberOfStudents + circle + "   \u2595  ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.GREEN);
            String circle = returnCircleUnicodeForColor(PawnColor.GREEN);
            if (numeberOfStudents < 10) System.out.print("\\   0" + numeberOfStudents + circle + "   /  ");
            else System.out.print("\\    " + numeberOfStudents + circle + "   /  ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.PINK);
            String circle = returnCircleUnicodeForColor(PawnColor.PINK);
            if (numeberOfStudents < 10) System.out.print(" \\  0" + numeberOfStudents + circle + "  /   ");
            else System.out.print(" \\  " + numeberOfStudents + circle + "  /   ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            System.out.print("  \\\u005F\u005F\u005F\u005F\u005F\u005F/    ");
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
        System.out.println("\n\nAssistants:");
        for (Assistant a : assistants) {
            System.out.print("\u2581\u2581\u2581\u2581\u2581\u2581\u2581\u2581     ");
        }
        System.out.println("");
        for (Assistant a : assistants) System.out.print("\u258F      \u2595     ");
        System.out.println("");
        for (Assistant a : assistants) {
            if (a.getCardValue() < 10) System.out.print("\u258F CV:" + a.getCardValue() +   " \u2595     ");
            else System.out.print("\u258F CV:" + a.getCardValue() +   "\u2595     ");
        }
        System.out.println("");
        for (Assistant a : assistants) System.out.print("\u258F MV:" + a.getMotherNatureMoves() + " \u2595     ");
        System.out.println("");
        for (Assistant a : assistants) System.out.print("\u258F      \u2595     ");
        System.out.println("");
        for (Assistant a : assistants) {
            System.out.print("\u2594\u2594\u2594\u2594\u2594\u2594\u2594\u2594     ");
        }
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

    void printAllSchoolboards () {
        System.out.print("\n");
        if (schoolBoards.length == 2) {
            printSchoolboard(0);
            printSchoolboard(1);
        } else if (schoolBoards.length == 3) {
            printSchoolboard(0);
            printSchoolboard(1);
            System.out.print("\n");
            printSchoolboard(2);
        } else {
            printSchoolboard(0);
            printSchoolboard(1);
            System.out.print("\n");
            printSchoolboard(2);
            printSchoolboard(3);
        }
    }

    void printSchoolboard (int playerId) {
        SchoolBoard s = schoolBoards[playerId];
        Student[] entrance = schoolBoards[playerId].getStudentsFromEntrance();
        PawnColor[] color = new PawnColor[entrance.length];
        for (int i = 0; i < entrance.length; i++) {
            if (entrance[i] != null) color[i] = entrance[i].getColor();
        }
        TowerColor towerColor = s.getTowersColor();
        String unicodeTower = returnCircleUnicodeFromColor(towerColor);
        if (numberOfPLayer == 3) {
            System.out.print("╔════════╦═════════════════════════════════╦════╦═══════╗\n" +
                    "║ " + ((color[0] != null) ? returnCircleUnicodeForColor(color[0]) : "  ") + "  " + ((color[1] != null) ? returnCircleUnicodeForColor(color[1]) : "  ") + " ║ " + ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.YELLOW) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " ║ " + (1 <= s.getTowers().size() ? unicodeTower : " ") + " " + (2 <= s.getTowers().size() ? unicodeTower : " ") + "   ║\n" +
                    "║ " + ((color[2] != null) ? returnCircleUnicodeForColor(color[2]) : "  ") + "  " + ((color[3] != null) ? returnCircleUnicodeForColor(color[3]) : "  ") + " ║ " +  ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : " ") + "  " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.BLUE) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " ║ " + (3 <= s.getTowers().size() ? unicodeTower : " ") + " " + (4 <= s.getTowers().size() ? unicodeTower : " ") + "   ║\n" +
                    "║ " + ((color[4] != null) ? returnCircleUnicodeForColor(color[4]) : "  ") + "  " + ((color[5] != null) ? returnCircleUnicodeForColor(color[5]) : "  ") + " ║ " +  ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.GREEN) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " ║ " + (5 <= s.getTowers().size() ? unicodeTower : " ") + " " + (6 <= s.getTowers().size() ? unicodeTower : " ") + "   ║\n" +
                             "║ " + ((color[8] != null) ? returnCircleUnicodeForColor(color[8]) : "  ") + "     ║                                 ║    ║       ║\n" +
                             "╚════════╩═════════════════════════════════╩════╩═══════╝\n");
        } else {
            System.out.print("╔════════╦═════════════════════════════════╦════╦═══════╗\n" +
                             "║ " + ((color[0] != null) ? returnCircleUnicodeForColor(color[0]) : "  ") + "  " + ((color[1] != null) ? returnCircleUnicodeForColor(color[1]) : "  ") + " ║ " + ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.YELLOW)) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.YELLOW) ? returnCircleUnicodeForColor(PawnColor.YELLOW) : "  ") + " ║ " + (1 <= s.getTowers().size() ? unicodeTower : " ") + " " + (2 <= s.getTowers().size() ? unicodeTower : " ") + "   ║\n" +
                             "║ " + ((color[2] != null) ? returnCircleUnicodeForColor(color[2]) : "  ") + "  " + ((color[3] != null) ? returnCircleUnicodeForColor(color[3]) : "  ") + " ║ " +  ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : " ") + "  " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.BLUE)) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.BLUE) ? returnCircleUnicodeForColor(PawnColor.BLUE) : "  ") + " ║ " + (3 <= s.getTowers().size() ? unicodeTower : " ") + " " + (4 <= s.getTowers().size() ? unicodeTower : " ") + "   ║\n" +
                             "║ " + ((color[4] != null) ? returnCircleUnicodeForColor(color[4]) : "  ") + "  " + ((color[5] != null) ? returnCircleUnicodeForColor(color[5]) : "  ") + " ║ " +  ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.GREEN)) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.GREEN) ? returnCircleUnicodeForColor(PawnColor.GREEN) : "  ") + " ║ " + (5 <= s.getTowers().size() ? unicodeTower : " ") + " " + (6 <= s.getTowers().size() ? unicodeTower : " ") + "   ║\n" +
                             "║ " + ((color[6] != null) ? returnCircleUnicodeForColor(color[6]) : "  ") + "     ║ " + ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.RED)) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.RED) ? returnCircleUnicodeForColor(PawnColor.RED) : "  ") + " ║ " + (7 <= s.getTowers().size() ? unicodeTower : " ") + " " + (8 <= s.getTowers().size() ? unicodeTower : " ") + "   ║\n" +
                             "║        ║ " +  ((1 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " +  ((2 <= s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((3 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((4 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((5 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((6 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((7 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((8 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((9 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + ((10 <=  s.getNumberOfStudentsOnTable(PawnColor.PINK)) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " " + "  ║ " + (s.getProfessors().contains(PawnColor.PINK) ? returnCircleUnicodeForColor(PawnColor.PINK) : "  ") + " ║       ║\n" +
                             "╚════════╩═════════════════════════════════╩════╩═══════╝\n");
        }

    }

    private String returnCircleUnicodeForColor (PawnColor color) {
        switch (color.getIndex()) {
            case 0:
                return "\uD83D\uDFE1";
            case 1:
                return "\uD83D\uDD35";
            case 2:
                return "\uD83D\uDFE2";
            case 3:
                return "\uD83D\uDD34";
            case 4:
                return "\uD83D\uDFE3";
        }
        return "";
    }

    private String returnCircleUnicodeFromColor (TowerColor color) {
        switch (color.getIndex()) {
            case 0:
                return "\u26AA";
            case 1:
                return "\u26AB";
            case 2:
                return "\u25EF";
        }
        return "";
    }
}
