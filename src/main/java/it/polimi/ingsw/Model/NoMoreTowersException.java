package it.polimi.ingsw.Model;

public class NoMoreTowersException extends Exception {
    private TowerColor emptySchoolboardColor;

    public NoMoreTowersException(TowerColor emptySchoolboardColor) {
        super();
        this.emptySchoolboardColor = emptySchoolboardColor;
    }

}
