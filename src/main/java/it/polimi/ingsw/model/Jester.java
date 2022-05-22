package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;

public class Jester extends CharacterWithStudent{

    private List<Integer> studentsIndexOnCard;
    private List<Integer> studentsIndexOnEntrance;
    private int countCard;
    private int countEntrance;

    public Jester(int id, int cost, int numberOfStudent) {
        super(id, cost, numberOfStudent, "Jester");
        studentsIndexOnCard = new ArrayList<>();
        studentsIndexOnEntrance = new ArrayList<>();
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
                getRound().getGame().getPlayer(playerId).setPlayerMessage("Select student on card");
                countCard = parameter;
                countEntrance = parameter;
                setRoundState(5);
        } else if (getRoundState() == 5) {
            if (countCard > 0) {
                studentsIndexOnCard.add(parameter);
                countCard -= 1;
            }
            if(countCard == 0) {
                setRoundState(6);
                getRound().getGame().getPlayer(playerId).setPlayerMessage("Select student on entrance");
            }
        } else if (getRoundState() == 6) {
            if (countEntrance > 0) {
                studentsIndexOnEntrance.add(parameter);
                countEntrance -= 1;
            }
            if(countEntrance == 0){
                try {
                    if (studentsIndexOnEntrance.size() != studentsIndexOnCard.size())
                        throw new InvalidIndexException("Error on index character 7");
                    List<Student> newStudentsOnEntrance = new ArrayList<>(getStudents(studentsIndexOnCard));
                    List<Student> newStudentsOnCard = new ArrayList<>();
                    for (Integer i : studentsIndexOnEntrance)
                        newStudentsOnCard.add(getRound().getGame().getGameTable().getSchoolBoards()[playerId].removeStudentFromEntrance(i));
                    addStudents(newStudentsOnCard);
                    getRound().getGame().getGameTable().getSchoolBoards()[playerId].addStudentsOnEntrance(newStudentsOnEntrance);
                    deactivateEffect();
                } catch (InvalidIndexException e) {
                    setPlayerMessage(playerId, e.getMessage()); // non penso sia da mostrare al player tale errore, magari chiediamo di nuovo l'inserimento
                }
            }
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessage("How many Students do you want to change");
        Round r = super.activateEffect(playerID, round);
        setRoundState(4);
        return r;
    }
}
