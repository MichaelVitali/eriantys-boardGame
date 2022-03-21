package it.polimi.ingsw.Model;

public class Assistant {
    private int cardValue;
    private int motherNatureMoves;

    public Assistant(int cardValue, int motherNatureMoves) {
        this.cardValue = cardValue;
        this.motherNatureMoves = motherNatureMoves;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getMotherNatureMoves() {
        return motherNatureMoves;
    }
}
