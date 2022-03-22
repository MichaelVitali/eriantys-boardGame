package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.PawnColor;
import it.polimi.ingsw.Model.Student;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class StudentTest {
    @Test
    public void testGetColor() {
            Student s = new Student(PawnColor.YELLOW);
            assertTrue(s.getColor() == PawnColor.YELLOW);
    }
}