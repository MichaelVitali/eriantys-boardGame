package it.polimi.ingsw.model;

import java.io.Serializable;

public enum GameMode implements Serializable {
    NORMAL("Normal mode"), EXPERT("Expert mode");

    private String mode;

    private GameMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
