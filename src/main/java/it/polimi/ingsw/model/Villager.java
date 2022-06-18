package it.polimi.ingsw.model;
import it.polimi.ingsw.model.exception.*;

public class Villager extends Character {
    private PawnColor studentColor;

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public Villager(int id, int cost) {
        super(id, cost, "Villager");
        studentColor = null;
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
            try {
                if (parameter < 0 || parameter > 4) throw new InvalidIndexException("The chosen student color doesn't exist");
                getGame().getRound().setRoundState(getOldState());
                setPlayerMessageCli(playerId, getStateMessageCli());
                setPlayerMessageGui(playerId, getStateMessageGui());
                studentColor = PawnColor.associateIndexToPawnColor(parameter);
            } catch (InvalidIndexException e) {
                setPlayerMessageCli(playerId, e.getMessage());
                setPlayerMessageGui(playerId, e.getMessage());
            }
        }
    }

    public Round activateEffect(int playerID, Round round) throws EffectCannotBeActivatedException {
        round.getGame().getPlayer(playerID).setPlayerMessageCli("Select a student color { 0:GREEN - 1:RED - 2:YELLOW - 3:PINK - 4:BLUE}");
        round.getGame().getPlayer(playerID).setPlayerMessageGui("Select a student color");
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
                deactivateEffect(false);
            } catch (TooFarIslandException e) {
                setPlayerMessageCli(playerId, "You cannot put mother nature in the chosen island\n" + getStateMessageCli());
                setPlayerMessageGui(playerId, "You cannot put mother nature in the chosen island\n" + getStateMessageGui());
                getGame().sendGame();
            } catch (InvalidIndexException e) {
                setPlayerMessageCli(playerId, "You cannot put mother nature in the chosen island, it does not exist");
                setPlayerMessageGui(playerId, "You cannot put mother nature in the chosen island, it does not exist");
                getGame().sendGame();
            }
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessageCli(playerId, "You cannot move mother nature now");
            setPlayerMessageGui(playerId, "You cannot move mother nature now");
            getGame().sendGame();
        }
    }

}