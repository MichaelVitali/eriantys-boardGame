package it.polimi.ingsw.model;

import java.io.Serializable;

public enum PawnColor implements Serializable {
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

    public static PawnColor associateIndexToPawnColor(int index){

        PawnColor pc = null;

        switch (index){
            case 0:
                pc=PawnColor.YELLOW;
                break;
            case 1:
                pc=PawnColor.BLUE;
                break;
            case 2:
                pc=PawnColor.GREEN;
                break;
            case 3:
                pc=PawnColor.RED;
                break;
            case 4:
                pc=PawnColor.PINK;
                break;
        }

        return pc;
    }
}
