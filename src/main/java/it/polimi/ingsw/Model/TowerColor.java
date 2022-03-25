package it.polimi.ingsw.Model;

public enum TowerColor {
    WHITE(1), BLACK(2), GREY(3);
    private final int index;

    TowerColor(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
