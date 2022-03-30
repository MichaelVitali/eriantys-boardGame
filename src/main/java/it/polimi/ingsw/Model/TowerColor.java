package it.polimi.ingsw.Model;

public enum TowerColor {
    WHITE(0), BLACK(1), GREY(2);
    private final int index;

    TowerColor(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
