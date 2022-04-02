package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IslandTest {

    private Island island = new Island(1);

    @Test
    public void testGetIndex() {
        assertEquals(1, island.getIndex());
    }

    @Test
    public void testSetTowers() {
        List<Tower> t = new ArrayList<>() ;
        t.add(new Tower(TowerColor.BLACK));
        island.setTowers(t);
        assertEquals(t.get(0), island.getTowers().get(0));
    }

    @Test
    public void testGetTowers() {
        List<Tower> t = new ArrayList<>() ;
        t.add(new Tower(TowerColor.BLACK));
        island.setTowers(t);
        assertEquals(t.get(0), island.getTowers().get(0));

    }

    @Test
    public void testRemoveTowers() {
        List<Tower> t = new ArrayList<>() ;
        t.add(new Tower(TowerColor.BLACK));
        island.setTowers(t);
        assertEquals(t.get(0), island.removeTowers().get(0));
        assertNull(island.getTowers());
    }

    @Test
    public void testAddStudents() throws EmptyBagException {
        for(int i = 0; i < 3; i++){
            island.addStudents(Bag.getBag().drawStudents(1).get(0));
            assertNotNull(island.getStudents());
        }
        assertEquals(3, island.getStudents().size());
    }

    @Test
    public void testGetStudents() throws EmptyBagException {
        for(int i = 0; i < 3; i++){
            Student s = Bag.getBag().drawStudents(1).get(0);
            island.addStudents(s);
            assertNotNull(island.getStudents());
            assertEquals(s, island.getStudents().get(i));
        }
        assertEquals(3, island.getStudents().size());
    }

    @Test
    public void testGetAggregation() {
        assertEquals(1, island.getAggregation());
    }
}