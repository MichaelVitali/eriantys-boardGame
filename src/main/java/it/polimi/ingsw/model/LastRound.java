package it.polimi.ingsw.model;

public class LastRound extends Round {

    boolean studentsEnded;

    /**
     * Creates the last round of a match filling every attribute with the default value
     */
    public LastRound() { }

    /**
     * Creates the last round of a match, giving to the round the instance of the game. The player order of the round is the one given as parameter
     * @param game instance of the game of which the round is part
     * @param playerOrder playing order of the new round
     */
    public LastRound(Game game, int[] playerOrder, boolean studentsEnded) {
        super(game, playerOrder);
        this.studentsEnded = studentsEnded;
    }

    @Override
    public boolean isActionPhaseEnded() {
        if(getRoundState() == 2 && (getIndexOfPlayerOnTurn() == getGame().getNumberOfPlayers() - 1)) return true;
        return false;
    }
    @Override
    public void calculateNextPlayer() {
        if (isActionPhaseEnded()) {
            setRoundState(100);
            //getGame().endTheMatch();
        } else if (isTimeToChooseACloud() && studentsEnded) {
            roundState = 1;
            setIndexOfPlayerOnTurn(getIndexOfPlayerOnTurn() + 1);
            setAlreadyPlayedCharacter(false);
        } else {
            super.calculateNextPlayer();
        }
    }
}
