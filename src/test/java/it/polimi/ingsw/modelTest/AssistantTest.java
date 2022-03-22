package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.Assistant;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class AssistantTest {

    private Assistant a1 = new Assistant(1,5);
    private Assistant a2 = new Assistant(10, 1);
    @Test
    public void testGetCardValue() {
        assertTrue(a1.getCardValue() == 1);
        assertTrue(a2.getCardValue() == 10);
    }
    @Test
    public void testGetMotherNatureMoves() {
        assertTrue(a1.getMotherNatureMoves() == 5);
        assertTrue(a2.getMotherNatureMoves() == 1);
    }
}