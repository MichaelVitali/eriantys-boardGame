package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Princess extends CharacterWithStudent  {

    private int studentIndex;

    public Princess(int id, int cost, int numberOfStudent){
        super(id, cost, numberOfStudent, "Princess");
    }

    @Override
    public void doYourJob(int playerId, int parameter){

        if (getRoundState() == 4) {
            studentIndex = parameter;
            try{

                List<Integer> indexStudentsOnCard = new ArrayList<>();
                indexStudentsOnCard.add(studentIndex);
                List<Student> studentsOnCard = new ArrayList<>(getStudents(indexStudentsOnCard));

                if (studentsOnCard.size() <= 0)
                    throw new NoMoreStudentsException();

                getRound().getGame().getGameTable().getSchoolBoards()[playerId].addStudentOnTable(studentsOnCard.get(0));
                addStudents(getRound().getGame().getGameTable().getBag().drawStudents(1));

            }catch (InvalidIndexException e){
                getRound().getGame().getPlayer(playerId).setPlayerMessage(e.getMessage());
            }catch (NoMoreStudentsException e){
                getRound().getGame().getPlayer(playerId).setPlayerMessage("You can't play this card because there are no students");
            } catch (FullTableException e) {
                getRound().getGame().getPlayer(playerId).setPlayerMessage("You can't add the chosen student on table because it is full");
            } catch (EmptyBagException e) {
                getRound().getGame().getPlayer(playerId).setPlayerMessage(e.getMessage());
            }
            deactivateEffect(true);
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessage("Select student");
        super.activateEffect(playerID, round);
        setRoundState(4);
        return this;
    }
}
