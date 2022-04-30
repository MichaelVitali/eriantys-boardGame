package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;

public class Character7 extends CharacterWithStudent{

    private List<Integer> studentsIndexOnCard;
    private List<Integer> studentsIndexOnEntrance;

    public Character7(int id, int cost, int numberOfStudent) {
        super(id, cost, numberOfStudent);
        studentsIndexOnCard = new ArrayList<>();
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if (parameter == -1) setRoundState(7);
        if (parameter == -2 || getRoundState() == 11) applyEffect(playerId);
        switch (getRoundState()) {
            case 5:
            case 6:
            case 7:
                studentsIndexOnCard.add(parameter);
                setRoundState(getRoundState()+1);
                break;
            case 8:
            case 9:
            case 10:
                studentsIndexOnEntrance.add(parameter);
                setRoundState(getRoundState()+1);
                break;
        }
    }

    public void applyEffect(int playerIndex) {
        try {
            if (studentsIndexOnEntrance.size() != studentsIndexOnCard.size()) throw new InvalidIndexException("Error on index");
            List<Student> newStudentsOnEntrance = new ArrayList<>(getStudents(studentsIndexOnCard));
            List<Student> newStudentsOnCard = new ArrayList<>();
            for (Integer i : studentsIndexOnEntrance)
                newStudentsOnCard.add(getRound().getGame().getGameTable().getSchoolBoards()[playerIndex].removeStudentFromEntrance(i));
            addStudents(newStudentsOnCard);
            getRound().getGame().getGameTable().getSchoolBoards()[playerIndex].addStudentsOnEntrance(newStudentsOnEntrance);
        } catch (InvalidIndexException e) {
            setErrorMessage(playerIndex, e.getMessage());
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) {
        round.getGame().getPlayer(playerID).setErrorMessage("Select Student on card");
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
