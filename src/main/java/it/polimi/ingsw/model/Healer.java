package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.InvalidIndexException;

public class Healer extends Character {
    public int prohibition;

    public Healer(int id, int cost) {
        super(id, cost);
        this.prohibition = 4;
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {

            //getGame().getGameTable().

        }
    }
    public Round activateEffect(int playerID, Round round) throws EffectCannotBeActivatedException {
        if (prohibition <= 0) throw new EffectCannotBeActivatedException("The healer cannot be activated: there are no prohibition cards");
        round.getGame().getPlayer(playerID).setPlayerMessage("Select Island");
        setRoundState(4);
        return super.activateEffect(playerID, round);

    }
}
