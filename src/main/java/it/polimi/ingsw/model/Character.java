package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import java.io.Serializable;
import java.util.List;


public class Character extends Round implements Serializable {
    private final int ID;
    private int cost;
    private boolean firstUse;
    private Round round;
    private int oldState;
    private String name;

    /**
     * Creates a character card with the given two values
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public Character(int id, int cost, String name) {
        super();
        this.ID = id;
        this.cost = cost;
        firstUse = false;
        round = null;
        this.name = name;
    }

    /**
     * Returns the identifier of the character card
     * @return the identifier of the character card
     */
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the cost of activation of the card
     * @return the cost of activavtion of the card
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns true if the character card has been used one or more times and returns false if its effect hasn't been activated yet
     * @return the value of usage of the character card - false if it hasn't been used yet
     */
    public boolean getFirstUse() {
        return firstUse;
    }

    /**
     * Sets the card as "already been used once" so it costs one more coin to be activated that the initial cost
     */
    public void setFirstUse() {
        firstUse = true;
        cost++;
    }

    @Override
    public int getPlayerOnTurn() { return round.getPlayerOnTurn(); }

    @Override
    public Round getRound() {
        return round;
    }

    @Override
    public Game getGame() {
        return round.getGame();
    }

    @Override
    public Round.PlayedAssistant[] getPlayedAssistants(){
        return round.getPlayedAssistants();
    }

    @Override
    public int[] getPlayerOrder() { return round.getPlayerOrder(); }

    @Override
    public void checkPlayerOnTurn(int playerId) throws PlayerNotOnTurnException { round.checkPlayerOnTurn(playerId); }

    @Override
    public void setRoundState(int state) { round.setRoundState(state); }

    @Override
    public int getRoundState(){
        return round.getRoundState();
    }

    @Override
    public int[] getMovesCounter(){
        return round.getMovesCounter();
    }

    @Override
    public void setMovesCounter(int playerId, int moves){
        round.setMovesCounter(playerId, moves);
    }

    @Override
    public void setIndexOfPlayerOnTurn(int index)  { round.setIndexOfPlayerOnTurn(index); }

    @Override
    public int getIndexOfPlayerOnTurn(){
        return round.getIndexOfPlayerOnTurn();
    }

    @Override
    public void setAlreadyPlayedCharacter(boolean alreadyPlayedCharacter) { round.setAlreadyPlayedCharacter(alreadyPlayedCharacter); }

    @Override
    public int calculateFirstPlayer(int numberOfPlayers) { return round.calculateFirstPlayer(numberOfPlayers); }

    @Override
    public void checkStatusAndMethod(int methodId) throws InvalidMethodException { round.checkStatusAndMethod(methodId); }

    @Override
    public void checkNumberOfMoves(int playerId) throws TooManyMovesException { round.checkNumberOfMoves(playerId); }

    @Override
    public void setPlayerMessage(int playerId, String message) { round.setPlayerMessage(playerId, message); }

    @Override
    public void setMessageToAPlayerAndWaitingMessageForOthers(int playerId, String message) { round.setMessageToAPlayerAndWaitingMessageForOthers(playerId, message); }

    @Override
    public String getStateMessage() { return round.getStateMessage(); }

    @Override
    public boolean isPianificationPhaseEnded() { return round.isPianificationPhaseEnded(); }

    @Override
    public boolean isActionPhaseEnded() { return round.isActionPhaseEnded(); }

    @Override
    public boolean isTimeToChooseTheNextStudent() { return round.isTimeToChooseTheNextStudent(); }

    @Override
    public boolean isTimeToMoveMotherNature() { return round.isTimeToMoveMotherNature(); }

    @Override
    public boolean isTimeToChooseACloud() { return round.isTimeToChooseACloud(); }

    @Override
    public boolean cloudHasBeenChosen() { return round.cloudHasBeenChosen(); }

    @Override
    public boolean isTheGameEnded() { return round.isTheGameEnded(); }

    @Override
    public void setPianificationPhaseOrder() { round.setPianificationPhaseOrder(); }

    @Override
    public void setActionPhaseOrder() { round.setActionPhaseOrder(); }

    @Override
    public void switchToPianificationPhase() { round.switchToPianificationPhase();}

    @Override
    public void switchToActionPhase() { round.switchToActionPhase(); }

    @Override
    public void calculateNextPlayer() {
        round.calculateNextPlayer();
    }

    @Override
    public boolean assistantNoChoice(List<Assistant> outer, List<Assistant> inner) { return round.assistantNoChoice(outer, inner); }

    @Override
    public void removeAssistant(int playerId, int assistantPosition) throws InvalidIndexException { round.removeAssistant(playerId, assistantPosition); }

    @Override
    public void playAssistant(int playerId, int assistantPosition) { round.playAssistant(playerId, assistantPosition); }

    @Override
    public void addStudentOnIsland(int playerId, int studentIndex, int islandIndex) { round.addStudentOnIsland(playerId, studentIndex, islandIndex); }

    @Override
    public void addStudentOnTable(int playerId, int studentIndex) { round. addStudentOnTable(playerId, studentIndex); }

    @Override
    public boolean isANewAllowedPositionForMotherNature(Assistant assistant, int islandIndex) { return round.isANewAllowedPositionForMotherNature(assistant, islandIndex); }

    @Override
    public void checkEndgameAndSetTheWinner() { round.checkEndgameAndSetTheWinner(); }

    @Override
    /**
     * Changes mother nature position, calculate the influences of the players on the island and puts or changes the tower on the island
     * @param playerId player ID of the player which want to make the move
     * @param islandIndex index of the island on which the player wants to move mothter nature
     */
    public void changeMotherNaturePosition (int playerId, int islandIndex) { round.changeMotherNaturePosition(playerId, islandIndex); }

    @Override
    public void getStudentsFromCloud(int playerId, int cloudIndex) { round.getStudentsFromCloud(playerId, cloudIndex); }

    /**
     * It stores the old state value
     * @param playerID
     * @param oldRound
     * @return
     * @throws EffectCannotBeActivatedException
     */
    public Round activateEffect(int playerID, Round oldRound) throws EffectCannotBeActivatedException {
        oldState = oldRound.getRoundState();
        round = oldRound;
        return this;
    }

    public void deactivateEffect(boolean resetState) {
        if(resetState) round.setRoundState(oldState);
        round.getGame().setRound(round);
        round.setPlayerMessage(getPlayerOnTurn(), getStateMessage());
    }

    @Override
    public void doYourJob(int playerId, int parameter) throws InvalidIndexException { }

    @Override
    public boolean getAlreadyPLayedCharacter() {
        return round.getAlreadyPLayedCharacter();
    }

    public int getOldState() {
        return oldState;
    }

    public void setOldState(int oldState) {
        this.oldState = oldState;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}