package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.EmptyCloudException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.OutOfBoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player = new Player("Mike", 1, new ArrayList<Assistant>());
    private GameTable gameTable;

    @Test
    @Before
    public void testAddGameTable() {
        SchoolBoard[] schoolBoards= new SchoolBoard[2];
        schoolBoards[0] = new SchoolBoard(6, TowerColor.WHITE, 8);
        schoolBoards[1] = new SchoolBoard(6, TowerColor.BLACK, 8);
        this.gameTable = new GameTable(2, schoolBoards);
        player.addGameTable(gameTable);
        player.addSchoolBoard(schoolBoards[0]);
    }

    @Test
    public void testAddSchoolBoard() {
        SchoolBoard s = new SchoolBoard(6, TowerColor.BLACK, 8);
        player.addSchoolBoard(s);
    }

    @Test
    public void testPlayAssistant() throws OutOfBoundException {
        List<Assistant> assistants = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            assistants.add(new Assistant(i, i%5));
        }
        player.addAssistants(assistants);
        for(Assistant s : assistants){
            assertEquals(s, player.playAssistant(0));
        }
    }

    @Test
    public void testGetAssistant() throws OutOfBoundException{
        List<Assistant> assistants = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            assistants.add(new Assistant(i, i%5));
        }
        player.addAssistants(assistants);
        int i = 0;
        for(Assistant s : assistants){
            assertEquals(s, player.getAssistant(i));
            assertNotNull(player.getAssistant(i));
            i++;
        }
    }

    @Test
    public void testMoveStudentOnTable() {
        player.moveStudentOnTable(0);
    }

    @Test
    public void testMoveStudentOnIsland() throws InvalidIndexException, InvalidIndexException {
        Student[] entrance = player.getStudentsFormEntrance();
        player.moveStudentOnIsland(0, 1);
        List<Island> islands = gameTable.getIslands();
        assertEquals(islands.get(1).getStudents().get(0), entrance[0]);
        assertTrue(islands.get(1).getStudents().size() == 1);
    }

    @Test
    public void testTakeStudentsFromCloud() throws EmptyBagException, EmptyCloudException {
        Student[] entrance;
        Student[] entrance2;

        gameTable.addStudentsOnClouds();
        for(int i = 0; i < 3; i++){
            player.moveStudentOnTable(i);
        }

        entrance = player.getStudentsFormEntrance();
        player.takeStudentsFromCloud(1);
        entrance2 = player.getStudentsFormEntrance();
        for(int i = 0; i < entrance.length; i++){
            if(i < 3) assertNotEquals(entrance[i], entrance2[i]);
            else assertEquals(entrance[i], entrance2[i]);
        }

    }

    @Test
    public void testAddAssistants() throws OutOfBoundException{
        List<Assistant> assistants = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            assistants.add(new Assistant(i, i%5));
        }
        player.addAssistants(assistants);
        int i = 0;
        for(Assistant s : assistants){
            assertEquals(s, player.getAssistant(i));
            assertNotNull(player.getAssistant(i));
            i++;
        }
    }
}