package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.NoMoreStudentsException;

import java.util.ArrayList;
import java.util.List;

public class Character1 extends CharacterWithStudent {

    private int studentIndex;
    private int islandIndex;

    /**
     * Creates a character that can contain a certain amount of students
     *
     * @param id              integer that identifies the character card
     * @param cost            amount of money needed to activate the card effect
     * @param numberOfStudent maximum number of students the card can contain
     */
    public Character1(int id, int cost, int numberOfStudent) {
        super(id, cost, numberOfStudent);
        setRoundState(5);
    }

    /*@Override
    public void calculateNextPlayer() {

    }*/

    @Override
    public void doYourJob(int playerId, int parameter) throws InvalidIndexException { ////// controlliamo eccezione
        if (getRoundState() == 5) {
            getGame().getPlayer(playerId).setErrorMessage("Select island");
            studentIndex = parameter;
            setRoundState(6);
        } else if (getRoundState() == 6) {
            islandIndex = parameter;
            List<Student> studentsOnCard = new ArrayList<>(getStudents(new ArrayList<Integer>(studentIndex)));
            try {
                if (studentsOnCard.size() <= 0) throw new NoMoreStudentsException();
                Student newStudent = studentsOnCard.remove(0);
                getGame().getGameTable().addStudentOnIsland(newStudent, islandIndex);
                deactivateEffect();
            } catch (NoMoreStudentsException e) {
                // bisogna capire come dire all'utente che non ci sono più studenti e non può usare l'effetto
            }
        }
    }
}
