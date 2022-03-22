package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.Tower;

import it.polimi.ingsw.Model.TowerColor;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TowerTest {

    private Tower t = new Tower(TowerColor.BLACK);

    @Test
    public void testGetColor() {
        assertTrue(t.getColor() == TowerColor.BLACK);
    }
}