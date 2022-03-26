package it.polimi.ingsw.Model;

import com.sun.tools.javac.code.Attribute;

import java.util.ArrayList;
import java.util.List;

public class CharacterWithStudent extends Character{
    private Student[] students;

    public CharacterWithStudent(int id, int cost, int numberOfStudent) {
        super(id, cost);
        this.students = new Student[numberOfStudent];
    }

    public void addStudents(List<Student> students){
        for(int i = 0; i < this.students.length; i++){
            if(this.students[i] == null) this.students[i] = students.remove(0);
        }
    }

    public List<Student> getStudents(List<Integer> indexStudent){
        List<Student> returnStudents = new ArrayList<>();
        for(int i = 0; i < this.students.length; i++){
            if(i == indexStudent.remove(0)) {
                returnStudents.add(this.students[i]);
                this.students[i] = null;
            }
        }
        return returnStudents;
    }
}
