package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.exception.*;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class SchoolBoardTest {

    private SchoolBoard schoolBoard = new SchoolBoard(9, TowerColor.BLACK, 8);

    @Test
    public void testAddStudentOnTable() {
        Student[] entrance = schoolBoard.getStudentsFromEntrance();
        int[] numberOfStudentsForColor = new int[5];
        for (int i = 0; i < 9; i++) {
            schoolBoard.addStudentOnTable(i);
            numberOfStudentsForColor[entrance[i].getColor().getIndex()]++;
        }

        for (PawnColor color : PawnColor.values()) {
            assertEquals(numberOfStudentsForColor[color.getIndex()], schoolBoard.getNumberOfStudentsOnTable(color));
        }
    }

    @Test
    public void testTestAddStudentOnTable() {
        Student[] entrance = schoolBoard.getStudentsFromEntrance();
        int[] numberOfStudentsForColor = new int[5];
        for (Student s : entrance) {
            schoolBoard.addStudentOnTable(s);
            numberOfStudentsForColor[s.getColor().getIndex()]++;
        }

        for (PawnColor color : PawnColor.values()) {
            assertEquals(numberOfStudentsForColor[color.getIndex()], schoolBoard.getNumberOfStudentsOnTable(color));
        }
    }

    @Test
    public void testAddStudentsOnEntrance() throws EmptyBagException, InvalidIndexException {
        Student[] entrance = schoolBoard.getStudentsFromEntrance();
        for (int i = 0; i < 9; i++) {
            schoolBoard.removeStudentFromEntrance(i);
        }
        schoolBoard.addStudentsOnEntrance(Bag.getBag().drawStudents(9));
        for (int i = 0; i < 9; i++) {
            assertNotEquals(entrance[i], schoolBoard.getStudentsFromEntrance()[i]);
        }
    }

    @Test
    public void testGetProfessors() {
        assertEquals(0, schoolBoard.getProfessors().size());
        schoolBoard.setProfessor(PawnColor.YELLOW, true);
        schoolBoard.setProfessor(PawnColor.BLUE, true);

        List<PawnColor> professors = schoolBoard.getProfessors();
        assertEquals(PawnColor.YELLOW, professors.remove(0));
        assertEquals(PawnColor.BLUE, professors.remove(0));

        schoolBoard.setProfessor(PawnColor.BLUE, false);
        professors = schoolBoard.getProfessors();
        assertEquals(PawnColor.YELLOW, professors.remove(0));
    }

    @Test
    public void testSetProfessor() {
        for(PawnColor color : PawnColor.values()) {
            schoolBoard.setProfessor(color, true);
        }
        List<PawnColor> professors = schoolBoard.getProfessors();
        for (PawnColor color : PawnColor.values()) {
            assertEquals(color, professors.remove(0));
        }

        for(PawnColor color : PawnColor.values()) {
            schoolBoard.setProfessor(color, false);
        }
        assertEquals(0, schoolBoard.getProfessors().size());
    }

    @Test
    public void testGetTowers() throws InvalidIndexException, NoMoreTowersException {
        for (Tower t : schoolBoard.getTowers()){
            assertNotNull(t);
            assertEquals(TowerColor.BLACK, t.getColor());
        }

        schoolBoard.removeTowers(8);
        assertEquals(0, schoolBoard.getTowers().size());
    }

    @Test
    public void testRemoveTowers() {
        try {
            schoolBoard.removeTowers(9);
        }catch (InvalidIndexException | NoMoreTowersException e) {
            assertTrue(true);
        }
        try {
            List<Tower> towers = schoolBoard.removeTowers(8);
            assertEquals(8, towers.size());
            schoolBoard.removeTowers(8);
        }catch (NoMoreTowersException | InvalidIndexException e){
            assertTrue(true);
        }

    }

    @Test
    public void testAddTowers() throws InvalidIndexException, NoMoreTowersException {
        schoolBoard.removeTowers(8);
        List<Tower> towers = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            towers.add(new Tower(TowerColor.BLACK));
        }
        schoolBoard.addTowers(towers);
        for (Tower t : schoolBoard.getTowers()) {
            assertEquals(t, towers.remove(0));
        }

    }

    @Test
    public void testRemoveStudentFromEntrance() {
        try {
            for (int i = 0; i < 9; i++) {
                schoolBoard.removeStudentFromEntrance(i);
            }
            for (int i = 0; i < 9; i++) {
                assertNull(schoolBoard.getStudentsFromEntrance()[i]);
            }
            schoolBoard.removeStudentFromEntrance(1);
        }catch (InvalidIndexException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetNumberOfStudentsOnTable() {
        Student[] entrance = schoolBoard.getStudentsFromEntrance();
        int[] numberOfStudentsForColor = new int[5];
        for (int i = 0; i < 9; i++) {
            schoolBoard.addStudentOnTable(i);
            numberOfStudentsForColor[entrance[i].getColor().getIndex()]++;
        }

        for(PawnColor color : PawnColor.values()) {
            assertEquals(numberOfStudentsForColor[color.getIndex()], schoolBoard.getNumberOfStudentsOnTable(color));
        }
    }

    @Test
    public void testGetTowersColor() {
        assertEquals(TowerColor.BLACK, schoolBoard.getTowersColor());
    }

    @Test
    public void testGetStudentsFromEntrance() throws InvalidIndexException {
        for (int i = 0; i < 9; i++) {
            assertNotNull(schoolBoard.getStudentsFromEntrance()[i]);
            schoolBoard.removeStudentFromEntrance(i);
        }
        for (int i = 0; i < 9; i++) {
            assertNull(schoolBoard.getStudentsFromEntrance()[i]);
        }
    }
}