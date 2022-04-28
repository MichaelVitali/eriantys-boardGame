package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.EmptyCloudException;
import it.polimi.ingsw.model.exception.FullTableException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecoratedGameTableMoreInflueceTest {

    private DecoratedGameTableMoreInfluece decoratedGameTableMoreInfluece;

    @Before
    public void setUp() throws InvalidIndexException {
        SchoolBoard[] schoolBoards = new SchoolBoard[2];
        schoolBoards[0] = new SchoolBoard(7, TowerColor.BLACK, 6);
        schoolBoards[1] = new SchoolBoard(7, TowerColor.WHITE, 6);
        GameTable gameTable = new GameTable(2, schoolBoards, new Bag());
        gameTable.addStudentOnIsland(new Student(PawnColor.YELLOW), gameTable.getMotherNaturePosition());
        gameTable.getSchoolBoards()[0].setProfessor(PawnColor.YELLOW, true);
        decoratedGameTableMoreInfluece = new DecoratedGameTableMoreInfluece(gameTable, 0);
    }

    @Test
    public void testGetMotherNaturePosition() {
        decoratedGameTableMoreInfluece.getMotherNaturePosition();
    }

    @Test
    public void testCalculateInfluences() {
        int[] result;
        result = decoratedGameTableMoreInfluece.calculateInfluences();
        assertEquals(result[0], 3);
        assertEquals(result[1], 0);

        decoratedGameTableMoreInfluece.getSchoolBoards()[0].setProfessor(PawnColor.YELLOW, false);
        result = decoratedGameTableMoreInfluece.calculateInfluences();
        assertEquals(result[0], 2);
        assertEquals(result[1], 0);
    }

    @Test
    public void testAddStudentsOnClouds() throws EmptyBagException {
        decoratedGameTableMoreInfluece.addStudentsOnClouds();
    }

    @Test
    public void testMoveProfessorToTheRightPosition() {
        decoratedGameTableMoreInfluece.moveProfessorToTheRightPosition(PawnColor.YELLOW);
    }

    @Test
    public void testAddStudentOnIsland() throws InvalidIndexException {
        decoratedGameTableMoreInfluece.addStudentOnIsland(new Student(PawnColor.YELLOW), 0);
    }

    @Test
    public void testChangeMotherNaturePosition() {
        decoratedGameTableMoreInfluece.changeMotherNaturePosition(0);
    }

    @Test
    public void testPutTowerOrChangeColorIfNecessary() {
        decoratedGameTableMoreInfluece.putTowerOrChangeColorIfNecessary();
    }

    @Test
    public void testMergeIslandsIfNecessary() {
        decoratedGameTableMoreInfluece.mergeIslandsIfNecessary();
    }

    @Test
    public void testGetStudentsOnCloud() throws EmptyCloudException, EmptyBagException {
        decoratedGameTableMoreInfluece.addStudentsOnClouds();
        decoratedGameTableMoreInfluece.getStudentsOnCloud(0);
    }

    @Test
    public void testTeamWithLessTowersOnSchoolboards() {
        decoratedGameTableMoreInfluece.teamWithLessTowersOnSchoolboards();
    }

    @Test
    public void testTeamWithMoreProfessors() {
        decoratedGameTableMoreInfluece.teamWithMoreProfessors(decoratedGameTableMoreInfluece.teamWithLessTowersOnSchoolboards());
    }

    @Test
    public void testAddStudentOnTableFromEntrance() throws FullTableException {
        decoratedGameTableMoreInfluece.addStudentOnTableFromEntrance(1, 1);
    }

    @Test
    public void testGetGameTableInstance() {
        assertNotNull(decoratedGameTableMoreInfluece.getGameTableInstance());
    }
}