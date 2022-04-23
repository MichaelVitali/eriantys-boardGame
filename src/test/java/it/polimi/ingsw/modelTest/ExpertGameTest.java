package it.polimi.ingsw.modelTest;
import it.polimi.ingsw.model.*;
import java.util.*;

import it.polimi.ingsw.model.Character;
import it.polimi.ingsw.model.exception.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class ExpertGameTest {

    ExpertGame exp_game2p = new ExpertGame(2, new String[]{"player0", "player1"});

    @Test
    public void testGetGame(){}//TODO

    @Test
    public void testGetCharacters(){}//TODO

    @Test
    public void testSetGame(){}//TODO

    @Test
    public void testSetCharacters(){}//TODO

    @Test
    public void testCreateCharacters() throws EmptyBagException {
        exp_game2p.createCharacters();
        Character[] testCharacters = exp_game2p.getCharacters();
        assertEquals(3, testCharacters.length);
        for(Character c : testCharacters){
            assertNotNull(c);
            assertNotNull(c.getCost());
            assertNotNull(c.getID());
            if(c.getID()==1 || c.getID()==11 || c.getID()==7){
                assertThat(c, instanceOf(CharacterWithStudent.class));
            }
        }
    }

    @Test
    public void testGetIdCharacter() throws EmptyBagException, InvalidIndexException {

        int indexCard=1;
        assertNotEquals(0, exp_game2p.getIdCharacter(indexCard));

        indexCard=4;
        try{
            exp_game2p.getIdCharacter(indexCard);
        }catch (InvalidIndexException e){assertTrue(true);}
    }

    @Test
    public void testGetCostCharacter() throws InvalidIndexException {
        int indexCard=1;
        assertNotEquals(0, exp_game2p.getCostCharacter(indexCard));

        indexCard=4;
        try{
            exp_game2p.getCostCharacter(indexCard);
        }catch (InvalidIndexException e){assertTrue(true);}
    }

    @Test
    public void testGetStudentFromCard() {

    }

    @Test
    public void testAddStudentsOnCard() {
    }

    @Test
    public void testGetCharacter() {
    }

    @Test
    public void testEffectActivation() {
    }

    @Test
    public void testGetCoinsOfTheTable() {
    }

    @Test
    public void testGetPlayersCoins() {
    }

}