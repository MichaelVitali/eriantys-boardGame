package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.NoMoreStudentsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Monk extends CharacterWithStudent {

    private int studentIndex;
    private int islandIndex;

    /**
     * Creates a character that can contain a certain amount of students
     *
     * @param id              integer that identifies the character card
     * @param cost            amount of money needed to activate the card effect
     * @param numberOfStudent maximum number of students the card can contain
     */
    public Monk(int id, int cost, int numberOfStudent) {
        super(id, cost, numberOfStudent, "Monk");
    }


    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
            try {
                if (parameter < 0 || parameter > 3) throw new InvalidIndexException("The student doesn't exists\nChose another one:");
                getRound().getGame().getPlayer(playerId).setPlayerMessage("Select island");
                studentIndex = parameter;
                setRoundState(5);
            } catch (InvalidIndexException e) {
                setPlayerMessage(playerId, e.getMessage());
                getGame().sendGame();
            }
        } else if (getRoundState() == 5) {
            try {
                if (parameter < 0 || parameter > 11) throw new InvalidIndexException("The island doesn't exists\nChose another one:");
                islandIndex = parameter;
                List<Integer> indexStudentsOnCard = new ArrayList<>();
                indexStudentsOnCard.add(studentIndex);
                List<Student> studentsOnCard = new ArrayList<>(getStudents(indexStudentsOnCard));
                if (studentsOnCard.size() <= 0) throw new NoMoreStudentsException();
                getRound().getGame().getGameTable().addStudentOnIsland(studentsOnCard.remove(0), islandIndex);
                addStudents(getRound().getGame().getGameTable().getBag().drawStudents(1));
                deactivateEffect(true);
            } catch (NoMoreStudentsException e) {
                setPlayerMessage(playerId,"You can't play this card because there are no students");
                getGame().sendGame();
            } catch (InvalidIndexException e) {
                setPlayerMessage(playerId, e.getMessage());
                getGame().sendGame();
            } catch (EmptyBagException e) {
                //Non aggiunge nessuno studente sulla carta
            }
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessage("Select Student on card");
        super.activateEffect(playerID, round);
        setRoundState(4);
        return this;
    }
}
