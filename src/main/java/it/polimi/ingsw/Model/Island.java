package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private Tower tower;
    private List<Student> students;

    public Island() {
        students = new ArrayList<>();
    }

    public void setTower(List<Tower> t){
        tower=t.remove(0);
    }

    public Tower getTower() {
        return tower;
    }

    public int getAggregation(){
        return 1;
    }

    public void addStudents(Student newStudent) {
        students.add(newStudent);
    }

    public List<Student> getStudents() {
        return students;
    }
}
