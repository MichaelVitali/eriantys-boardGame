package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.exception.EmptyBagException;
import it.polimi.ingsw.Model.exception.InvalidIndexException;
import it.polimi.ingsw.Model.exception.NoMoreStudentsException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final int numberOfPlayers;
    private GameTable gameTable;
    private List<Assistant>[] assistants;
    private Player[] players;
    private Round round;

    Game(int numberOfPlayers) { this.numberOfPlayers = numberOfPlayers; }

    Game(int numberOfPlayers, String[] nicknames) {
        this.numberOfPlayers = numberOfPlayers;

        gameTable = createGameTable(numberOfPlayers);

        assistants = new ArrayList[numberOfPlayers];
        List<Assistant> assistantsList = createAssistants();
        for (int i = 0; i < numberOfPlayers; i++) {
            assistants[i] = new ArrayList<>();
            assistants[i].addAll(assistantsList);
        }

        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++)
            players[i] = new Player(nicknames[i], i, assistantsList);

        round = startRound();

    }

    private GameTable createGameTable(int numberOfPlayers) {
        SchoolBoard[] schoolBoards = new SchoolBoard[numberOfPlayers];

        int numberOfStudentsOnEntrance;
        int numberOfTowers;
        if (numberOfPlayers == 3) {
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 6;
        } else {
            numberOfStudentsOnEntrance = 7;
            numberOfTowers = 8;
        }

        switch (numberOfPlayers) {
            case 2:
                schoolBoards[0] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                break;
            case 3:
                schoolBoards[0] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.GREY, numberOfTowers);
                break;
            case 4:
                schoolBoards[0] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, 0);
                schoolBoards[3] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, 0);
                break;
        }

        return new GameTable(numberOfPlayers, schoolBoards);
    }

    private List<Assistant> createAssistants(){
        JSONParser parser = new JSONParser();
        List<Assistant> l = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\Mike\\IdeaProjects\\project_ingsw\\src\\main\\java\\it\\polimi\\ingsw\\Model\\Assistant.js"));
            for (Object o : a) {
                JSONObject assistant = (JSONObject) o;
                int  cardValue =  Integer.parseInt((String) assistant.get("cardValue"));
                int motherNatureMoves = Integer.parseInt((String) assistant.get("motherNatureMoves"));
                l.add(new Assistant(cardValue, motherNatureMoves));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public Player getPlayer(int playerId) {
        try {
            if (playerId < 0 || playerId >= numberOfPlayers) throw new InvalidIndexException("This player doesn't exit");
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }
        return this.players[playerId];
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public boolean isAValidPositionForMotherNature(int position) {
        if(0 <= position && position < gameTable.getIslands().size()) return true;
        return false;
    }

    public Round getRound() {
        return round;
    }

    public Round startRound() {
        round = new Round(this);
        try {
            gameTable.addStudentsOnClouds();
        } catch (EmptyBagException e) {
            // Va gestita ad hoc perché deve cambiare il funzionamento di gioco
        }
        return round;
    }

    public Round startRound(int[] playerOrder) {
        round = new Round(this, playerOrder);
        try {
            gameTable.addStudentsOnClouds();
        } catch (EmptyBagException e) {
            // Va gestita ad hoc perché deve cambiare il funzionamento di gioco
        }
        return round;
    }

    public void endRound() {
        if(checkEndgame()) endGame();;
    }

    private boolean checkEndgame() {
        if(gameTable.isVictory() || gameTable.isVictory()) return true;
        return false;
    }

    public void endGame() {
        //TODO
    }
}