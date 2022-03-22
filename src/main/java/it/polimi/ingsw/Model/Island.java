package it.polimi.ingsw.Model;

import java.util.List;

public class Island extends Tile {
    private Tower tower;

    public Island() {
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
}
