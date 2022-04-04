package it.polimi.ingsw.Model.exception;

import it.polimi.ingsw.Model.TowerColor;

public class NoMoreTowersException extends Exception {
    private TowerColor emptySchoolboardColor;

    public NoMoreTowersException(TowerColor emptySchoolboardColor) {
        super();
        this.emptySchoolboardColor = emptySchoolboardColor;
    }

    public TowerColor getEmptySchoolboardColor() {
        return emptySchoolboardColor;
    }
}
