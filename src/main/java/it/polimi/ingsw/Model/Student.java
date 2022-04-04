package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.PawnColor;

public class Student {
    private final PawnColor color;

    public Student(PawnColor color) {
        this.color = color;
    }

    public PawnColor getColor() {
        return color;
    }
}
