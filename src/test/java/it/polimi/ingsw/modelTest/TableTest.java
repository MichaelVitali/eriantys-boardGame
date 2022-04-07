package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.PawnColor;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.Student;
import org.junit.Test;
import static org.junit.Assert.*;

public class TableTest {

    private Table table = new Table(PawnColor.RED);

    @Test
    public void testGetNumberOfStudents() {
        assertTrue(table.getNumberOfStudents() == 0);

        for(int i = 0; i < 5; i++){
            Student s = new Student(PawnColor.RED);
            table.addStudent(s);
        }
        assertTrue(table.getNumberOfStudents() == 5);

        for(int i = 0; i < 5; i++){
            Student s = new Student(PawnColor.RED);
            table.addStudent(s);
        }
        assertTrue(table.getNumberOfStudents() == 10);

    }
    @Test
    public void testGetColor() {
        assertTrue(table.getColor() == PawnColor.RED);
    }
    @Test
    public void testHasProfessor() {
        assertFalse(table.hasProfessor());
        table.setProfessor(true);
        assertTrue(table.hasProfessor());
    }
    @Test
    public void testAddStudent() {
        for(int i = 0; i < 10; i++){
            Student s = new Student(PawnColor.RED);
            table.addStudent(s);
        }
        assertTrue(table.getNumberOfStudents() == 10);
    }

    @Test
    public void testSetProfessor(){
        table.setProfessor(true);
        assertTrue(table.hasProfessor());
        table.setProfessor(false);
        assertFalse(table.hasProfessor());
    }
}