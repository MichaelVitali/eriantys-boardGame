package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EmptyBagException;
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
    }

    /*@Override
    public void calculateNextPlayer() {

    }*/

    @Override
    public void doYourJob(int playerId, int parameter) {
        //problema a tornare nello stato vecchio
        if (getRoundState() == 5) {
            getRound().getGame().getPlayer(playerId).setErrorMessage("Select island");
            studentIndex = parameter;
            setRoundState(6);
        } else if (getRoundState() == 6) {
            islandIndex = parameter;
            try {
                List<Integer> indexStudentsOnCard = new ArrayList<>();
                indexStudentsOnCard.add(studentIndex);
                List<Student> studentsOnCard = new ArrayList<>(getStudents(indexStudentsOnCard));
                if (studentsOnCard.size() <= 0) throw new NoMoreStudentsException();
                getRound().getGame().getGameTable().addStudentOnIsland(studentsOnCard.remove(0), islandIndex);
                addStudents(getRound().getGame().getGameTable().getBag().drawStudents(1));
                deactivateEffect();
            } catch (NoMoreStudentsException e) {
                getRound().getGame().getPlayer(playerId).setErrorMessage("You can't play this card because there are no students");
            } catch (InvalidIndexException e) {
                getRound().getGame().getPlayer(playerId).setErrorMessage(e.getMessage());
            } catch (EmptyBagException e) {
                //Non aggiunge nessuno studente sulla carta
            }
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) {
        round.getGame().getPlayer(playerID).setErrorMessage("Select Student");
        setRoundState(5);
        return super.activateEffect(playerID, round);
    }

    @Override
    public void setRoundState(int state){
        if (state>=0 && state<7)
            this.roundState=state;
        else roundState = -1;
    }

}
