package it.polimi.ingsw.model;

public class Assistant {
    private int cardValue;
    private int motherNatureMoves;
    public static final int MAXVALUE = 10;

    /**
     * Constructs an assistant card with the specified two values
     * @param cardValue value of the assistant card
     * @param motherNatureMoves number of maximum moves that mother nature can do when a player plays the assistant card
     */
    public Assistant(int cardValue, int motherNatureMoves) {
        if (cardValue >= 1 && cardValue <= 10) this.cardValue = cardValue;
        if (motherNatureMoves >= 1 && motherNatureMoves <= 5) this.motherNatureMoves = motherNatureMoves;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getMotherNatureMoves() {
        return motherNatureMoves;
    }
}
