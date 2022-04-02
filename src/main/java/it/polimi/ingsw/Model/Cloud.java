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
        return new ArrayList<>(students);
    }

    public List<Student> removeStudents() {
        List<Student> removedStudents = new ArrayList<>(students);
        students.clear();
        return removedStudents;
    }

    public void addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
    }
}
