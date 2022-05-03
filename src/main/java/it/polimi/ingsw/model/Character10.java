package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EmptyTableException;
import it.polimi.ingsw.model.exception.FullTableException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.OutOfBoundException;

import java.util.ArrayList;
import java.util.List;

public class Character10 extends Character{

    private int entranceIndex;
    private PawnColor pawnColor;
    private int canSwitch;
    private int wantToGoOn;

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public Character10(int id, int cost) {
        super(id, cost);
        setRoundState(5);
        canSwitch = 0;
        wantToGoOn = 1;
        pawnColor = null;
    }

    @Override
    public void doYourJob(int playerId, int parameter) {

        Student tmpStudentEntrance = null;
        Student tmpStudentTable = null;
        if (canSwitch < 2 && wantToGoOn == 1) {
            if (getRoundState() == 5) {
                getRound().getGame().getPlayer(playerId).setPlayerMessage("Select student from Table");
                try {
                    if (parameter < 0 || parameter >= getGame().getGameTable().getSchoolBoards()[playerId].getNumberOfStudentsOnEntrance())
                        throw new InvalidIndexException("Error effect 10: invalid index on entrance");

                    entranceIndex = parameter;
                }catch (InvalidIndexException e) {
                    getGame().getPlayer(playerId).setErrorMessage(e.getMessage());
                }
                setRoundState(6);
            }else if(getRoundState() == 6) {

                try {
                    if (parameter < 0 || parameter > 4)
                        throw new InvalidIndexException("Error effect 10: invalid table choice");

                    pawnColor = PawnColor.associateIndexToPawnColor(parameter);

                    if (getGame().getGameTable().getSchoolBoards()[playerId].getNumberOfStudentsOnTable(pawnColor) == 0)
                        throw new EmptyTableException();

                    tmpStudentEntrance=getGame().getGameTable().getSchoolBoards()[playerId].removeStudentFromEntrance(entranceIndex);
                    tmpStudentTable=getGame().getGameTable().getSchoolBoards()[playerId].removeStudentFromTable(pawnColor);
                    List<Student> tst = new ArrayList<Student>();
                    tst.add(tmpStudentTable);

                    getGame().getGameTable().getSchoolBoards()[playerId].addStudentOnTable(tmpStudentEntrance);
                    getGame().getGameTable().getSchoolBoards()[playerId].addStudentsOnEntrance(tst);

                }catch (EmptyTableException e){
                    getGame().getPlayer(playerId).setErrorMessage("Error effect 10: the table you've chosen is empty");
                }catch (InvalidIndexException e){
                    getGame().getPlayer(playerId).setErrorMessage("Error effect 10: the index of the table is not correct");
                }catch (FullTableException e){
                    /////////////
                }

                if (canSwitch < 1)
                    setRoundState(7);

            }else if (getRoundState() == 7) {
                try {
                    if (parameter < 0 || parameter > 1) throw new OutOfBoundException("The parameter must be 0 or 1");
                    wantToGoOn = parameter;
                    canSwitch++;
                }catch (OutOfBoundException e){}
            }
        }
        deactivateEffect();
    }

    @Override
    public Round activateEffect (int playerID, Round round) {
        round.getGame().getPlayer(playerID).setPlayerMessage("Select Student on entrance");
        setRoundState(5);
        return super.activateEffect(playerID, round);
    }

    @Override
    public void setRoundState(int state){
        if (state>=0 && state<8)
            this.roundState=state;
        else roundState = -1;
    }
}
