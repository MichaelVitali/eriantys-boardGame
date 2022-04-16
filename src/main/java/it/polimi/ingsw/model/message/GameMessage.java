package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.*;

public class GameMessage {
    private String printableMessage;
    private DisplayedBoard displayableBoard;

    public GameMessage(String printableMessage, DisplayedBoard displayableBoard) {
        this.printableMessage = printableMessage;
        this.displayableBoard = displayableBoard;
    }

    public void printMessage() {
        System.out.println(printableMessage);
    }

    public void displayBoardOptions() {
        // modo in cui vogliamo displayare le cose
        // eventualmente l'utente può scegliere mediante un elenco puntato
    }
}
