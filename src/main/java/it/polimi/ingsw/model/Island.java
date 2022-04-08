package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Island {
    /**
     * index is the index/identifier of the island in the group of islands on the game table (in the beginning).
     *      The index is created at the beginning of the match and remains the same even the island merges itself to another one
     */
    private final int index;
    private Tower tower;
    private final List<Student> students;

    /**
     * Creates an island which index is the index passed as parameter
     * @param index index of the new island
     */
    public Island(int index) {
        this.index = index;
        students = new ArrayList<>();
    }

    /**
     * Returns the index of the island in the group of island on the beginning game table
     * @return index of the island in the group of island on the beginning game table
     */
    public int getIndex() {
        return index;
    }

    /**
     * Puts the first tower of the list on the island
     * @param tower list of towers within which, on index 0, there is the tower to put on the island
     */
    public void setTowers(List<Tower> tower){
        this.tower = tower.get(0);
    }

    /**
     * Returns a list containing the tower which is placed on the island, if there isn't any tower on the island the list is empty
     * @return list containing the tower the island contains
     */
    public List<Tower> getTowers() {
        List<Tower> towersOnTheIsland = new ArrayList<>();
        if ( tower != null ) towersOnTheIsland.add(tower);
        return towersOnTheIsland;
    }

    /**
     * Removes the tower which is placed on the island from it and returns the list containing the removed tower
     * @return list containing the tower removed from the island
     */
    public List<Tower> removeTowers() {
        List<Tower> removedTowers = new ArrayList<>();
        if (tower != null) {
            removedTowers.add(tower);
            tower = null;
        }
        return removedTowers;
    }

    /**
     * Adds the student passed as parameter on the island
     * @param newStudent instance of the student to put on the island
     */
    public void addStudents(Student newStudent) {
        students.add(newStudent);
    }

    /**
     * Returns a list containing all the students placed on the island
     * @return list containing all the students placed on the island
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Returns one
     * @return one
     */
    public int getAggregation(){
        return 1;
    }
}
