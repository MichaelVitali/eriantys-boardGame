package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class MergedIslands extends Island {
    private final Island firstIstand;
    private final Island secondIstand;

    public MergedIslands(Island firstIstand, Island secondIstand) {
        super(firstIstand.getIndex());
        this.firstIstand = firstIstand;
        this.secondIstand = secondIstand;
    }

    public void setTowers(List<Tower> towersToSet){
        firstIstand.setTowers(towersToSet);
        secondIstand.setTowers(towersToSet);
    }
    public List<Tower> getTowers() {
        List<Tower> towersOnTheIslands = new ArrayList<>();
        towersOnTheIslands.addAll(firstIstand.getTowers());
        towersOnTheIslands.addAll(secondIstand.getTowers());
        for(Tower tower : towersOnTheIslands) {
            if(tower == null) { } // exception
        }
        return towersOnTheIslands;
    }

    public List<Tower> removeTowers() {
        List<Tower> towersOnTheIslands = new ArrayList<>();
        towersOnTheIslands.addAll(firstIstand.removeTowers());
        towersOnTheIslands.addAll(secondIstand.removeTowers());
        return towersOnTheIslands;
    }

    public int getAggregation(){
        return firstIstand.getAggregation() + secondIstand.getAggregation();
    }

    public List<Student> getStudents(){
        List<Student> studentsOnTheIslands = new ArrayList<>();
        studentsOnTheIslands.addAll(firstIstand.getStudents());
        studentsOnTheIslands.addAll(secondIstand.getStudents());
        return studentsOnTheIslands;
    }
}
