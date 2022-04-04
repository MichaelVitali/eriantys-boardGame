package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.Bag;
import it.polimi.ingsw.Model.CharacterWithStudent;
import it.polimi.ingsw.Model.exception.EmptyBagException;
import it.polimi.ingsw.Model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CharacterWithStudentTest {
    private CharacterWithStudent c = new CharacterWithStudent(1, 2, 4);
    @Test
    public void testAddStudents() throws EmptyBagException {
        c.addStudents(Bag.getBag().drawStudents(4));
        List<Integer> indexStudents = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            indexStudents.add(i);
        }
        for(Student s : c.getStudents(indexStudents)){
            assertNotNull(s);
        }
    }
    @Test
    public void testGetStudents() throws EmptyBagException {
        c.addStudents(Bag.getBag().drawStudents(4));
        List<Integer> indexStudents = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            indexStudents.add(i);
            assertNotNull(c.getStudents(indexStudents));
            indexStudents.clear();
        }
        assertNull(c.getStudents(indexStudents));
    }
}