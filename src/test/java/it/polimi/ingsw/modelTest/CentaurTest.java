package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.FullTableException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CentaurTest {

    private Centaur character = new Centaur(6, 3);
    private ExpertGame game;
    Round round;

    @Before
    public void setUp() {
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        game = new ExpertGame(2, nicknames);
        game.setASingleCharacter(character, 0);
        round = new Round(new ExpertGame(2, nicknames));
    }

    @Test
    public void testChangeMotherNaturePosition() throws FullTableException, EffectCannotBeActivatedException {
        try {
            character.activateEffect(0, round);
            assertEquals(0, character.getRoundState());
        } catch (EffectCannotBeActivatedException e) {
            System.out.println(e.getMessage());
        }

        int playerId = 0;

        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[1].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[1].setProfessor(PawnColor.YELLOW, true);
        character.activateEffect(playerId, round);
        assertTrue(round.getGame().getGameTable().getSchoolBoards()[1].getProfessors().contains(PawnColor.YELLOW));
        assertFalse(round.getGame().getGameTable().getSchoolBoards()[0].getProfessors().contains(PawnColor.YELLOW));
    }

}
