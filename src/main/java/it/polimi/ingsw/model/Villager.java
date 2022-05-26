package it.polimi.ingsw.model;
import it.polimi.ingsw.model.exception.*;

public class Villager extends Character {
    private PawnColor studentColor;

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     * @param name
     */
    public Villager(int id, int cost, String name) {
        super(id, cost, name);
    }

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     */


    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
            try {
                if (parameter < 0 || parameter > 4)
                    throw new InvalidIndexException("The chosen student color doesn't exist");

                studentColor = PawnColor.associateIndexToPawnColor(parameter);
            } catch (InvalidIndexException e) {
                setPlayerMessage(playerId, e.getMessage());
            }
            deactivateEffect(true);
        }
    }

    public Round activateEffect(int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessage("Select a student color");
        super.activateEffect(playerID, round);
        setRoundState(4);
        return this;
    }

    @Override
    public void changeMotherNaturePosition (int playerId, int islandIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(2);
            try {
                int i = 0;
                while (i < getRound().getPlayedAssistants().length) {
                    if (getRound().getPlayedAssistants()[i].getPlayerIndex() == playerId)
                        break;
                    i++;
                }
                if (!isANewAllowedPositionForMotherNature(getRound().getPlayedAssistants()[i].getAssistant(), islandIndex))
                    throw new TooFarIslandException();
                getGame().getGameTable().changeMotherNaturePosition(islandIndex);

                int[] influenceValues = getGame().getGameTable().calculateInfluenceValuesGivenByStudentsExceptOne( studentColor );

                for (i = 0; i < influenceValues.length; i++)
                    influenceValues[i] += getGame().getGameTable().calculateInfluenceValuesGivenByTowers()[i];
                try {
                    getGame().getGameTable().putTowerOrChangeColorIfNecessary(influenceValues);
                } catch (NoMoreTowersException e) {
                    getGame().setVictory();
                    getGame().setWinner(e.getEmptySchoolboardColor());
                } catch (ThreeOrLessIslandException e) {
                    checkEndgameAndSetTheWinner();
                }
                if(getGame().isGameEnded()) {
                    roundState = 100;
                    getGame().endTheMatch();
                }
                calculateNextPlayer();
            } catch (TooFarIslandException e) {
                String message = "You cannot put mother nature in the chosen island\n" + getStateMessage();
                setPlayerMessage(playerId, message);
                getGame().sendGame();
            } catch (InvalidIndexException e) {
                setPlayerMessage(playerId, "You cannot put mother nature in the chosen island, it does not exist");
            }
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You cannot move mother nature now");
        }
    }

}