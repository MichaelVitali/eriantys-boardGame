package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MergedIslandsTest {
    private Island firstIsland = new Island(0);
    private Island secondIsland = new Island (1);
    private MergedIslands mergedIslands = new MergedIslands(firstIsland, secondIsland);
    private MergedIslands mergedIslands2 = new MergedIslands(mergedIslands, new Island(2));

    @Test
    public void testSetTowers() {
        List<Tower> towers = new ArrayList<>();
        towers.add(new Tower(TowerColor.BLACK));
        towers.add(new Tower(TowerColor.BLACK));
        mergedIslands.setTowers(towers);
        assertEquals(2, mergedIslands.getTowers().size());
        for (Tower t : mergedIslands.getTowers()) {
            assertNotNull(t);
            assertEquals(TowerColor.BLACK, t.getColor());
        }

        towers.add(new Tower(TowerColor.BLACK));
        mergedIslands2.setTowers(towers);
        for (Tower t : mergedIslands2.getTowers()) {
            assertNotNull(t);
            assertEquals(TowerColor.BLACK, t.getColor());
        }
    }

    @Test
    public void testGetTowers() {
        List<Tower> towers = new ArrayList<>();
        towers.add(new Tower(TowerColor.BLACK));
        towers.add(new Tower(TowerColor.BLACK));
        mergedIslands.setTowers(towers);
        assertEquals(2, mergedIslands.getTowers().size());
        for (Tower t : mergedIslands.getTowers()) {
            assertEquals(TowerColor.BLACK, mergedIslands.getTowers().remove(0).getColor());
        }

        towers.add(new Tower(TowerColor.BLACK));
        mergedIslands2.setTowers(towers);
        assertEquals(3, mergedIslands2.getTowers().size());
        for (Tower t : mergedIslands2.getTowers()) {
            assertEquals(TowerColor.BLACK, mergedIslands2.getTowers().remove(0).getColor());
        }
    }

    @Test
    public void testRemoveTowers() {
        List<Tower> towers = new ArrayList<>();
        towers.add(new Tower(TowerColor.BLACK));
        towers.add(new Tower(TowerColor.BLACK));

        //test 2 isole
        mergedIslands.setTowers(towers);
        List<Tower> previousTowers = mergedIslands.getTowers();
        List<Tower> removedTowers = mergedIslands.removeTowers();
        assertEquals(2, removedTowers.size());
        assertEquals(previousTowers, removedTowers);
        for (Tower t : removedTowers) {
            assertEquals(TowerColor.BLACK, t.getColor());
            assertNotNull(t);
        }
        assertEquals(0, mergedIslands.removeTowers().size());

        //test tre isole
        towers.add(new Tower(TowerColor.BLACK));
        mergedIslands2.setTowers(towers);
        previousTowers = mergedIslands2.getTowers();
        removedTowers = mergedIslands2.removeTowers();
        assertEquals(3, removedTowers.size());
        assertEquals(previousTowers, removedTowers);
        for (Tower t : removedTowers) {
            assertEquals(TowerColor.BLACK, t.getColor());
            assertNotNull(t);
        }
        assertEquals(0, mergedIslands2.removeTowers().size());
    }

    @Test
    public void testGetAggregation() {
        assertEquals(mergedIslands.getAggregation(), 2);
        assertEquals(3, mergedIslands2.getAggregation());
    }

    @Test
    public void testGetStudents() {
        Student s1 = new Student(PawnColor.GREEN);
        mergedIslands.addStudents(s1);
        assertEquals(1, mergedIslands.getStudents().size());
        assertEquals(s1, mergedIslands.getStudents().get(0));

        Student s2 = new Student(PawnColor.RED);
        mergedIslands2.addStudents(s2);
        assertEquals(2, mergedIslands2.getStudents().size());
        assertEquals(s1, mergedIslands2.getStudents().get(0));
        assertEquals(s2, mergedIslands2.getStudents().get(1));
    }
}