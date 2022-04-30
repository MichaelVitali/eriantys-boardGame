package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.InvalidMethodException;
import it.polimi.ingsw.model.exception.PlayerNotOnTurnException;
import it.polimi.ingsw.model.exception.TooFarIslandException;

public class TwoMoreInfluencePointsCharacter extends Character {

    private int teamWithTwoMorePoints;
    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public TwoMoreInfluencePointsCharacter(int id, int cost) {
        super(id, cost);
    }

    @Override
    /**
     * Activate the effect of the character decorating the current round
     * @param playerID player ID of the player which wants to activate the effect
     * @param oldRound round to decorate
     * @return the decorated round
     */
    public Round activateEffect(int playerID, Round oldRound) {
        teamWithTwoMorePoints = super.getGame().getNumberOfPlayers() == 3 ? playerID : playerID % 2;
        return super.activateEffect(playerID, oldRound);
    }

    @Override
    public void changeMotherNaturePosition (int playerId, int islandIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(2);
            try {
                int i = 0;
                while (i < getPlayedAssistants().length) {
                    if (getPlayedAssistants()[i].getPlayerIndex() == playerId) break;
                    i++;
                }
                if(!isANewAllowedPositionForMotherNature(getPlayedAssistants()[i].getAssistant(), islandIndex)) throw new TooFarIslandException();
                getGame().getGameTable().changeMotherNaturePosition(islandIndex);
                int[] influenceValues = getGame().getGameTable().calculateInfluenceValuesGivenByStudents();
                for (i = 0; i < influenceValues.length; i++) {
                    influenceValues[i] += getGame().getGameTable().calculateInfluenceValuesGivenByTowers()[i];
                    if (i == teamWithTwoMorePoints) influenceValues[i] += 2;
                }
                getGame().getGameTable().putTowerOrChangeColorIfNecessary(influenceValues);
                calculateNextPlayer();
            } catch (TooFarIslandException e) {
                setErrorMessage(playerId, "You cannot put mother nature in the chosen island");
            } catch (InvalidIndexException e) {
                // Stato di errore sarà da togliere dal codice
            }
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setErrorMessage(playerId, "You cannot move mother nature now");
        }
    }
}
