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
        tower = t.remove(0);
    }

    public List<Tower> getTower() {
        List<Tower> towerOnTheIsland = new ArrayList<>();
        towerOnTheIsland.add(tower);
        return towerOnTheIsland;
    }

    public List<Tower> removeTower() {
        List<Tower> removedTower =  new ArrayList<>();
        removedTower.add(tower);
        tower = null;
        return removedTower;
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
