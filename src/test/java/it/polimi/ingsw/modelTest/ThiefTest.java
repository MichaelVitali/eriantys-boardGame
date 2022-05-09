package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.FullTableException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ThiefTest {

    private Thief character;
    private Round round;

    @Before
    public void setUp() throws Exception {
        character = new Thief(10, 0);
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        round = new Round(new ExpertGame(2, nicknames));
    }

    @Test
    public void testDoYourJob() throws FullTableException {
        character.activateEffect(0, round);
        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[1].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[1].addStudentOnTable(new Student(PawnColor.YELLOW));
        character.doYourJob(0, 0);
        assertEquals(1, round.getGame().getGameTable().getSchoolBoards()[0].getNumberOfStudentsOnTable(PawnColor.YELLOW));
        assertEquals(0, round.getGame().getGameTable().getSchoolBoards()[1].getNumberOfStudentsOnTable(PawnColor.YELLOW));
    }

    @Test
    public void testActivateEffect() {
        assertEquals(character.activateEffect(0, round), character);
        assertEquals(0, character.getRound().getRoundState());
    }

    @Test
    public void testSetRoundState() {
        character.setRoundState(1);
        assertEquals(1, character.getRoundState());
        character.setRoundState(6);
        assertEquals(-1, character.getRoundState());
    }
}