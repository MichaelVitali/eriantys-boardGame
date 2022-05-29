package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.Centaur;
import it.polimi.ingsw.model.ExpertGame;
import it.polimi.ingsw.model.Herald;
import it.polimi.ingsw.model.Round;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CentaurTest {

    private Centaur character = new Centaur(6, 3);
    private ExpertGame game;

    @Before
    public void setUp() {
        List<String> nicknames = new ArrayList<>();
        nicknames.add("mike");
        nicknames.add("enri");
        game = new ExpertGame(2, nicknames);
        game.setASingleCharacter(character, 0);
    }

    @Test
    public void centaurTest() {
        int currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 0);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 1);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().activateEffect(currentPlayer, 0);
        
    }
}
