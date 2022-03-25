package it.polimi.ingsw.Model;

public enum PawnColor {
    YELLOW(1), BLUE(2), GREEN(3), RED(4), PINK(5);
    private final int index;

    PawnColor(int index) { this.index = index; }

    public int getIndex() { return index; }
}
