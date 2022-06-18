package it.polimi.ingsw.controllerTest.message;

import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.model.*;
import junit.framework.TestCase;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class GameMessageTest extends TestCase {

    private GameMessage message;
    private ExpertGame game;

    @Before
    public void setUp(){
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        game = new ExpertGame(2, nicknames);
        message = new GameMessage(game,0);
    }

    public void testGetGametable() {
        assertNotNull(message.getGametable());
    }

    public void testGetAssistants() {
        message.getAssistants();
    }

    public void testGetState() {
        message.getState();
    }

    public void testGetPlayerId() {
        assertEquals(0, message.getPlayerId());
    }

    public void testGetPlayerMessage() {
        message.getPlayerMessageCli();
    }

    public void testPrintDefaultOnCli() {
        message.printDefaultOnCli();
    }

    public void testGetPlayerOnTurn() {
        message.getPlayerOnTurn();
    }

    public void testGetGameMode() {
        assertEquals(GameMode.EXPERT, message.getGameMode());
    }

    public void testPrintIslands() {
        message.printIslands(message.getGametable().getIslands());
    }

    public void testPrintAssistants() {
        message.printAssistants(message.getAssistants());
    }

    public void testPrintCloud() {
        message.printCloud(message.getGametable().getClouds());
    }

    public void testPrintAllSchoolboards() {
        message.printAllSchoolboards();
    }

    public void testPrintSchoolboard() {
        message.printSchoolboard(0);
    }

    public void testPrintCharacter() {
    }

    public void testReturnCircleUnicodeForColor() {
        message.returnCircleUnicodeFromColor(TowerColor.WHITE);
    }

    public void testReturnCircleUnicodeFromColor() {
        message.returnCircleUnicodeForColor(PawnColor.YELLOW);
    }
}