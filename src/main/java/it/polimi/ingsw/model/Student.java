package it.polimi.ingsw.model;

import java.io.Serializable;

public class Student implements Serializable {
    private final PawnColor color;

    /**
     * Creates a student whose color is the color passed as parameter
     * @param color color of the new student
     */
    public Student(PawnColor color) {
        this.color = color;
    }

    /**
     * Returns the color of the student
     * @return color of the student
     */
    public PawnColor getColor() {
        return color;
    }
}
