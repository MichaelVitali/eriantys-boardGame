package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Character1Test {

    private Character1 character = new Character1(1, 0, 4);
    private Round round;

    @Before
    public void setUp() {
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        round = new Round(new ExpertGame(2, nicknames));
    }

    @Test
    public void testDoYourJob() throws InvalidIndexException, EmptyBagException {
        character.addStudents(round.getGame().getGameTable().getBag().drawStudents(4));
        character.activateEffect(0, round);
        character.doYourJob(0, 2);
        character.doYourJob(0, character.getRound().getGame().getGameTable().getMotherNaturePosition());
        Island island = character.getRound().getGame().getGameTable().getIslands().get(character.getRound().getGame().getGameTable().getMotherNaturePosition());
        assertEquals(1, island.getStudents().size());
        assertNotEquals(island.getStudents().get(0), character.getStudents(new ArrayList<>(2)));
        assertEquals(round, character.getRound().getGame().getRound());
    }

    @Test
    public void testActivateEffect() {
        character.setRoundState(1);
        assertEquals(1, character.getRoundState());
        character.setRoundState(7);
        assertEquals(-1, character.getRoundState());
    }
    @Test
    public void testSetRoundState(){
        character.setRoundState(1);
        assertEquals(1, character.getRoundState());
        character.setRoundState(7);
        assertEquals(-1, character.getRoundState());
    }
}