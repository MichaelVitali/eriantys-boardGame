package it.polimi.ingsw.model;

public class Tower {
    private final TowerColor color;

    /**
     * Creates a tower of the color passed as parameter
     * @param color color of the new tower
     */
    public Tower(TowerColor color) {
        this.color = color;
    }

    /**
     * Returns the color of the tower
     * @return color of the tower
     */
    public TowerColor getColor() {
        return color;
    }
}
