package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

public class Healer extends Character {
    private int prohibition;

    public Healer(int id, int cost) {
        super(id, cost, "Healer");
        this.prohibition = 4;
    }

    @Override
    public void doYourJob(int playerId, int parameter) {
        if (getRoundState() == 4) {
            try {
                if (parameter < 0 || parameter > 11) throw new InvalidIndexException("The island doesn't exist");
                getGame().getGameTable().getIslandByIndex(parameter).setProhibition();
                prohibition--;
                setRoundState(getOldState());
                setPlayerMessage(playerId, getStateMessage());
            } catch (InvalidIndexException | IslandAlreadyForbiddenException e) {
                setPlayerMessage(playerId, e.getMessage());
                getGame().sendGame();
            }
        }
    }

    @Override
    public void setAlreadyPlayedCharacter(boolean alreadyPlayedCharacter) {
        getRound().setAlreadyPlayedCharacter(alreadyPlayedCharacter);
    }

    @Override
    public void activateEffect(int playerId, int indexCard) {
        try {
            checkPlayerOnTurn(playerId);
            if (getRoundState() <= 0 || getRoundState() >= 4) throw new InvalidMethodException();
            if (getAlreadyPLayedCharacter()) throw new AlreadyPlayedCharcaterException();
            setOldState(getRoundState());
            getGame().activateEffect(playerId, indexCard);
            setAlreadyPlayedCharacter(true);
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (AlreadyPlayedCharcaterException e) {
            setPlayerMessage(playerId, "You already played a character\n" + getRound().getStateMessage());
            getGame().sendGame();
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You can't play a character during the pianification phase");
            getGame().sendGame();
        } catch (EffectCannotBeActivatedException e) {
            getRound().setPlayerMessage(playerId, e.getMessage() + "\n" + getRound().getStateMessage() );
            getGame().sendGame();
        } catch (NotEnoughCoins e) {
            setPlayerMessage(playerId,"Not enough coins to play this character" + getRound().getStateMessage());
            getGame().sendGame();
        }
    }

    @Override
    public Round activateEffect(int playerID, Round round) throws EffectCannotBeActivatedException {
        if (prohibition <= 0) throw new EffectCannotBeActivatedException("The healer cannot be activated: there are no prohibition cards");
        round.getGame().getPlayer(playerID).setPlayerMessage("Select an island where you want to put the prohibition card");
        setRoundState(4);
        return this;
    }

    @Override
    public void switchToPianificationPhase() {
        System.out.println("Abbiamo eseguito la switch to pianification phase");
        setPianificationPhaseOrder();
        try {
            getGame().getGameTable().addStudentsOnClouds();
            setRound(new Round(getGame(), getPlayerOrder()));
        } catch (EmptyBagException e) {
            setRound(new LastRound(getGame(), getPlayerOrder(), true));
        }
        getGame().setRound(this);
    }

    @Override
    public void changeMotherNaturePosition (int playerId, int islandIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(2);
            try {
                int i = 0;
                while (i < getRound().getPlayedAssistants().length) {
                    if (getRound().getPlayedAssistants()[i].getPlayerIndex() == playerId) break;
                    i++;
                }
                if (!isANewAllowedPositionForMotherNature(getRound().getPlayedAssistants()[i].getAssistant(), islandIndex)) throw new TooFarIslandException();
                getGame().getGameTable().changeMotherNaturePosition(islandIndex);
                if(!getGame().getGameTable().getIslandByIndex(getGame().getGameTable().getMotherNaturePosition()).isForbidden()) {
                    int[] influenceValues = getGame().getGameTable().calculateInfluenceValuesGivenByStudents();
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
                    if (getGame().isGameEnded()) {
                        roundState = 100;
                        getGame().endTheMatch();
                    }
                } else {
                    getGame().getGameTable().getIslandByIndex(getGame().getGameTable().getMotherNaturePosition()).resetProhibition();
                }
                calculateNextPlayer();
            } catch (TooFarIslandException e) {
                String message = "You cannot put mother nature in the chosen island\n" + getRound().getStateMessage();
                setPlayerMessage(playerId, message);
                getGame().sendGame();
            } catch (InvalidIndexException e) {
                setPlayerMessage(playerId, "You cannot put mother nature in the chosen island, it does not exist");
                getGame().sendGame();
            }
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You cannot move mother nature now");
            getGame().sendGame();
        }
    }

    public int getNumberOfProibitionCard() {
        return prohibition;
    }
}
