package it.polimi.ingsw.controllerTest.message;

import it.polimi.ingsw.controller.message.DoYourJobMessage;
import it.polimi.ingsw.model.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DoYourJobMessageTest {

    private DoYourJobMessage message;
    private Game game;

    @Before
    public void setUp(){
        message = new DoYourJobMessage(0,0);
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        game = new Game(2, nicknames);
    }

    @Test
    public void testPerformMove() {
        message.performMove(game);
    }
}