package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.Character;
import it.polimi.ingsw.model.ExpertGame;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Round;
import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CharacterTest {

    private Character c = new Character(1, 2);
    private Round round;

    @Before
    public void setUp() {
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        round = new Round(new ExpertGame(2, nicknames));
    }

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
    public void testActivateEffect() throws EffectCannotBeActivatedException {
        assertEquals(c.activateEffect(0, round), c);
    }

    @Test
    public void testDeactivateEffect() throws EffectCannotBeActivatedException {
        c.activateEffect(0, round);
        c.deactivateEffect();
        assertEquals(round, round.getGame().getRound());
    }

    @Test
    public void testDoYourJob() {
        try {
            c.doYourJob(0, 1);
        } catch (InvalidIndexException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetRound() throws EffectCannotBeActivatedException {
        assertNull(c.getRound());
        c.activateEffect(0, round);
        assertNotNull(c.getRound());
    }
}