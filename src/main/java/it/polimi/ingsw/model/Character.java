package it.polimi.ingsw.model;

public class Character {
    private final int ID;
    private int cost;
    private boolean firstUse;

    private Round oldRound;

    /**
     * Creates a character card with the given two values
     * @param id integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public Character(int id, int cost){
        this.ID = id;
        this.cost = cost;
        firstUse = false;

    }

    /**
     * Returns the identifier of the character card
     * @return the identifier of the character card
     */
    public int getID(){
        return ID;
    }

    /**
     * Returns the cost of activation of the card
     * @return the cost of activavtion of the card
     */
    public int getCost(){
        return cost;
    }

    /**
     * Returns true if the character card has been used one or more times and returns false if its effect hasn't been activated yet
     * @return the value of usage of the character card - false if it hasn't been used yet
     */
    public boolean getFirstUse(){
        return firstUse;
    }

    /**
     * Sets the card as "already been used once" so it costs one more coin to be activated that the initial cost
     */
    public void setFirstUse(){
        firstUse = true;
        cost++;
    }
}
