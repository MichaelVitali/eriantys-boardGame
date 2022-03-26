package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private int index;
    private Tower tower;
    private final List<Student> students;

    public Island(int index) {
        this.index = index;
        students = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    public void setTowers(List<Tower> t){
        tower = t.get(0);
    }

    public List<Tower> getTowers() {
        List<Tower> towersOnTheIsland = new ArrayList<>();
        towersOnTheIsland.add(tower);
        return towersOnTheIsland;
    }

    public List<Tower> removeTowers() {
        List<Tower> removedTowers = new ArrayList<>();
        removedTowers.add(tower);
        tower = null;
        return removedTowers;
    }

    public void addStudents(Student newStudent) {
        students.add(newStudent);
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getAggregation(){
        return 1;
    }
}
