package it.polimi.ingsw.controller.message;

public class TerminatorMessage extends Message {
    private String message;
    public TerminatorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
