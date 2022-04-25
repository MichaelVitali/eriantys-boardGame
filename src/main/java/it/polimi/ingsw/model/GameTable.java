package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;
import it.polimi.ingsw.server.ClientConnection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameTable {
    private int numberOfPlayers;
    private List<Island> islands;
    private Cloud[] clouds;
    private SchoolBoard[] schoolBoards;
    private int motherNaturePosition;
    private boolean victory;
    private boolean draw;
    private TowerColor winner;
    private Bag bag;

    public GameTable() { }

    public GameTable(int numberOfPlayers, SchoolBoard[] schoolBoards, Bag bag) {
        this.numberOfPlayers = numberOfPlayers;

        islands = createIslands();
        clouds = createClouds(numberOfPlayers);

        this.schoolBoards = schoolBoards;
        this.bag = bag;
        try{
            for(SchoolBoard s : this.schoolBoards) s.addStudentsOnEntrance(this.bag.drawStudents(s.getNumberOfStudentsOnEntrance()));
        }catch (EmptyBagException e){
            e.printStackTrace();
        }

        Random random = new Random();
        motherNaturePosition = random.nextInt(12);
        victory = false;
        draw = false;
        winner = null;
    }
    /**
     * Creates the 12 Island used in the game
     */
    public List<Island> createIslands() {
        List<Island> islands = new ArrayList<>();
        islands = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            islands.add(new Island(i));
        }
        return islands;
    }

    /**
     * Creates clouds used in the game. The clouds are one for each player
     * @param numberOfPlayers number of player in the game
     */
    private Cloud[] createClouds(int numberOfPlayers) {
        int numberOfStudentsOnClouds = 3;
        if (numberOfPlayers == 3)
            numberOfStudentsOnClouds = 4;
        Cloud[] clouds = new Cloud[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++)
            clouds[i] = new Cloud(numberOfStudentsOnClouds);
        return clouds;
    }

    /**
     * Returns the number of players
     * @return numberOfPlayers
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Returns the list of islands, including mergeIslands
     * @return list of islands
     */
    public List<Island> getIslands() {
        return islands;
    }


    public int getNumberOfClouds(){ return clouds.length; }
    /**
     * @return an array with the schoolboards
     */

    public SchoolBoard[] getSchoolBoards() {
        return schoolBoards;
    }

    /**
     * @return current mother nature position
     */
    public int getMotherNaturePosition() {
        return motherNaturePosition;
    }

    /**
     * @return the number of island/merged island
     */
    public int getNumberOfIslands() {
        return islands.size();
    }

    public void setVictory(){
        victory=true;
    }

    public void setDraw(){
        draw=true;
    }

    public boolean isVictory() {
        return victory;
    }

    public boolean isDraw() {
        return draw;
    }

    /**
     * Takes new students from the bag and adds the students on the clouds. The number of students change with the number of players
     * @throws EmptyBagException if there are no more students on the bag
     */
    public void addStudentsOnClouds() throws EmptyBagException {
            for (int i = 0; i < numberOfPlayers; i++)
                clouds[i].addStudents(bag.drawStudents(clouds[i].getNumberOfStudents()));
    }

    /**
     * Calculates wich players has to take the professor. If another player had it, the professor is swapped.
     * If one player had the professor and all the players have the same number of students for this color, the professor will not move.
     * @param colorOfTheMovedStudent that is the color of the professor to control the position
     */
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

    /**
     * Adds a student on an island
     * @param student student to put on the island
     * @param islandIndex index of the island where has to be put the student. The index is the index of the island from 0 to 11 (beginning indexes)
     * @throws InvalidIndexException if in the set of island there is no islands with an index that match with the index passed as parameter
     */
    public void addStudentOnIsland(Student student, int islandIndex) throws InvalidIndexException {
        if(islandIndex < 0 || islandIndex >= 12) throw new InvalidIndexException("The island index " + islandIndex + " doesn't exists");
        int index = -1;
        for(int i = 0; i < islands.size(); i++) {
            if(islands.get(i).getIndex().contains(islandIndex)) {
                index = i;
                break;
            }
        }
        try {
            if(index == -1) throw new OutOfBoundException("Errore in addStudents of GameTable");
            this.islands.get(index).addStudents(student, islandIndex);
        } catch (OutOfBoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InvalidIndexException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Changes the mother nature position with the new position of the island where it is.
     * @param newPosition index of the island where is the mother nature
     * @throws InvalidIndexException if the position isn't one of the island
     */
    public void changeMotherNaturePosition(int newPosition) throws InvalidIndexException {
        if (newPosition >= islands.size()) throw new InvalidIndexException("Wrong new index of mother nature");
        motherNaturePosition = newPosition;
    }

    /**
     * Calculates if, after the movement of mother nature, the player with the highest influence is the same. If not the towers on the island are changed
     * with the towers of the player with the highest influence. The old towers on the island are added to his player on his schoolboard.
     */
    public void putTowerOrChangeColorIfNecessary() {
        Tower towerOnTheIsland;
        int[] influences = calculateInfluences(); // pay attention : influence.length isn't numberOfPlayers
        if (islands.get(motherNaturePosition).getTowers().size() == 0) towerOnTheIsland = null;
        else towerOnTheIsland = islands.get(motherNaturePosition).getTowers().get(0);
        int maxInfluence = -1, indexMaxInfluence = -1;

        if(towerOnTheIsland != null) {
            maxInfluence = influences[islands.get(motherNaturePosition).getTowers().get(0).getColor().getIndex()];
            indexMaxInfluence = islands.get(motherNaturePosition).getTowers().get(0).getColor().getIndex();
        }

        for(int i = 0; i < influences.length; i++) {
            if (influences[i] > maxInfluence) {
                maxInfluence = influences[i];
                indexMaxInfluence = i;
            }
        }
        if (indexMaxInfluence < 0 || maxInfluence < 0) {}
        else {
            try {
                if (towerOnTheIsland == null) {
                    boolean equalInfluenceToOtherPlayer = false;
                    for (int i = 0; i < influences.length; i++) {
                        if (maxInfluence == influences[i] && indexMaxInfluence != i) {
                            equalInfluenceToOtherPlayer = true;
                            break;
                        }
                    }
                    if (!equalInfluenceToOtherPlayer) {
                        islands.get(motherNaturePosition).setTowers(schoolBoards[indexMaxInfluence].removeTowers(1));
                        mergeIslandsIfNecessary();
                    }
                } else if (indexMaxInfluence != towerOnTheIsland.getColor().getIndex()) {
                    int numberOfRequiredTowers = islands.get(motherNaturePosition).getAggregation();
                    List<Tower> previousTowers = islands.get(motherNaturePosition).removeTowers();
                    schoolBoards[towerOnTheIsland.getColor().getIndex()].addTowers(previousTowers);
                    islands.get(motherNaturePosition).setTowers(schoolBoards[indexMaxInfluence].removeTowers(numberOfRequiredTowers));
                    if (islands.get(motherNaturePosition).getTowers().isEmpty())
                        throw new NoMoreTowersException(islands.get(motherNaturePosition).getTowers().get(0).getColor());
                    if (islands.size() <= 3) throw new ThreeOrLessIslandException();
                    mergeIslandsIfNecessary();
                }
            } catch (InvalidIndexException e) {
                e.printStackTrace();
            } catch (NoMoreTowersException e) {
                victory = true;
                winner = e.getEmptySchoolboardColor();
            } catch (ThreeOrLessIslandException e) {
                victory = false;
                List<TowerColor> possibleWinner = teamWithLessTowersOnSchoolboards();
                if (possibleWinner.size() > 1) {
                    possibleWinner = teamWithMoreProfessors(possibleWinner);
                    if (possibleWinner.size() > 1) {
                        draw = true;
                    } else {
                        victory = true;
                        winner = possibleWinner.get(0);
                    }
                } else {
                    victory = true;
                    winner = possibleWinner.get(0);
                }
            }
        }

        if(islands.get(motherNaturePosition).getTowers() == null) { } // exception
    }

    /**
     * Calculate all the influences related to the players on the island where mother nature is moved.
     * @return an array with all the players influence
     */
    public int[] calculateInfluences() {
        int numberOfIteration = (numberOfPlayers == 4) ? 2 : numberOfPlayers;
        int[] influence = new int[numberOfIteration];
        List<Student> studentsOnTheIslands = islands.get(motherNaturePosition).getStudents();
        List<Tower> towersOnTheIslands = islands.get(motherNaturePosition).getTowers();
        Arrays.fill(influence, 0);

        for(int i = 0; i < numberOfIteration; i++) {
            influence[i] = 0;
            List<PawnColor> professors = schoolBoards[i].getProfessors();
            if (numberOfPlayers == 4) professors.addAll(schoolBoards[i + 2].getProfessors());
            for (PawnColor professor : professors) {
                for (Student student : studentsOnTheIslands) {
                    if (professor == student.getColor()) influence[i]++;
                }
            }
            if (towersOnTheIslands.size() > 0) {
                if (schoolBoards[i].getTowers().size() <= 0) { } //exception
                else if (schoolBoards[i].getTowers().get(0).getColor() == towersOnTheIslands.get(0).getColor())
                    influence[i] += islands.get(motherNaturePosition).getAggregation();
            }
        }
        return influence;
    }

    /**
     * Calculates if there are two island close to each other with the same color tower. If there are, they are merged.
     */
    public void mergeIslandsIfNecessary() {
        List<Tower> towersOnTheIsland = islands.get(motherNaturePosition).getTowers();
        List<Tower> towersOnTheNextIsland = islands.get((motherNaturePosition + 1) % islands.size()).getTowers();
        List<Tower> towersOnThePreviousIsland = islands.get((motherNaturePosition == 0 ? islands.size() -1 : motherNaturePosition - 1)).getTowers();
        if(towersOnTheNextIsland.size() > 0 && towersOnTheIsland.get(0).getColor() == towersOnTheNextIsland.get(0).getColor()) {
            islands.set(motherNaturePosition, new MergedIslands(islands.get(motherNaturePosition), islands.get((motherNaturePosition + 1) % islands.size())));
            islands.remove(islands.get((motherNaturePosition + 1) % islands.size()));
        }
        if(towersOnThePreviousIsland.size() > 0 && towersOnTheIsland.get(0).getColor() == towersOnThePreviousIsland.get(0).getColor()) {
            islands.set((motherNaturePosition == 0 ? islands.size() -1 : motherNaturePosition - 1), new MergedIslands(islands.get((motherNaturePosition == 0 ? islands.size() -1 : motherNaturePosition - 1)), islands.get(motherNaturePosition)));
            islands.remove(islands.get(motherNaturePosition));
            motherNaturePosition = (motherNaturePosition == 0) ? islands.size() -1 : motherNaturePosition - 1;
        }
    }

    /**
     * Return all the student on a cloud. Then all the students on the cloud are removed.
     * @param cloudIndex index of the cloud where I have to take the students
     * @return a list with all the students on the cloud
     * @throws EmptyCloudException if there isn't any student on the cloud
     */
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

    public List<TowerColor> teamWithMoreProfessors(List<TowerColor> teamWithLessTowersOnSchoolboards) {
        List<TowerColor> teamColor = new ArrayList<>();
        int maximumNumberOfProfessors = -1;
        int[] numberOfProfessors = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            if(teamWithLessTowersOnSchoolboards.contains(schoolBoards[i].getTowersColor()))
                numberOfProfessors[i] = schoolBoards[i].getProfessors().size();
            else
                numberOfProfessors[i]=-1;
        }
        if(numberOfPlayers == 4) {
            int[] numberOfProfessorsForFourPlayers = new int[2];
            numberOfProfessorsForFourPlayers[0] = numberOfProfessors[0] + numberOfProfessors[2];
            numberOfProfessorsForFourPlayers[1] = numberOfProfessors[1] + numberOfProfessors[3];
            numberOfProfessors = numberOfProfessorsForFourPlayers;
        }

        for (int i = 0; i < numberOfProfessors.length; i++) {

            if (numberOfProfessors[i] > maximumNumberOfProfessors)
                maximumNumberOfProfessors=numberOfProfessors[i];
        }

        for(int i=0; i<numberOfProfessors.length; i++){
            if(numberOfProfessors[i]==maximumNumberOfProfessors)
                teamColor.add(teamWithLessTowersOnSchoolboards.get(i));
        }

        return teamColor;
    }

    /**
     * Adds a student from the entrance to his related table.
     * @param indexStudent index of the student on entrance to move on the table
     * @param schoolBoardIndex
     * @throws FullTableException if there isn't much space on the table
     */
    public void addStudentOnTableFromEntrance(int indexStudent, int schoolBoardIndex) throws FullTableException {
        this.schoolBoards[schoolBoardIndex].addStudentOnTable(indexStudent);
    }

    public void addSchoolBoards(SchoolBoard[] schoolBoards) {
        if(schoolBoards != null)
            this.schoolBoards = schoolBoards;
    }

    public GameTable getGameTableInstance(){
        return this;
    }

    /**
     * @return all the cloud in the gameTable
     */
    public Cloud[] getClouds(){
        return clouds;
    }

    /**
     * @return the bag with the students used in this game
     */
    public Bag getBag(){
        return this.bag;
    }
}