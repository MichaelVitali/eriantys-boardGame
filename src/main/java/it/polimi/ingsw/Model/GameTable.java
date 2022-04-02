package it.polimi.ingsw.Model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameTable {
    private final int numberOfPlayers;
    private final Player[] players;
    private List<Island> islands;
    private Cloud[] clouds;
    private SchoolBoard[] schoolBoards;
    private Assistant[] assistants; /////////non mi ricordo come li utilizzavamo quindi non ci saranno nei metodi
    private int motherNaturePosition;

    public GameTable(int numberOfPlayers, Player[] players) {
        // if(numberOfPlayers > 4 || numberOfPlayers <= 1;) throw new Exception();
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++)
            this.players[i] = players[i];

        List<Island> islands = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            islands.add(new Island(i));
        }

        int numberOfStudentsOnClouds;
        int numberOfStudentsOnEntrance;
        int numberOfTowers;
        if(numberOfPlayers == 3) {
            numberOfStudentsOnClouds = 4;
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 6;
        }else {
            numberOfStudentsOnClouds = 3;
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 8;
        }
        clouds = new Cloud[numberOfPlayers];
        for(Cloud cloud : clouds)
            cloud = new Cloud(numberOfStudentsOnClouds);
        addStudentsOnClouds();

        schoolBoards = new SchoolBoard[numberOfPlayers];
        switch(numberOfPlayers) {
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
        for(int i = 0; i < numberOfPlayers; i++)
            players[i].addSchoolBoard(schoolBoards[i]);

        assistants = new Assistant[numberOfPlayers * 10]; /////va controllato
        ////inizializzazione degli assistant
        List<Assistant> listAssistants = createAssistant();
        for(int i = 0; i < numberOfPlayers; i++)
            players[i].addAssistants(listAssistants);

        motherNaturePosition = 0;
    }

    public Player[] getPlayers(){
        return players;
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

    public void addStudentsOnClouds() {
        for(int i = 0; i < numberOfPlayers; i++)
            clouds[i].addStudents(Bag.getBag().drawStudents(clouds[i].getNumberOfStudents()));
    }

    public void moveProfessorToTheRightPosition(PawnColor colorOfTheMovedStudent) {
        int indexOfTheProfessor = -1;
        int[] studentsOnTheTable = new int[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++) {
            studentsOnTheTable[i] = schoolBoards[i].getNumberOfStudentsOnTable(colorOfTheMovedStudent);
            if(schoolBoards[i].getProfessors().contains(colorOfTheMovedStudent))
                indexOfTheProfessor = i;
        }
        if(indexOfTheProfessor == -1) {
            for(int i = 0; i < numberOfPlayers; i++) {
                if(studentsOnTheTable[i] > 0)
                    schoolBoards[i].setProfessor(colorOfTheMovedStudent, true);
                break;
            }
        }else {
            for(int i = 0; i < numberOfPlayers; i++) {
                if(studentsOnTheTable[i] > studentsOnTheTable[indexOfTheProfessor]) {
                    schoolBoards[i].setProfessor(colorOfTheMovedStudent, true);
                    schoolBoards[indexOfTheProfessor].setProfessor(colorOfTheMovedStudent, false);
                    break;
                }
            }
        }
    }

    public void addStudentOnIsland(Student s, int islandIndex) {
        if(islandIndex >= 0 && islandIndex < islands.size())
            this.islands.get(islandIndex).addStudents(s);

    }

    public void changeMotherNaturePosition(int newPosition) {
        motherNaturePosition = newPosition;
        putTowerOrChangeColorIfNecessary();
    }

    private void putTowerOrChangeColorIfNecessary() {
        int[] influences = calculateInfluences(); // pay attention : influence.length isn't numberOfPlayers
        Tower towerOnTheIsland = islands.get(motherNaturePosition).getTowers().get(0);
        int maxInfluence = -1, indexMaxInfluence = -1;
        if(towerOnTheIsland != null) {
            maxInfluence = influences[islands.get(motherNaturePosition).getTowers().get(0).getColor().getIndex()];
            indexMaxInfluence = islands.get(motherNaturePosition).getTowers().get(0).getColor().getIndex();
        }
        for (int i = 0; i < influences.length; i++) {
            if (influences[i] > maxInfluence) {
                maxInfluence = influences[i];
                indexMaxInfluence = i;
            }
        }
        if(indexMaxInfluence < 0 || maxInfluence < 0) { } // exception
        try {
            if(towerOnTheIsland == null) {
                boolean equalInfluenceToOtherPlayer = false;
                for (int i = 0; i < influences.length; i++) {
                    if (maxInfluence == influences[i] && indexMaxInfluence != i) {
                        equalInfluenceToOtherPlayer = true;
                        break;
                    }
                }
                if(!equalInfluenceToOtherPlayer) {
                    islands.get(motherNaturePosition).setTowers(schoolBoards[indexMaxInfluence].removeTowers(1));
                    mergeIslandsIfNecessary();
                }
            }else
                if(indexMaxInfluence != towerOnTheIsland.getColor().getIndex()) {
                    int numberOfRequiredTowers = islands.get(motherNaturePosition).getAggregation();
                    List<Tower> previousTowers = islands.get(motherNaturePosition).removeTowers();
                    schoolBoards[towerOnTheIsland.getColor().getIndex()].addTowers(previousTowers);
                    islands.get(motherNaturePosition).setTowers(schoolBoards[indexMaxInfluence].removeTowers(numberOfRequiredTowers));
                    mergeIslandsIfNecessary();
                }
        }catch(InvalidNumberException e) {
            e.printStackTrace();
        }catch(NoMoreTowersException e) {
            // qualcuno dovrebbe vincere
        }
        if(islands.get(motherNaturePosition).getTowers() == null) { } // exception
    }

    private int[] calculateInfluences() {
        int numberOfIteration = numberOfPlayers == 4 ? 2 : numberOfPlayers;
        int[] influence = new int[numberOfIteration];
        List<Student> studentsOnTheIslands = islands.get(motherNaturePosition).getStudents();
        List<Tower> towersOnTheIslands = islands.get(motherNaturePosition).getTowers();
        influence[0] = 0;

        /*
        * Controllo che tutte le torri siano dello stesso colore
        TowerColor colorOfTheTowers = towersOnTheIslands.get(0).getColor();
        for(Tower tower : towersOnTheIslands) {
            if(tower.getColor() != colorOfTheTowers) { } //exception
        }
        */

        for(int i = 0; i < numberOfIteration; i++) {
            influence[i] = 0;
            List<PawnColor> professors = schoolBoards[i].getProfessors();
            if (numberOfPlayers == 4) professors.addAll(schoolBoards[i + 2].getProfessors());
            for (PawnColor professor : professors) {
                for (Student student : studentsOnTheIslands) {
                    if (professor == student.getColor()) influence[i]++;
                }
            }
            if (towersOnTheIslands != null) {
                if (schoolBoards[i].getTowers().size() <= 0) { } //exception
                if (schoolBoards[i].getTowers().get(0).getColor() == towersOnTheIslands.get(0).getColor())
                    influence[i] += islands.get(motherNaturePosition).getAggregation();
            }
        }
        return influence;
    }

    private void mergeIslandsIfNecessary() {
        Tower towerOnTheIsland = islands.get(motherNaturePosition).getTowers().get(0);
        Tower towerOnTheNextIsland = islands.get(motherNaturePosition).getTowers().get(0);
        Tower towerOnThePreviousIsland = islands.get(motherNaturePosition).getTowers().get(0);
        if(towerOnTheIsland.getColor() == towerOnTheNextIsland.getColor()) {
            islands.set(motherNaturePosition, new MergedIslands(islands.get(motherNaturePosition), islands.get(motherNaturePosition + 1)));
            islands.remove(islands.get(motherNaturePosition + 1));
        }
        if(towerOnTheIsland.getColor() == towerOnThePreviousIsland.getColor()) {
            islands.set(motherNaturePosition - 1, new MergedIslands(islands.get(motherNaturePosition - 1), islands.get(motherNaturePosition)));
            islands.remove(islands.get(motherNaturePosition));
            motherNaturePosition--;
        }
        for(Island island : islands){
            if(island == null) { } // exception
        }
    }

    public List<Student> getStudentsOnCloud(int cloudIndex) {
        if(clouds[cloudIndex] == null) { }//exception ("This cloud doesn't have students on it")
        List<Student> studentsOnTheCloud = null;
        if(cloudIndex >= 0 && cloudIndex < clouds.length) {
            studentsOnTheCloud = clouds[cloudIndex].getStudents();
        }
        return studentsOnTheCloud;
    }
}