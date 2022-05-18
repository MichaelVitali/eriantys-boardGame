package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;

import java.io.Serializable;
import java.util.List;

public class DisplayedBoard implements Serializable {
    private static final long serialVersionUID = 100L;
    private int state;
    private int playerId;
    private String playerMessage;
    private GameTable gametable;
    private List<Assistant> assistants;
    private int playerOnTurn;
    private int numberOfPLayer;
    private GameMode gameMode;
    private boolean alreadyPlayedCharacter;

    public DisplayedBoard(Game model, int playerId) {
        state = model.getRound().getRoundState();
        this.playerId = playerId;
        playerMessage = model.getPlayer(playerId).getPlayerMessage();
        gametable = model.getGameTable();
        assistants = model.getPlayerAssistant(playerId);
        playerOnTurn = model.getRound().getPlayerOnTurn();
        numberOfPLayer = model.getNumberOfPlayers();
        gameMode = model.getGameMode();
        alreadyPlayedCharacter = model.getRound().getAlreadyPLayedCharacter();
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



    public void printDefaultOnCli() {
        printIslands(gametable.getIslands());
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
            System.out.print("   ______     ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.YELLOW);
            if (numeberOfStudents < 10) System.out.print("  / 0" + numeberOfStudents + "\uD83D\uDFE1 \\    ");
            else System.out.print("  / " + numeberOfStudents + "\uD83D\uDFE1   \\  ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.RED);
            if (numeberOfStudents < 10) System.out.print(" /  0" + numeberOfStudents + "\uD83D\uDD34  \\   ");
            else System.out.print(" /  " + numeberOfStudents + "\uD83D\uDD34  \\   ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.BLUE);
            if (numeberOfStudents < 10) System.out.print("/   0" + numeberOfStudents + "\uD83D\uDD35   \\  ");
            else System.out.print("/   " + numeberOfStudents + "\uD83D\uDD35   \\  ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.GREEN);
            if (numeberOfStudents < 10) System.out.print("\\   0" + numeberOfStudents + "\uD83D\uDFE2   /  ");
            else System.out.print("\\    " + numeberOfStudents + "\uD83D\uDFE2   /  ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            int numeberOfStudents = islands.get(i).getNumberOfStudentsForColor(PawnColor.PINK);
            if (numeberOfStudents < 10) System.out.print(" \\  0" + numeberOfStudents + "\uD83D\uDFE3  /   ");
            else System.out.print(" \\  " + numeberOfStudents +"\uD83D\uDFE3  /   ");
        }
        System.out.println("");
        for (int i = 0; i < islands.size(); i++) {
            System.out.print("  \\\u005F\u005F\u005F\u005F\u005F\u005F/    ");
        }
        System.out.println("");
        /*
            System.out.println("\t\\          /");
            System.out.println("\t \\        /");
            System.out.println("\t  \\      /");
            System.out.println("\t   ------");
            Island island = islands.get(i);
            System.out.println("Island number " + i);
            for (PawnColor color : PawnColor.values()) {
                long numberOfStudents = island.getStudents().stream().filter(s -> (s.getColor() == color)).count();
                System.out.println("Number of " + color + " students is " + numberOfStudents);
            }*/
        //}
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
