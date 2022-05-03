package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import java.util.ArrayList;
import java.util.List;

public class Character11 extends CharacterWithStudent{

    private int studentIndex;

    public Character11(int id, int cost, int numberOfStudent){
        super(id, cost, numberOfStudent);
    }

    @Override
    public void doYourJob(int playerId, int parameter){

        if (getRoundState() == 5) {
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
            deactivateEffect();
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) {
        round.getGame().getPlayer(playerID).setPlayerMessage("Select student");
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