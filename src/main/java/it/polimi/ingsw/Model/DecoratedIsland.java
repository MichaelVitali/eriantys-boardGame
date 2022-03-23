package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class DecoratedIsland extends Island{
    private Island subIsland;

    public void setTower(List<Tower> t){
        super.setTower(t);
        subIsland.setTower(t);
    }
    public List<Tower> getTower() {
        List<Tower> towersOnTheIslands = new ArrayList<>();
        towersOnTheIslands.addAll(super.getTower());
        towersOnTheIslands.addAll(subIsland.getTower());
        return towersOnTheIslands;
    }

    public List<Tower> removeTower() {
        List<Tower> towersOnTheIslands = new ArrayList<>();
        towersOnTheIslands.addAll(super.removeTower());
        super.setTower(null);
        towersOnTheIslands.addAll(subIsland.removeTower());
        return towersOnTheIslands;
    }

    public int getAggregation(){
        return 1+subIsland.getAggregation();
    }

    public List<Student> getStudents(){
        List<Student> studentsOnTheIslands = new ArrayList<>();
        studentsOnTheIslands.addAll(getStudents());
        studentsOnTheIslands.addAll(subIsland.getStudents());
        return studentsOnTheIslands;
    }
}
