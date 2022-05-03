package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EmptyTableException;
import it.polimi.ingsw.model.exception.InvalidIndexException;

import java.util.Arrays;

public class Character12 extends Character{

    private PawnColor pawnColor;

    public Character12(int id, int cost){
        super(id, cost);
        pawnColor = null;
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if(getRoundState() == 5){
            try{
                if(parameter < 0 || parameter > 4)
                    throw new InvalidIndexException("Error effect 12: invalid pawncolor");

                /*switch (parameter){
                    case 0:
                        pawnColor = PawnColor.YELLOW;
                        break;
                    case 1:
                        pawnColor = PawnColor.BLUE;
                        break;
                    case 2:
                        pawnColor = PawnColor.GREEN;
                        break;
                    case 3:
                        pawnColor = PawnColor.RED;
                        break;
                    case 4:
                        pawnColor = PawnColor.PINK;
                        break;
                }*/

                pawnColor = PawnColor.associateIndexToPawnColor(parameter);

                for(int i=0; i<getRound().getGame().getNumberOfPlayers(); i++)
                    for (int j=0; j<3; j++)
                        if(getRound().getGame().getGameTable().getSchoolBoards()[i].getNumberOfStudentsOnTable(pawnColor) > 0)
                            getRound().getGame().getGameTable().getBag().addStudents(Arrays.asList(getRound().getGame().getGameTable().getSchoolBoards()[i].removeStudentFromTable(pawnColor)));


            }catch (InvalidIndexException e){
                ////////////////////////////////
            }catch ( EmptyTableException e){
                ////////////////////////////////
            }

            deactivateEffect();
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) {
        round.getGame().getPlayer(playerID).setErrorMessage("Select pawn color");
        setRoundState(5);
        return super.activateEffect(playerID, round);
    }

    @Override
    public void setRoundState(int state){
        if (state>=0 && state<6)
            this.roundState=state;
        else roundState = -1;
    }
}
