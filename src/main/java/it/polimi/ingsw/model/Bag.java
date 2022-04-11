package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EmptyBagException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
    private static Bag bag;
    private final List<Student> students;

    private Bag() {
        students = new ArrayList<>();
        for (int i = 0; i < 26 ; i++) {
            students.add(new Student(PawnColor.YELLOW));
            students.add(new Student(PawnColor.BLUE));
            students.add(new Student(PawnColor.GREEN));
            students.add(new Student(PawnColor.RED));
            students.add(new Student(PawnColor.PINK));
        }
    }

    /**
     * Draws a certain amount of students from the bag so the bag remove the students from itself and return them to the caller
     * @param numberOfStudents number of students to draw
     * @return list of students required
     * @throws EmptyBagException if the bag doesn't contain the required amount of students
     */
    public List<Student> drawStudents(int numberOfStudents) throws EmptyBagException {
        List<Student> drawnStudents = new ArrayList<>();
        if (numberOfStudents > students.size()) {
            List<Student> returnedStudents = new ArrayList<>();
            returnedStudents.addAll(students);
            students.removeAll(students);
            throw new EmptyBagException(returnedStudents);
        }
        for (int i = 0; i < numberOfStudents; i++) {
            int index = new Random().nextInt(students.size());
            drawnStudents.add(students.remove(index));
        }
        return drawnStudents;
    }

    /**
     * Returns the instance of the Bag from which the students will be drawn
     * @return instance of the bag
     */
    public static Bag getBag() {
        if (bag == null) bag = new Bag();
        return bag;
    }

    /**
     * Inserts every student of the list in the bag
     * @param newStudents students to insert in the bag
     */
    public void addStudents(List<Student> newStudents){
        students.addAll(newStudents);
    }

}