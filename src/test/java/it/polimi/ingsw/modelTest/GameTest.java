package it.polimi.ingsw.modelTest;
import it.polimi.ingsw.model.*;
import java.util.*;

import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.EmptyCloudException;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    private SchoolBoard[] schoolBoards = new SchoolBoard[] { new SchoolBoard(9, TowerColor.BLACK, 8), new SchoolBoard(9, TowerColor.WHITE, 8)};
    private GameTable gameTable = new GameTable(2, schoolBoards);
    private Player[] players = {new Player("player0", 0,  new ArrayList<Assistant>()), new Player("player1", 1,  new ArrayList<Assistant>())};
    private String[] nicknames = {players[0].getNickname(), players[1].getNickname()};
    private Game game2p = new Game(2, nicknames);

    @Test
    public void testGetNumberOfPlayers() {
        assertEquals(2, game2p.getNumberOfPlayers());
    }

    @Test
    public void testGetGameTable() {
        game2p.setGameTable(gameTable);
        assertEquals(gameTable, game2p.getGameTable());
    }

    @Test
    public void testGetPlayer() {
        for (int i=0; i<game2p.getNumberOfPlayers(); i++)
            assertNotNull(game2p.getPlayer(i));
    }

    @Test
    public void testSetGameTable() {
        game2p.setGameTable(gameTable);
        assertEquals(gameTable, game2p.getGameTable());
    }

    @Test
    public void testIsAValidPositionForMotherNature() {

        game2p.setGameTable(gameTable);

        assertTrue(game2p.getGameTable().getNumberOfIslands() > 0);
        assertTrue(game2p.getGameTable().getMotherNaturePosition() >= 0);

        int givenPosition = -1;
        assertFalse(game2p.isAValidPositionForMotherNature(givenPosition));

        givenPosition = game2p.getGameTable().getIslands().size();
        assertFalse(game2p.isAValidPositionForMotherNature(givenPosition));

        givenPosition = game2p.getGameTable().getIslands().size() - 1;
        assertTrue(game2p.isAValidPositionForMotherNature(givenPosition));
    }

    @Test
    public void testGetRound() {
        Round r;
        r = game2p.startRound();

        assertEquals(r, game2p.getRound());
    }

    @Test
    public void testStartRound() throws EmptyCloudException {
        game2p.setGameTable(gameTable);

        for (int i=0; i<game2p.getGameTable().getNumberOfClouds(); i++)
            assertTrue(game2p.getGameTable().getClouds()[i].getStudents().isEmpty());

        game2p.startRound();

        for (int i=0; i<game2p.getGameTable().getNumberOfClouds(); i++) {
            //assertNotNull(game2p.getGameTable().getStudentsOnCloud(i));
            for(Student s : game2p.getGameTable().getStudentsOnCloud(i))
                assertNotNull(s);
        }
    }

    @Test
    public void testTestStartRound() throws EmptyCloudException {
        int playerOrder[]={0,1};
        game2p.setGameTable(gameTable);

        for (int i=0; i<game2p.getGameTable().getNumberOfClouds(); i++)
            assertTrue(game2p.getGameTable().getClouds()[i].getStudents().isEmpty());

        game2p.startRound(playerOrder);

        for (int i=0; i<game2p.getGameTable().getNumberOfClouds(); i++) {
            //assertNotNull(game2p.getGameTable().getStudentsOnCloud(i));
            for(Student s : game2p.getGameTable().getStudentsOnCloud(i))
                assertNotNull(s);
        }
    }

    @Test
    public void testCheckEndgame() {
        gameTable.addSchoolBoards(schoolBoards);
        game2p.setGameTable(gameTable);
    }

}