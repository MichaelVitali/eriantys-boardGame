package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EmptyTableException;
import it.polimi.ingsw.model.exception.FullTableException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Character10Test {

    private Character10 character;
    private Round round;

    @Before
    public void setUp() throws Exception {
        character = new Character10(10, 0);
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        round = new Round(new ExpertGame(2, nicknames));
    }

    @Test
    public void testDoYourJob() throws FullTableException, EmptyTableException {

        Student entranceStudent = null;

        character.activateEffect(0, round);
        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        round.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(PawnColor.YELLOW));
        character.doYourJob(0, 2);
        entranceStudent = round.getGame().getGameTable().getSchoolBoards()[0].getStudentsFromEntrance()[2];
        character.doYourJob(0, 0);
        assertEquals(entranceStudent, round.getGame().getGameTable().getSchoolBoards()[0].removeStudentFromTable(entranceStudent.getColor().getIndex()));
        assertEquals(PawnColor.YELLOW, round.getGame().getGameTable().getSchoolBoards()[0].getStudentsFromEntrance()[2].getColor());
        character.doYourJob(0,0);
        assertNotEquals(7, round.getRoundState());
    }

    @Test
    public void testActivateEffect() {
        assertEquals(character.activateEffect(0, round), character);
        assertEquals(0, character.getRound().getRoundState());
    }

    @Test
    public void testSetRoundState(){
        character.setRoundState(1);
        assertEquals(1, character.getRoundState());
        character.setRoundState(8);
        assertEquals(-1, character.getRoundState());
    }
}