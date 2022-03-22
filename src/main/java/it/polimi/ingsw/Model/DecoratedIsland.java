package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class DecoratedIsland extends Island{
    private Island subIsland;

    //public Tower getTower(){}

    public void setTower(List<Tower> t){
        super.setTower(t);
        subIsland.setTower(t);
    }

    public int getAggregation(){
        return 1+subIsland.getAggregation();
    }

    public List<Student> getStudents(){
        List<Student> tmp = new ArrayList<>();
        tmp.addAll(getStudents());
        tmp.addAll(subIsland.getStudents());
        return tmp;
    }
}
