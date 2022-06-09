package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.Centaur;
import it.polimi.ingsw.model.ExpertGame;
import it.polimi.ingsw.model.Postman;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PostmanTest {

    private Postman character = new Postman(4, 1);
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
    public void postmanTest1() throws InvalidIndexException {
        game.getGameTable().changeMotherNaturePosition(0);
        int currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 1);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 2);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().activateEffect(currentPlayer, 0);
        game.getRound().addStudentOnTable(currentPlayer, 0);
        game.getRound().addStudentOnTable(currentPlayer, 1);
        game.getRound().addStudentOnTable(currentPlayer, 2);
        int motherNaturePosition = game.getGameTable().getMotherNaturePosition();
        game.getRound().changeMotherNaturePosition(currentPlayer, motherNaturePosition + 1);
        assertEquals(game.getGameTable().getMotherNaturePosition(), motherNaturePosition + 1);
    }

    @Test
    public void postmanTest2() throws InvalidIndexException {
        game.getGameTable().changeMotherNaturePosition(0);
        int currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 1);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 2);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().activateEffect(currentPlayer, 0);
        game.getRound().addStudentOnTable(currentPlayer, 0);
        game.getRound().addStudentOnTable(currentPlayer, 1);
        game.getRound().addStudentOnTable(currentPlayer, 2);
        int motherNaturePosition = game.getGameTable().getMotherNaturePosition();
        game.getRound().changeMotherNaturePosition(currentPlayer, motherNaturePosition + 2);
        assertEquals(game.getGameTable().getMotherNaturePosition(), motherNaturePosition + 2);
    }

    @Test
    public void postmanTest3() throws InvalidIndexException {
        game.getGameTable().changeMotherNaturePosition(0);
        int currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 1);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().playAssistant(currentPlayer, 2);
        currentPlayer = game.getRound().getPlayerOrder()[game.getRound().getIndexOfPlayerOnTurn()];
        game.getRound().activateEffect(currentPlayer, 0);
        game.getRound().addStudentOnTable(currentPlayer, 0);
        game.getRound().addStudentOnTable(currentPlayer, 1);
        game.getRound().addStudentOnTable(currentPlayer, 2);
        int motherNaturePosition = game.getGameTable().getMotherNaturePosition();
        game.getRound().changeMotherNaturePosition(currentPlayer, motherNaturePosition + 3);
        assertEquals(game.getGameTable().getMotherNaturePosition(), motherNaturePosition + 3);
    }
}
