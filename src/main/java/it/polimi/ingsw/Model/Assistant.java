package it.polimi.ingsw.Model;

public class Assistant {
    private int cardValue;
    private int motherNatureMoves;
    public static final int MAXVALUE=10;

    //manca eccezione nel caso di valori sbagliati
    public Assistant(int cardValue, int motherNatureMoves) {
        if(cardValue >= 1 && cardValue <= 10) this.cardValue = cardValue;
        if(motherNatureMoves >= 1 && motherNatureMoves <= 5) this.motherNatureMoves = motherNatureMoves;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getMotherNatureMoves() {
        return motherNatureMoves;
    }
}
