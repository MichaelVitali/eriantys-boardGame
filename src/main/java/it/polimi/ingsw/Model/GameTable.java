package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.exception.*;

import java.util.ArrayList;
import java.util.List;

public class GameTable {
    private int numberOfPlayers;
    private List<Island> islands;
    private Cloud[] clouds;
    private SchoolBoard[] schoolBoards;
    private int motherNaturePosition;
    private boolean victory;
    private boolean draw;
    private TowerColor winner;

    public GameTable() { }

    public GameTable(int numberOfPlayers, SchoolBoard[] schoolBoards) {
        this.numberOfPlayers = numberOfPlayers;

        createIslands();
        createClouds(numberOfPlayers);

        this.schoolBoards = schoolBoards;

        motherNaturePosition = 0;
        victory = false;
        draw = false;
        winner = null;
    }

    private void createIslands() {
        islands = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            islands.add(new Island(i));
        }
    }

    private void createClouds(int numberOfPlayers) {
        int numberOfStudentsOnClouds = 3;
        if (numberOfPlayers == 3)
            numberOfStudentsOnClouds = 4;
        clouds = new Cloud[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++)
            clouds[i] = new Cloud(numberOfStudentsOnClouds);
    }

    /*messo solo perché serve nei decorator*/
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /*messo solo perché serve nei decorator*/
    public List<Island> getIslands() {
        return islands;
    }

    /*messo solo perché serve nei decorator*/
    public SchoolBoard[] getSchoolBoards() {
        return schoolBoards;
    }

    public int getMotherNaturePosition() {
        return motherNaturePosition;
    }

    public int getNumberOfIslands() {
        return islands.size();
    }

    public boolean isVictory() {
        return victory;
    }

    public boolean isDraw() {
        return draw;
    }

    public void addStudentsOnClouds() throws EmptyBagException {
            for (int i = 0; i < numberOfPlayers; i++)
                clouds[i].addStudents(Bag.getBag().drawStudents(clouds[i].getNumberOfStudents()));
    }

    public void moveProfessorToTheRightPosition(PawnColor colorOfTheMovedStudent) {
        int indexOfTheProfessor = -1;
        int[] studentsOnTheTable = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            studentsOnTheTable[i] = schoolBoards[i].getNumberOfStudentsOnTable(colorOfTheMovedStudent);
            if(schoolBoards[i].getProfessors().contains(colorOfTheMovedStudent))
                indexOfTheProfessor = i;
        }
        if (indexOfTheProfessor == -1) {
            for(int i = 0; i < numberOfPlayers; i++) {
                if(studentsOnTheTable[i] > 0) {
                    schoolBoards[i].setProfessor(colorOfTheMovedStudent, true);
                    break;
                }
            }
        } else {
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

    public void changeMotherNaturePosition(int newPosition) throws InvalidIndexException {
        if (newPosition >= islands.size()) throw new InvalidIndexException("Wrong new index of mother nature");
        motherNaturePosition = newPosition;
    }

    protected void putTowerOrChangeColorIfNecessary() {
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
        if(indexMaxInfluence < 0 || maxInfluence <= 0) { } // exception
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
                    if(islands.get(motherNaturePosition).getTowers().isEmpty()) throw new NoMoreTowersException(islands.get(motherNaturePosition).getTowers().get(0).getColor());
                    mergeIslandsIfNecessary();
                }
            } else
                if(indexMaxInfluence != towerOnTheIsland.getColor().getIndex()) {
                    int numberOfRequiredTowers = islands.get(motherNaturePosition).getAggregation();
                    List<Tower> previousTowers = islands.get(motherNaturePosition).removeTowers();
                    schoolBoards[towerOnTheIsland.getColor().getIndex()].addTowers(previousTowers);
                    islands.get(motherNaturePosition).setTowers(schoolBoards[indexMaxInfluence].removeTowers(numberOfRequiredTowers));
                    if(islands.get(motherNaturePosition).getTowers().isEmpty()) throw new NoMoreTowersException(islands.get(motherNaturePosition).getTowers().get(0).getColor());
                    if(islands.size() <= 3) throw new ThreeOrLessIslandException();
                    mergeIslandsIfNecessary();
                }
        }catch(InvalidIndexException e) {
            e.printStackTrace();
        }catch(NoMoreTowersException e) {
            victory = true;
            winner = e.getEmptySchoolboardColor();
        }catch(ThreeOrLessIslandException e) {
            victory = false;
            List<TowerColor> possibleWinner = teamWithLessTowersOnSchoolboards();
            if(possibleWinner.size() > 1) {
                possibleWinner = teamWithMoreProfessors(possibleWinner);
                if (possibleWinner.size() > 1) {
                    draw = true;
                } else {
                    victory = true;
                    winner = possibleWinner.get(0);
                }
            }else {
                victory = true;
                winner = possibleWinner.get(0);
            }
        }
        if(islands.get(motherNaturePosition).getTowers() == null) { } // exception
    }

    public int[] calculateInfluences() {
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

    public void mergeIslandsIfNecessary() {
        List<Tower> towersOnTheIsland = islands.get(motherNaturePosition).getTowers();
        List<Tower> towersOnTheNextIsland = islands.get((motherNaturePosition + 1) % islands.size()).getTowers();
        List<Tower> towersOnThePreviousIsland = islands.get((motherNaturePosition + 1) % islands.size()).getTowers();
        if(towersOnTheNextIsland.size() > 0 && towersOnTheIsland.get(0).getColor() == towersOnTheNextIsland.get(0).getColor()) {
            islands.set(motherNaturePosition, new MergedIslands(islands.get(motherNaturePosition), islands.get((motherNaturePosition + 1) % islands.size())));
            islands.remove(islands.get((motherNaturePosition + 1) % islands.size()));
        }
        if(towersOnThePreviousIsland.size() > 0 && towersOnTheIsland.get(0).getColor() == towersOnThePreviousIsland.get(0).getColor()) {
            islands.set((motherNaturePosition - 1) % islands.size(), new MergedIslands(islands.get((motherNaturePosition - 1) % islands.size()), islands.get(motherNaturePosition)));
            islands.remove(islands.get(motherNaturePosition));
            motherNaturePosition = (motherNaturePosition - 1) % islands.size();
        }
        for(Island island : islands){
            if(island == null) { } // exception
        }
    }

    public List<Student> getStudentsOnCloud(int cloudIndex) throws EmptyCloudException {
        if(clouds[cloudIndex].isEmpty()) throw new EmptyCloudException();
        List<Student> studentsOnTheCloud = new ArrayList<>();
        if(cloudIndex >= 0 && cloudIndex < clouds.length) {
            studentsOnTheCloud.addAll(clouds[cloudIndex].removeStudents());
        }
        return studentsOnTheCloud;
    }

    public List<TowerColor> teamWithLessTowersOnSchoolboards() {
        List<TowerColor> teamColor = new ArrayList<>();
        int minimumNumberOfTowerOnSchoolboards = 9;
        int numberOfIterations = 2;
        if(numberOfPlayers == 3) numberOfIterations = numberOfPlayers;
        for (int i = 0; i < numberOfIterations; i++) {
            if (minimumNumberOfTowerOnSchoolboards > schoolBoards[i].getTowers().size())
                minimumNumberOfTowerOnSchoolboards = schoolBoards[i].getTowers().size();
        }
        for(int i = 0; i < numberOfIterations; i++) {
            if(minimumNumberOfTowerOnSchoolboards == schoolBoards[i].getTowers().size())
                teamColor.add(schoolBoards[i].getTowersColor());
        }
        return teamColor;
    }

    protected List<TowerColor> teamWithMoreProfessors(List<TowerColor> teamWithLessTowersOnSchoolboards) {
        List<TowerColor> teamColor = null;
        int maximumNumberOfProfessors = -1;
        int[] numberOfProfessors = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++)
            numberOfProfessors[i] = schoolBoards[i].getProfessors().size();
        if(numberOfPlayers == 4) {
            int[] numberOfProfessorsForFourPlayers = new int[2];
            numberOfProfessorsForFourPlayers[0] = numberOfProfessors[0] + numberOfProfessors[2];
            numberOfProfessorsForFourPlayers[1] = numberOfProfessors[1] + numberOfProfessors[3];
            numberOfProfessors = numberOfProfessorsForFourPlayers;
        }
        for (int i = 0; i < numberOfProfessors.length; i++) {
            if (maximumNumberOfProfessors == numberOfProfessors[i]) {
                teamColor = null;
                break;
            }else if (maximumNumberOfProfessors < numberOfProfessors[i]) {
                maximumNumberOfProfessors = numberOfPlayers;
                teamColor = schoolBoards[i].getTowersColor();
            }
        }
        ///////////////////////////////////////////////
        int i = 0;

        /*das/d/asd/asd/asd/as/da*/

        return teamColor;
    }

    public void addStudentOnTableFromEntrance(int indexStudent, int schoolBoardIndex) {
        this.schoolBoards[schoolBoardIndex].addStudentOnTable(indexStudent);
    }

    public void addSchoolBoards(SchoolBoard[] schoolBoards) {
        if(schoolBoards != null)
            this.schoolBoards = schoolBoards;
    }

    public GameTable getGameTableInstance(){
        return this;
    }
}