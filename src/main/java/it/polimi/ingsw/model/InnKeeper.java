package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

public class InnKeeper extends Character{

    //private ArrayList<ArrayList<PawnColor>> playersProfessors;

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public InnKeeper(int id, int cost) {
        super(id, cost, "Innkeeper");
        //playersProfessors = new ArrayList<ArrayList<PawnColor>>(4);
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
                        //playersProfessors.get(i).add(professor);
                    }
                }
            }
        }
    }

    /*public void reverseProfessorEffect( int playerId ){
        for (int i = 0; i < getGame().getNumberOfPlayers(); i++) {
            if (i != playerId) {
                for (PawnColor professor : playersProfessors.get(i)) {
                    if (getGame().getGameTable().getSchoolBoards()[i].getNumberOfStudentsOnTable(professor.getIndex()) == getGame().getGameTable().getSchoolBoards()[playerId].getNumberOfStudentsOnTable(professor.getIndex())) {
                        getGame().getGameTable().getSchoolBoards()[i].setProfessor(professor, true);
                        getGame().getGameTable().getSchoolBoards()[playerId].setProfessor(professor, false);
                    }
                }
            }
        }
        deactivateEffect(false);
    }*/
}