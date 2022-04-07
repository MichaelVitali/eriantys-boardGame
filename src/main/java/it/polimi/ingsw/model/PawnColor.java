package it.polimi.ingsw.model;

public enum PawnColor {
    YELLOW(0), BLUE(1), GREEN(2), RED(3), PINK(4);
    private final int index;

    PawnColor(int index) { this.index = index; }

    public int getIndex() { return index; }
}
