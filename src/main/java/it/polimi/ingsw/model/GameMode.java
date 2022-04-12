package it.polimi.ingsw.model;

public enum GameMode {
    NORMAL("Normal mode"), EXPERT("Expert mode");

    private String mode;

    private GameMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
