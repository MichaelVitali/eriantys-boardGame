package it.polimi.ingsw.model;
import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.NoMoreTowersException;
import it.polimi.ingsw.model.exception.ThreeOrLessIslandException;


public class Herald extends Character{

    public Herald(int id, int cost) {
        super(id, cost);
    }

    /**
     * Calculates influence in a selected Island without change mother nature position
     * @param playerId      Index of player that want to play the character
     * @param parameter     Index of island where the player want to calculate the influence
     */
    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
            int islandIndex = parameter;
            int oldPosition = getRound().getGame().getGameTable().getMotherNaturePosition();
            try {
                if (getRound().getGame().isAValidPositionForMotherNature(islandIndex)) {
                    getRound().getGame().getGameTable().changeMotherNaturePosition(islandIndex);
                    int[] influences = getRound().getGame().getGameTable().calculateInfluenceValuesGivenByStudents();
                    int[] influencesFromTowers = getRound().getGame().getGameTable().calculateInfluenceValuesGivenByTowers();
                    for (int i = 0; i < influences.length; i++) {
                        influences[i] += influencesFromTowers[i];
                    }
                    try {
                        getRound().getGame().getGameTable().putTowerOrChangeColorIfNecessary(influences);
                    } catch (NoMoreTowersException e) {
                        getRound().getGame().setVictory();
                        getRound().getGame().setWinner(e.getEmptySchoolboardColor());
                    } catch (ThreeOrLessIslandException e) {
                        checkEndgameAndSetTheWinner();
                    }
                    if(getRound().getGame().isGameEnded()) {
                        getRound().setRoundState(100);
                        roundState = 100;
                        getRound().getGame().endTheMatch();
                    }
                }
                getRound().getGame().getGameTable().changeMotherNaturePosition(oldPosition);
                deactivateEffect();
            } catch (InvalidIndexException e) {
                System.out.println("Invalid island index");
            }
        }
    }

    @Override
    public Round activateEffect (int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessage("Select Island");
        super.activateEffect(playerID, round);
        setRoundState(4);
        return this;
    }
}
