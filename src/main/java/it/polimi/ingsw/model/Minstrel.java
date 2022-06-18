package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Minstrel extends Character  {

    private int countEntrance;
    private int countTable;
    private int wantToGoOn;
    private List<Integer> entranceIndexes;
    private List<Integer> tableIndexes;

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public Minstrel(int id, int cost) {
        super(id, cost, "Minsterl");
        countTable = 0;
        countEntrance = 0;
        entranceIndexes = new ArrayList<>();
        tableIndexes = new ArrayList<>();
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
            try {
                if (parameter > 2 || parameter < 0) throw new InvalidIndexException("The number of students is wrong. How many students do you want to change? {1, 2}");
                if (getRound().getGame().getGameTable().getSchoolBoards()[playerId].getTotalNumberOfStudentsOnTables() < parameter) throw new InvalidIndexException("Yuo don't have enought students! Select another number");
                setPlayerMessage(playerId,"Select an index for a student on entrance:");
                countTable = parameter;
                countEntrance = parameter;
                setRoundState(5);
            } catch (InvalidIndexException e){
                setPlayerMessage(playerId, e.getMessage());
            }
        } else if (getRoundState() == 5) {
            if (countEntrance > 0) {
                try {
                    if (parameter < 0 || (parameter > 6 && getGame().getNumberOfPlayers() == 2) || (parameter > 6 && getGame().getNumberOfPlayers() == 4) || (parameter > 8 && getGame().getNumberOfPlayers() == 3) || getGame().getGameTable().getSchoolBoards()[playerId].getStudentsFromEntrance()[parameter] == null) throw new InvalidIndexException("The student doesn't exists\n Chose another one: ");
                    if (entranceIndexes.contains(parameter)) throw new InvalidIndexException("The student is already chosen\n Chose another one: ");
                    entranceIndexes.add(parameter);
                    countEntrance -= 1;
                    setPlayerMessage(playerId,"Select an index for a student on entrance:");
                } catch (InvalidIndexException e) {
                    setPlayerMessage(playerId, e.getMessage());
                }
            }
            if(countEntrance == 0) {
                setRoundState(6);
                setPlayerMessage(playerId,"Select index for a table to switch:");
            }
        } else if (getRoundState() == 6) {
            if (countTable > 0) {
                try{
                    if (parameter < 0 || parameter > 5) throw new InvalidIndexException("The table doesn't exists\n Chose another one: ");
                    if (getGame().getGameTable().getSchoolBoards()[playerId].getNumberOfStudentsOnTable(parameter) == 10) throw new InvalidIndexException("The table is full, choose another one!");
                    if (getGame().getGameTable().getSchoolBoards()[playerId].getNumberOfStudentsOnTable(parameter) == 0) throw new InvalidIndexException("The table is empty, choose another one");
                    tableIndexes.add(parameter);
                    countTable -= 1;
                    setPlayerMessage(playerId,"Select index for a table to switch:");
                } catch (InvalidIndexException e) {
                    setPlayerMessage(playerId, e.getMessage());
                }
            }
            if(countTable == 0){
                try {
                    List<Student> newStudentsTable = new ArrayList<>();
                    List<Student> newStudentsOnEntrance = new ArrayList<>();
                    for (Integer i : entranceIndexes) newStudentsTable.add(getGame().getGameTable().getSchoolBoards()[playerId].removeStudentFromEntrance(i));
                    for (Integer i : tableIndexes) newStudentsOnEntrance.add(getGame().getGameTable().getSchoolBoards()[playerId].removeStudentFromTable(i));
                    for (Student s : newStudentsTable) {
                        getGame().getGameTable().getSchoolBoards()[playerId].addStudentOnTable(s);
                        getGame().getGameTable().moveProfessorToTheRightPosition(s.getColor());
                    }
                    getRound().getGame().getGameTable().getSchoolBoards()[playerId].addStudentsOnEntrance(newStudentsOnEntrance);
                    deactivateEffect(true);
                } catch (InvalidIndexException e) {
                    setPlayerMessage(playerId, e.getMessage());
                } catch (EmptyTableException e) {
                    setPlayerMessage(playerId, "The table is empty, choose another one");
                } catch (FullTableException e) {
                    setPlayerMessage(playerId, "The table is full, choose another one");
                }
            }
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessage("How many Students do you want to change");
        if (round.getGame().getGameTable().getSchoolBoards()[playerID].getTotalNumberOfStudentsOnTables() == 0) throw new EffectCannotBeActivatedException("The Minstrel cannot be activated: you don't have students on tables!");
        super.activateEffect(playerID, round);
        setRoundState(4);
        return this;
    }
}
