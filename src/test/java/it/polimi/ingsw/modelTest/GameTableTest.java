package it.polimi.ingsw.modelTest;
import it.polimi.ingsw.Model.*;;
import java.util.*;

import it.polimi.ingsw.Model.exception.EmptyBagException;
import it.polimi.ingsw.Model.exception.EmptyCloudException;
import it.polimi.ingsw.Model.exception.InvalidIndexException;
import it.polimi.ingsw.Model.exception.NoMoreTowersException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTableTest {

    private SchoolBoard[] schoolBoards = new SchoolBoard[2];
    private GameTable gameTable = new GameTable(2,schoolBoards);

    @Before
    public void createSchoolbords(){
        schoolBoards[0] = new  SchoolBoard(9, TowerColor.BLACK, 8);
        schoolBoards[1] = new SchoolBoard(9, TowerColor.WHITE, 8);
    }

    @Test
    public void testGetNumberOfPlayers() {
        assertEquals(2, gameTable.getNumberOfPlayers());
    }

    @Test
    public void testGetIslands() {
        List<Island> islands = gameTable.getIslands();
        for (Island i : islands) assertNotNull(i);
    }

    @Test
    public void testGetSchoolBoards() {
        SchoolBoard[] schoolBoards = gameTable.getSchoolBoards();
        for(SchoolBoard s : schoolBoards) assertNotNull(s);
    }

    @Test
    public void testGetMotherNaturePosition() {
        assertTrue(gameTable.getMotherNaturePosition() >= 0 && gameTable.getMotherNaturePosition() < 12);
    }

    @Test
    public void testAddStudentsOnClouds() throws EmptyBagException {
        for (Cloud c : gameTable.getClouds()) assertEquals(0, c.getStudents().size());
        gameTable.addStudentsOnClouds();
        for (Cloud c : gameTable.getClouds()) assertEquals(3, c.getNumberOfStudents());
    }

    @Test
    public void testMoveProfessorToTheRightPosition() {
        gameTable.getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        gameTable.moveProfessorToTheRightPosition(PawnColor.YELLOW);
        assertEquals(gameTable.getSchoolBoards()[0].getProfessors().get(0), PawnColor.YELLOW);

        gameTable.getSchoolBoards()[1].addStudentOnTable(new Student(PawnColor.YELLOW));
        gameTable.moveProfessorToTheRightPosition(PawnColor.YELLOW);
        assertEquals(gameTable.getSchoolBoards()[0].getProfessors().get(0), PawnColor.YELLOW);
        assertEquals(0, gameTable.getSchoolBoards()[1].getProfessors().size());

        gameTable.getSchoolBoards()[1].addStudentOnTable(new Student(PawnColor.YELLOW));
        gameTable.moveProfessorToTheRightPosition(PawnColor.YELLOW);
        assertEquals(gameTable.getSchoolBoards()[1].getProfessors().get(0), PawnColor.YELLOW);
        assertEquals(0, gameTable.getSchoolBoards()[0].getProfessors().size());

        gameTable.getSchoolBoards()[1].addStudentOnTable(new Student(PawnColor.RED));
        gameTable.moveProfessorToTheRightPosition(PawnColor.RED);
        assertEquals(gameTable.getSchoolBoards()[1].getProfessors().get(1), PawnColor.RED);
        assertEquals(2, gameTable.getSchoolBoards()[1].getProfessors().size());
    }

    @Test
    public void testAddStudentOnIsland() {
        Student s = new Student(PawnColor.BLUE);
        gameTable.addStudentOnIsland(s, 5);
        List<Island> islands = gameTable.getIslands();
        assertEquals(islands.get(5).getStudents().get(0), s);
    }

    @Test
    public void testChangeMotherNaturePosition() {
        try{
            gameTable.changeMotherNaturePosition(10);
            assertEquals(10, gameTable.getMotherNaturePosition());
            gameTable.changeMotherNaturePosition(15);
        }catch (InvalidIndexException e){
            assertTrue(true);
        }


    }

    @Test
    public void testPutTowerOrChangeColorIfNecessary() throws InvalidIndexException {
        gameTable.addStudentOnIsland(new Student(PawnColor.BLUE), 0);
        gameTable.getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.BLUE));
        gameTable.moveProfessorToTheRightPosition(PawnColor.BLUE);
        gameTable.changeMotherNaturePosition(0);
        gameTable.putTowerOrChangeColorIfNecessary();
        assertEquals(TowerColor.BLACK, gameTable.getIslands().get(0).getTowers().get(0).getColor());

    }

    @Test
    public void testCalculateInfluences() throws InvalidIndexException {
        //test se il primmo giocatore ha il professore dello studente di quel colore e gli altri no
        gameTable.addStudentOnIsland(new Student(PawnColor.BLUE), 0);
        gameTable.getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.BLUE));
        gameTable.moveProfessorToTheRightPosition(PawnColor.BLUE);
        gameTable.changeMotherNaturePosition(0);
        int[] influences = gameTable.calculateInfluences();
        assertTrue(influences[0] > influences[1]);

        //test che calcola l'influenza di entrambi i giocatori con un solo professore
        gameTable.addStudentOnIsland(new Student(PawnColor.YELLOW), 0);
        gameTable.getSchoolBoards()[1].addStudentOnTable(new Student(PawnColor.YELLOW));
        gameTable.moveProfessorToTheRightPosition(PawnColor.YELLOW);
        assertEquals(PawnColor.YELLOW, gameTable.getSchoolBoards()[1].getProfessors().get(0));
        int[] influences2 = gameTable.calculateInfluences();
        assertEquals(influences2[0], influences2[1]);
    }

    @Test
    public void testMergeIslandsIfNecessary() throws InvalidIndexException {
        List<Tower> towers = new ArrayList<>();
        towers.add(new Tower(TowerColor.BLACK));
        gameTable.getIslands().get(0).setTowers(towers);
        gameTable.getIslands().get(1).setTowers(towers);
        gameTable.changeMotherNaturePosition(0);
        gameTable.mergeIslandsIfNecessary();
        assertEquals(2, gameTable.getIslands().get(0).getAggregation());
    }

    @Test
    public void testGetStudentsOnCloud() throws EmptyBagException, EmptyCloudException {
        List<Student> studentsOnCloud;
        gameTable.addStudentsOnClouds();
        for (int i = 0; i < gameTable.getClouds().length; i++){
            studentsOnCloud = gameTable.getStudentsOnCloud(i);
            for (Student s : studentsOnCloud) assertNotNull(s);
            assertTrue(gameTable.getClouds()[i].isEmpty());
        }
    }

    @Test
    public void testTeamWithLessTowersOnSchoolboars() throws InvalidIndexException, NoMoreTowersException {
        gameTable.getSchoolBoards()[0].removeTowers(2);
        gameTable.getSchoolBoards()[1].removeTowers(4);
        assertEquals(TowerColor.WHITE, gameTable.teamWithLessTowersOnSchoolboards().get(0));

        gameTable.getSchoolBoards()[0].removeTowers(3);
        assertEquals(TowerColor.BLACK, gameTable.teamWithLessTowersOnSchoolboards().get(0));

        gameTable.getSchoolBoards()[1].removeTowers(1);
        assertEquals(2, gameTable.teamWithLessTowersOnSchoolboards().size());
    }

    @Test
    public void testTeamWithMoreProfessors() {
        //test player one with one professor and second player without
        gameTable.getSchoolBoards()[0].setProfessor(PawnColor.RED, true);
        assertEquals(TowerColor.BLACK, gameTable.teamWithMoreProfessors(gameTable.teamWithLessTowersOnSchoolboards()).get(0));

        //test second player with more professors
        gameTable.getSchoolBoards()[1].setProfessor(PawnColor.YELLOW, true);
        gameTable.getSchoolBoards()[1].setProfessor(PawnColor.BLUE, true);
        assertEquals(TowerColor.WHITE, gameTable.teamWithMoreProfessors(gameTable.teamWithLessTowersOnSchoolboards()).get(0));

        //test with same number of professors. Expected list with all TowerColor
        gameTable.getSchoolBoards()[0].setProfessor(PawnColor.GREEN, true);
        assertEquals(2, gameTable.teamWithMoreProfessors(gameTable.teamWithLessTowersOnSchoolboards()).size());
    }

    @Test
    public void testAddStudentOnTableFromEntrance() {
        Student[] entranceSchoolBoard = gameTable.getSchoolBoards()[0].getStudentsFromEntrance();
        gameTable.addStudentOnTableFromEntrance(0,0);
        assertEquals(1, gameTable.getSchoolBoards()[0].getNumberOfStudentsOnTable(entranceSchoolBoard[0].getColor()));
    }

    @Test
    public void testGetGameTableInstance() {
        assertEquals(gameTable, gameTable.getGameTableInstance());
    }

    @Test
    public void testGetClouds() {
        for (Cloud c : gameTable.getClouds()) assertNotNull(c);
    }
}
