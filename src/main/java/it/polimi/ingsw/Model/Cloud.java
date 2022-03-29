package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Cloud {
    private final int numberOfStudents;
    private final List<Student> students;

    public Cloud(int numberOfStudents) {
        this.students = new ArrayList<>();
        this.numberOfStudents = numberOfStudents;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public List<Student> getStudents() {
        List<Student> returnedStudents = new ArrayList<>(students);
        returnedStudents.addAll(students);
        return returnedStudents;
    }

    public List<Student> removeStudents() {
        List<Student> removedStudents = new ArrayList<>(students);
        removedStudents.addAll(students);
        students.removeAll(removedStudents);
        return removedStudents;
    }

    public void addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
    }
}
