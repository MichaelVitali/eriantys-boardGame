package it.polimi.ingsw.Model;

public class Island extends Tile {
    private Tower tower;

    public Island() {
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}
