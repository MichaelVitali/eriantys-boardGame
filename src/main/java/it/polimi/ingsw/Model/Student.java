package it.polimi.ingsw.Model;

public class Student {
    private PawnColor color;

    Student(PawnColor color) {
        this.color = color;
    }

    public PawnColor getColor() {
        return color;
    }
}
