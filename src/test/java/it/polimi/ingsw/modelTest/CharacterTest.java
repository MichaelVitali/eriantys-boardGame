package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.Character;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CharacterTest {

    private Character c = new Character(1, 3);

    @Test
    public void testGetID() {
        assertTrue( c.getID() == 1);
    }
    @Test
    public void testGetCost() {
        assertTrue( c.getCost() == 3);
    }
    @Test
    public void testGetFirstUse() {
        assertFalse(c.getFirstUse());
        c.setFirstUse();
        assertTrue(c.getFirstUse());
    }
    @Test
    public void testSetFirstUse() {
        c.setFirstUse();
        assertTrue(c.getFirstUse());
    }
}