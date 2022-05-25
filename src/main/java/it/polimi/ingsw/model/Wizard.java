package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.InvalidIndexException;

import java.io.Serializable;

public enum Wizard implements Serializable {
    GREEN_WIZARD(0, "green"), YELLOW_WIZARD(1, "yellow"), PURPLE_WIZARD(2, "purple"), BLUE_WIZARD(3, "blue");
    private final int index;
    private final String printedValue;

    Wizard(int index, String printedValue) {
        this.index = index;
        this.printedValue = printedValue;
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

    @Override
    public String toString() {
        return printedValue;
    }

    public static Wizard getWizardFromString(String wizard) {
        switch (wizard) {
            case "green":
                return Wizard.GREEN_WIZARD;
            case "yellow":
                return Wizard.YELLOW_WIZARD;
            case "purple":
                return Wizard.PURPLE_WIZARD;
            case "blue":
                return Wizard.BLUE_WIZARD;
        }
        return null;
    }
}
