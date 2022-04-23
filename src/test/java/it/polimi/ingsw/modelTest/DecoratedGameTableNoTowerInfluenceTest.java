package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.EmptyCloudException;
import it.polimi.ingsw.model.exception.FullTableException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecoratedGameTableNoTowerInfluenceTest {

    private DecoratedGameTableNoTowerInfluence decoratedGameTableNoTowerInfluence;

    @Before
    public void setUp() throws InvalidIndexException {
        SchoolBoard[] schoolBoards = new SchoolBoard[2];
        schoolBoards[0] = new SchoolBoard(7, TowerColor.BLACK, 6);
        schoolBoards[1] = new SchoolBoard(7, TowerColor.WHITE, 6);
        GameTable gameTable = new GameTable(2, schoolBoards, new Bag());
        gameTable.addStudentOnIsland(new Student(PawnColor.YELLOW), 1);
        gameTable.getSchoolBoards()[0].setProfessor(PawnColor.YELLOW, true);
        gameTable.changeMotherNaturePosition(1);
        gameTable.putTowerOrChangeColorIfNecessary();
        decoratedGameTableNoTowerInfluence = new DecoratedGameTableNoTowerInfluence(gameTable);
    }

    @Test
    public void testGetMotherNaturePosition() {
        decoratedGameTableNoTowerInfluence.getMotherNaturePosition();
    }

    @Test
    public void testCalculateInfluences() {
        decoratedGameTableNoTowerInfluence.changeMotherNaturePosition(1);
        assertEquals(2, decoratedGameTableNoTowerInfluence.getGameTableInstance().calculateInfluences()[0]);
        int[] influences = decoratedGameTableNoTowerInfluence.calculateInfluences();
        assertEquals(1, influences[0]);
    }

    @Test
    public void testAddStudentsOnClouds() throws EmptyBagException {
        decoratedGameTableNoTowerInfluence.addStudentsOnClouds();
    }

    @Test
    public void testMoveProfessorToTheRightPosition() {
        decoratedGameTableNoTowerInfluence.moveProfessorToTheRightPosition(PawnColor.YELLOW);
    }

    @Test
    public void testAddStudentOnIsland() throws InvalidIndexException {
        decoratedGameTableNoTowerInfluence.addStudentOnIsland(new Student(PawnColor.YELLOW), 0);
    }

    @Test
    public void testChangeMotherNaturePosition() {
        decoratedGameTableNoTowerInfluence.changeMotherNaturePosition(0);
    }

    @Test
    public void testPutTowerOrChangeColorIfNecessary() {
        decoratedGameTableNoTowerInfluence.putTowerOrChangeColorIfNecessary();
    }

    @Test
    public void testMergeIslandsIfNecessary() {
        decoratedGameTableNoTowerInfluence.mergeIslandsIfNecessary();
    }

    @Test
    public void testGetStudentsOnCloud() throws EmptyBagException, EmptyCloudException {
        decoratedGameTableNoTowerInfluence.addStudentsOnClouds();
        decoratedGameTableNoTowerInfluence.getStudentsOnCloud(0);
    }

    @Test
    public void testTeamWithLessTowersOnSchoolboards() {
        decoratedGameTableNoTowerInfluence.teamWithLessTowersOnSchoolboards();
    }

    @Test
    public void testTeamWithMoreProfessors() {
        decoratedGameTableNoTowerInfluence.teamWithMoreProfessors(decoratedGameTableNoTowerInfluence.teamWithLessTowersOnSchoolboards());
    }

    @Test
    public void testAddStudentOnTableFromEntrance() throws FullTableException {
        decoratedGameTableNoTowerInfluence.addStudentOnTableFromEntrance(1, 0);
    }

    @Test
    public void testGetGameTableInstance() {
        assertNotNull(decoratedGameTableNoTowerInfluence.getGameTableInstance());
    }

    @Test
    public void testGetSchoolBoards() {
        decoratedGameTableNoTowerInfluence.getSchoolBoards();
    }
}