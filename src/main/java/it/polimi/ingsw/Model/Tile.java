package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

// Forse non ha senso fare Cloud e Island come sottoclassi di Tile
public abstract class Tile {
    private List<Student> students;

    public void addStudents(Student newStudent) {
        students = new ArrayList<>();
        students.add(newStudent);
    }

    public boolean removeStudent(Student studentToBeRemoved) { // Oppure passo il colore?
        return students.remove(studentToBeRemoved);
    }

    public List<Student> getStudents() {
        return students;
    }
}
