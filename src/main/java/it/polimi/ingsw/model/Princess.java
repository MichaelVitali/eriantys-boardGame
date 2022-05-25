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
                if (parameter < 0 || parameter > 3) throw new InvalidIndexException("The chosen student doesn't exists\n Chose another one: ");
                List<Integer> indexStudentsOnCard = new ArrayList<>();
                indexStudentsOnCard.add(studentIndex);
                List<Student> studentsOnCard = new ArrayList<>(getStudents(indexStudentsOnCard));

                if (studentsOnCard.size() <= 0)
                    throw new NoMoreStudentsException();

                getRound().getGame().getGameTable().getSchoolBoards()[playerId].addStudentOnTable(studentsOnCard.get(0));
                getGame().getGameTable().moveProfessorToTheRightPosition(studentsOnCard.get(0).getColor());
                addStudents(getRound().getGame().getGameTable().getBag().drawStudents(1));
                deactivateEffect(true);
            }catch (InvalidIndexException | EmptyBagException e){
                getRound().getGame().getPlayer(playerId).setPlayerMessage(e.getMessage());
                getGame().sendGame();
            }catch (NoMoreStudentsException e){
                getRound().getGame().getPlayer(playerId).setPlayerMessage("You can't play this card because there are no students");
                getGame().sendGame();
            } catch (FullTableException e) {
                getRound().getGame().getPlayer(playerId).setPlayerMessage("You can't add the chosen student on table because it is full");
                getGame().sendGame();
            }
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
