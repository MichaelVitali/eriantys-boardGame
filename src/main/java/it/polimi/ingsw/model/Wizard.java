package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.InvalidIndexException;

import java.io.Serializable;

public enum Wizard implements Serializable {
    GREEN_WIZARD(0), YELLOW_WIZARD(1), PURPLE_WIZARD(2), BLUE_WIZARD(3);
    private final int index;

    Wizard(int index){
        this.index = index;
    }

    public int getIndex() { return index; }

    public static Wizard associateIndexToWizard(int index) throws InvalidIndexException {
        Wizard w = null;

        if (index<0 || index>4)
            throw new InvalidIndexException("There is no such wizard");

        switch (index) {
            case 0:
                w = Wizard.GREEN_WIZARD;
                break;
            case 1:
                w = Wizard.YELLOW_WIZARD;
                break;
            case 2:
                w = Wizard.PURPLE_WIZARD;
                break;
            case 3:
                w = Wizard.BLUE_WIZARD;
                break;
        }

        return w;
    }
}
