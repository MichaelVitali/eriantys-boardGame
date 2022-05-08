package it.polimi.ingsw.model;

import java.io.Serializable;

public class Assistant implements Serializable {
    private int cardValue;
    private int motherNatureMoves;
    public static final int MAXVALUE = 10;

    /**
     * Creates an assistant card with the given two values
     * @param cardValue value of the assistant card
     * @param motherNatureMoves number of maximum moves that mother nature can perform when a player plays the assistant card
     */
    public Assistant(int cardValue, int motherNatureMoves) {
        if (cardValue >= 1 && cardValue <= 10) this.cardValue = cardValue;
        if (motherNatureMoves >= 1 && motherNatureMoves <= 5) this.motherNatureMoves = motherNatureMoves;
    }

    /**
     * Returns the value of the card
     * @return value of the assistant card
     */
    public int getCardValue() {
        return cardValue;
    }

    /**
     * Returns the maximum number of movements mother nature can perform after the player plays this assistant card
     * @return maximum number of movements mother nature can perform after the player plays this assistant card
     */
    public int getMotherNatureMoves() {
        return motherNatureMoves;
    }
}
