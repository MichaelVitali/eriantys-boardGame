package it.polimi.ingsw.Model;

public class Character {
    private final int ID;
    private int cost;
    private boolean firstUse;

    public Character(int id, int cost){
        this.ID = id;
        this.cost = cost;
        this.firstUse = false;

    }

    public int getID(){
        return ID;
    }

    public int getCost(){
        return cost;
    }

    public boolean getFirstUse(){
        return firstUse;
    }

    public void setFirstUse(){
        this.firstUse = true;
        this.cost++;
    }

}
