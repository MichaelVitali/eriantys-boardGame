package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterWithStudent extends Character {
    private final Student[] students;

    /**
     * Creates a character that can contain a certain amount of students
     * @param id integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     * @param numberOfStudent maximum number of students the card can contain
     */
    public CharacterWithStudent(int id, int cost, int numberOfStudent) {
        super(id, cost);
        students = new Student[numberOfStudent];
    }

    /**
     * Adds the students passed as parameter on the character card
     * @param students students to put on the card
     */
    public void addStudents(List<Student> students) {
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] == null) this.students[i] = students.remove(0);
        }
    }

    /**
     * Returns the list of students on the card at the indexes passed as parameter. The return is an empty List if there list of indexes is empty
     * @param indexesOfTheStudents list of indexes at which the required students are
     * @return list of students at the required indexes
     */
    public List<Student> getStudents(List<Integer> indexesOfTheStudents) {
        List<Student> returnedStudents = new ArrayList<>();

        if (indexesOfTheStudents.size() > 0) {
            for (Integer i : indexesOfTheStudents) {
                returnedStudents.add(this.students[i]);
                this.students[i] = null;
            }
        }
        return returnedStudents;
    }
}