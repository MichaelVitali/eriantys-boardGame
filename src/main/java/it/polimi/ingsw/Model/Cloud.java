package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Cloud {
    private int numberOfStudents;
    private List<Student> students;

    public Cloud(int numberOfStudents) {
        this.students = new ArrayList<>();
        this.numberOfStudents = numberOfStudents;
    }

    public void addStudents(List<Student> newStudent) {
        students.addAll(newStudent);
    }

    public boolean removeStudent(Student studentToBeRemoved) { // Oppure passo il colore?
        return students.remove(studentToBeRemoved);
    }

    public List<Student> getStudents() {
        return students;
    }
}
