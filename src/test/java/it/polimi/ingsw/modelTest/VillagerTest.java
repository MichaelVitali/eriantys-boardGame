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

    private Villager character = new Villager(1, 0, "Villager");
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

        round.getGame().getGameTable().addStudentOnIsland(new Student(PawnColor.YELLOW), 0);
        round.getGame().getGameTable().addStudentOnIsland(new Student(PawnColor.YELLOW), 0);
        round.getGame().getGameTable().addStudentOnIsland(new Student(PawnColor.BLUE), 0);
        round.getGame().getGameTable().getSchoolBoards()[0].setProfessor(PawnColor.YELLOW, true);
        round.getGame().getGameTable().getSchoolBoards()[1].setProfessor(PawnColor.BLUE, true);

        round.getGame().getGameTable().changeMotherNaturePosition(0);

        int[] influences = round.getGame().getGameTable().calculateInfluenceValuesGivenByStudentsExceptOne( PawnColor.YELLOW );

        int[] influencesFromTowers = round.getGame().getGameTable().calculateInfluenceValuesGivenByTowers();
        for (int i = 0; i < influences.length; i++) {
            influences[i] += influencesFromTowers[i];
            System.out.println(influences[i]);
        }
        assertTrue(influences[0] < influences[1]);
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