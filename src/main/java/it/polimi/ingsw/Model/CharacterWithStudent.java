package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class CharacterWithStudent extends java.lang.Character {
    private final Student[] students;

    public CharacterWithStudent(int id, int cost, int numberOfStudent) {
        super(id, cost);
        this.students = new Student[numberOfStudent];
    }

    public void addStudents(List<Student> students){
        for (int i = 0; i < this.students.length; i++){
            if(this.students[i] == null) this.students[i] = students.remove(0);
        }
    }

    public List<Student> getStudents(List<Integer> indexStudent){
        List<Student> returnStudents = new ArrayList<>();

        if (indexStudent.size() > 0) {
            for (Integer i : indexStudent) {
                returnStudents.add(this.students[i]);
                this.students[i] = null;
            }
            return returnStudents;
        } else {
            return null;
        }
    }
}
