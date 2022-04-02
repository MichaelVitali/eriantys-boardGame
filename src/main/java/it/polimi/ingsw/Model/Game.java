package it.polimi.ingsw.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameTable gameTable;
    private int numberOfPlayers;
    private Player[] players;
    private Assistant[] assistants;
    //private GameMode mode;
    private Round round;

    Game() { }

    Game(int numberOfPlayers) { //, GameMode mode) {
        // this.mode = mode;
        this.numberOfPlayers = numberOfPlayers;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    private void createGameTable() {

        assistants = new Assistant[numberOfPlayers * 10];
        List<Assistant> listAssistants = createAssistant();
        for(int i = 0; i < numberOfPlayers; i++)
            players[i].addAssistants(listAssistants);

        SchoolBoard[] schoolBoards = new SchoolBoard[numberOfPlayers];

        int numberOfStudentsOnEntrance;
        int numberOfTowers;
        if (numberOfPlayers == 3) {
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 6;
        } else {
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 8;
        }

        switch (numberOfPlayers) {
            case 2:
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                break;
            case 3:
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[3] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.GREY, numberOfTowers);
                break;
            case 4:
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[3] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, 0);
                schoolBoards[4] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, 0);
                break;
        }


        /*gameTable = new GameTable(numberOfPlayers, schoolBoards);
        for(int i = 0; i < numberOfPlayers; i++)
            players[i].addSchoolBoard(schoolBoards[i]);
    */
    }

    private List<Assistant> createAssistant(){
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

    private void createPlayer(String nickname, int id) {
        Player p = new Player(nickname, id);
        if(id>=0 && id<numberOfPlayers)
            players[id]=p;
    }

    public Player getPlayer(int id){
        //ECCEZIONE SU ID>=0 O ID<numberOfPlayers
        return this.players[id];
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    private boolean checkEndgame(){
        //TODO
        return false;
    }

    public void endGame(){
        //TODO
    }

    public Round startRound(){
        this.round=new Round(this);
        return round;
    }

    public Round getRound(){
        return this.round;
    }

    public void endRound(){
        //TODO
    }

}
