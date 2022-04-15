package it.polimi.ingsw.modelTest;
import it.polimi.ingsw.model.*;
import java.util.*;

import it.polimi.ingsw.model.exception.InvalidMethodException;
import it.polimi.ingsw.model.exception.PlayerNotOnTurnException;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoundTest {

    private Game game2p = new Game(2); //Ho cambiato protected in public sui costruttori di Game
    private Round firstRound = new Round(game2p);
    private Round round = new Round(game2p, firstRound.getPlayerOrder());

    //PianificationPhase Tests

    @Test
    public void testCalculateFirstPlayer(){
    }

    @Test
    public void testPlayAssistantPianificationPhase(){
    }

    //----------------------------------------------------------------
    //PlayedAssistants Tests

    @Test
    public void testGetPlayerIndex(){
    }

    @Test
    public void testGetAssistant(){
    }

    //-----------------------------------------------------------------

    @Test
    public void testCheckPlayerOnTurn() throws PlayerNotOnTurnException {
    }

    @Test
    public void testCheckStatusAndMethod() throws InvalidMethodException {
    }

    @Test
    public void testCheckNumberOfMoves() {
    }

    @Test
    public void testSetErrorMessage() {
    }

    @Test
    public void testIsPianificationPhaseEnded() {
    }

    @Test
    public void testIsActionPhaseEnded() {
    }

    @Test
    public void testIsTimeToMoveMotherNature() {
    }

    @Test
    public void testIsTimeToChooseACloud() {
    }

    @Test
    public void testIslandHasBeenChosen() {
    }

    @Test
    public void testSetPianificationPhaseOrder() {
    }

    @Test
    public void testSetActionPhaseOrder() {
    }

    @Test
    public void testPlayAssistant() {
    }

    @Test
    public void testAddStudentOnIsland() {
    }

    @Test
    public void testAddStudentOnTable() {
    }

    @Test
    public void testIsANewAllowedPositionForMotherNature() {
    }

    @Test
    public void testChangeMotherNaturePosition() {
    }

    @Test
    public void testGetStudentsFromCloud() {
    }

}