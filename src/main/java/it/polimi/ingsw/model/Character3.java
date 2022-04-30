package it.polimi.ingsw.model;
import it.polimi.ingsw.model.exception.InvalidIndexException;


public class Character3 extends Character{

    public Character3(int id, int cost) {
        super(id, cost);
    }

    /**
     * Calculates influence in a selected Island without change mother nature position
     * @param playerId      Index of player that want to play the character
     * @param parameter     Index of island where the player want to calculate the influence
     */
    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 5) {
            int islandIndex = parameter;
            int oldPosition = getRound().getGame().getGameTable().getMotherNaturePosition();
            try {
                if (getRound().getGame().isAValidPositionForMotherNature(islandIndex)) {
                    getRound().getGame().getGameTable().changeMotherNaturePosition(islandIndex);
                    getRound().getGame().getGameTable().putTowerOrChangeColorIfNecessary();
                }
                getRound().getGame().getGameTable().changeMotherNaturePosition(oldPosition);
                deactivateEffect();
            } catch (InvalidIndexException e) {
                System.out.println("Invalid island index");
            }
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) {
        round.getGame().getPlayer(playerID).setErrorMessage("Select Island");
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
