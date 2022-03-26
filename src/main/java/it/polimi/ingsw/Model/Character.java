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
        return this.ID;
    }

    public int getCost(){
        return this.cost;
    }

    public boolean getFirstUse(){
        return this.firstUse;
    }

    public void setFirstUse(){
        this.firstUse = true;
        this.cost++;
    }

}
