package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.EmptyTableException;
import it.polimi.ingsw.model.exception.InvalidIndexException;

import java.util.Arrays;

public class Thief extends Character {

    private PawnColor pawnColor;

    public Thief(int id, int cost){
        super(id, cost);
        pawnColor = null;
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
            try {
                if (parameter < 0 || parameter > 4)
                    throw new InvalidIndexException("Error effect 12: invalid pawncolor");

                pawnColor = PawnColor.associateIndexToPawnColor(parameter);

                for (int i = 0; i < getRound().getGame().getNumberOfPlayers(); i++)
                    for (int j = 0; j < 3; j++)
                        if (getRound().getGame().getGameTable().getSchoolBoards()[i].getNumberOfStudentsOnTable(pawnColor) > 0)
                            getRound().getGame().getGameTable().getBag().addStudents(Arrays.asList(getRound().getGame().getGameTable().getSchoolBoards()[i].removeStudentFromTable(pawnColor)));


            }catch (InvalidIndexException | EmptyTableException e) {
                getRound().getGame().getPlayer(playerId).setPlayerMessage(e.getMessage());
            }

            deactivateEffect(true);
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessage("Select pawn color");
        Round r = super.activateEffect(playerID, round);
        setRoundState(4);
        return r;
    }
}