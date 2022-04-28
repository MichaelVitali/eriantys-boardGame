package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.Character;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Round;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    private Character c = new Character(1, 2);
    private Round round = new Round(new Game(2));

    @Test
    public void testTestGetID() {
        assertEquals(c.getID(), 1);
    }

    @Test
    public void testTestGetCost() {
        assertEquals(2, c.getCost());
    }

    @Test
    public void testTestGetFirstUse() {
        assertFalse(c.getFirstUse());
        c.setFirstUse();
        assertTrue(c.getFirstUse());
    }

    @Test
    public void testTestSetFirstUse() {
        c.setFirstUse();
        assertTrue(c.getFirstUse());
    }

    @Test
    public void testActivateEffect() {
        assertEquals(c.activateEffect(0, round), c);
    }

    @Test
    public void testDeactivateEffect() {
    }

    @Test
    public void testDoYourJob() {
        c.doYourJob(0, 1);
    }

    @Test
    public void testGetRound() {
        assertNull(c.getRound());
        c.activateEffect(0, round);
        assertNotNull(c.getRound());
    }
}