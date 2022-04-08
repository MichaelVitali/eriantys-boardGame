package it.polimi.ingsw.model;

public enum PawnColor {
    YELLOW(0), BLUE(1), GREEN(2), RED(3), PINK(4);
    private final int index;

    /**
     * Constructs an element of the enumeration
     * @param index unique integer value associated with each value of the enumeration. YELLOW index is 0, BLUE index is 1, GREEN index is 2, RED index is 3, PINK index is 4
     */
    PawnColor(int index) { this.index = index; }

    /**
     * Returns the index associated with the specific value of the enumeration
     * @return unique index associated with the specific value of the enumeration
     */
    public int getIndex() { return index; }
}
