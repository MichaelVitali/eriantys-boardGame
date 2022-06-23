package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

public class InnKeeper extends Character{

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public InnKeeper(int id, int cost) {
        super(id, cost, "Innkeeper");
    }

    @Override
    public Round activateEffect(int playerID, Round round) throws EffectCannotBeActivatedException {
        super.activateEffect(playerID, round);
        changeProfessorEffect(playerID);
        return this;
    }

    public void changeProfessorEffect( int playerId ){
        for (int i = 0; i < getGame().getNumberOfPlayers(); i++){
            if (i != playerId){
                for (PawnColor professor : getGame().getGameTable().getSchoolBoards()[i].getProfessors()) {
                    if (getGame().getGameTable().getSchoolBoards()[i].getNumberOfStudentsOnTable(professor) == getGame().getGameTable().getSchoolBoards()[playerId].getNumberOfStudentsOnTable(professor)){
                        getGame().getGameTable().getSchoolBoards()[i].setProfessor(professor, false);
                        getGame().getGameTable().getSchoolBoards()[playerId].setProfessor(professor, true);
                    }
                }
            }
        }
    }
}