package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.EmptyTableException;
import it.polimi.ingsw.model.exception.FullTableException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MinstrelTest {

    private Minstrel character;
    private Round round;

    @Before
    public void setUp() throws Exception {
        character = new Minstrel(10, 0);
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        round = new Round(new ExpertGame(2, nicknames));
    }

    @Test
    public void testDoYourJob() throws FullTableException, EmptyTableException, EffectCannotBeActivatedException {

        Student entranceStudent = null;

        character.activateEffect(1, round);
        for (PawnColor pc : PawnColor.values())
            for (int i = 0; i < 3; i++)
                character.getGame().getGameTable().getSchoolBoards()[1].addStudentOnTable(new Student(pc));
        character.doYourJob(1, 3);
        entranceStudent = character.getGame().getGameTable().getSchoolBoards()[1].getStudentsFromEntrance()[3];
        character.doYourJob(1,1);
        assertEquals(entranceStudent, character.getGame().getGameTable().getSchoolBoards()[1].removeStudentFromTable(entranceStudent.getColor()));
        assertEquals(PawnColor.BLUE, character.getGame().getGameTable().getSchoolBoards()[1].getStudentsFromEntrance()[3].getColor());
        character.doYourJob(1, 1);
        assertEquals(5, character.getRoundState());
        character.doYourJob(1, 5);
        entranceStudent = character.getGame().getGameTable().getSchoolBoards()[1].getStudentsFromEntrance()[5];
        character.doYourJob(1, 0);
        assertEquals(entranceStudent, character.getGame().getGameTable().getSchoolBoards()[1].removeStudentFromTable(entranceStudent.getColor()));
        assertEquals(PawnColor.YELLOW, character.getGame().getGameTable().getSchoolBoards()[1].getStudentsFromEntrance()[5].getColor());

        character.activateEffect(0, round);
        for (PawnColor pc : PawnColor.values())
            for (int i = 0; i < 3; i++)
                character.getGame().getGameTable().getSchoolBoards()[0].addStudentOnTable(new Student(pc));
        character.doYourJob(0, 2);
        entranceStudent = round.getGame().getGameTable().getSchoolBoards()[0].getStudentsFromEntrance()[2];
        character.doYourJob(0, 0);
        assertEquals(entranceStudent, round.getGame().getGameTable().getSchoolBoards()[0].removeStudentFromTable(entranceStudent.getColor().getIndex()));
        assertEquals(PawnColor.YELLOW, round.getGame().getGameTable().getSchoolBoards()[0].getStudentsFromEntrance()[2].getColor());
        character.doYourJob(0,0);
        assertNotEquals(7, round.getRoundState());

    }

    @Test
    public void testActivateEffect() throws EffectCannotBeActivatedException {
        assertEquals(character.activateEffect(0, round), character);
        assertEquals(0, character.getRound().getRoundState());
    }

    @Test
    public void testSetRoundState(){
        character.setRoundState(1);
        assertEquals(1, character.getRoundState());
        character.setRoundState(9);
        assertEquals(-1, character.getRoundState());
    }
}