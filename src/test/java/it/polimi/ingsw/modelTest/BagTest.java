package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.Model.Bag;
import it.polimi.ingsw.Model.Student;
import it.polimi.ingsw.Model.PawnColor;

import org.junit.Test;;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class BagTest {
    private Bag bag;
    private boolean flag;
    private int arrayCountColor[];

    @Test
    public void testDrowStudent() {
        arrayCountColor = new int[5];
        //test bag not null because Singleton
        bag = bag.getBag();
        assertNotNull(bag);

        //test remove all student and controll color
        for(int j = 0; j < 130; j++) {
            flag = false;
            Student s = bag.drowStudent();
            assertNotNull(s);

            for (PawnColor c : PawnColor.values()) {
                if (c == s.getColor()) {
                    arrayCountColor[s.getColor().getIndex()-1]++;
                    flag = true;
                    break;
                }
            }
            assertTrue(flag);
        }
        //controll number of student foreach PawnColor
        for(int i = 0; i < 5; i++) assertTrue(arrayCountColor[i] == 26);
    }
    @Test
    public void testGetBag() {
        bag = bag.getBag();
        assertNotNull(bag);
    }
}