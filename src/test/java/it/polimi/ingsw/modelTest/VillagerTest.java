package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VillagerTest {

    private Villager character = new Villager(1,0);
    private Round round;

    @Before
    public void setUp() throws Exception {
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        round = new Round(new ExpertGame(2, nicknames));
    }

    @Test
    public void testDoYourJob() throws InvalidIndexException, EffectCannotBeActivatedException {

        round.setIndexOfPlayerOnTurn(0);
        round.setRoundState(1);

        for (int i = 0; i < 4; i++) {
            round.addStudentOnIsland(round.getPlayerOnTurn(), i, 0);
            round.setRoundState(1);
        }
        round.setIndexOfPlayerOnTurn(1);
        for (int i = 0; i < 4; i++) {
            round.addStudentOnIsland(round.getPlayerOnTurn(), i, 0);
            round.setRoundState(1);
        }
        round.getGame().getGameTable().changeMotherNaturePosition(0);

        character.activateEffect(0, round);
        assertEquals(4, round.getRoundState());
        character.doYourJob(round.getPlayerOnTurn(), 0);

        assertEquals(1, round.getRoundState());
    }

    @Test
    public void testActivateEffect() {
        try {
            character.activateEffect(0, round);
            assertEquals(4, character.getRoundState());
        } catch (EffectCannotBeActivatedException e) {
            System.out.println(e.getMessage());
        }
    }
}