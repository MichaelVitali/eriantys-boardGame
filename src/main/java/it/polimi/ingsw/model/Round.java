package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import java.io.Serializable;
import java.util.*;

public class Round implements Serializable {

    private Game game;
    protected int roundState;
    private int[] movesCounter;                     // In indice playerId si trovano gli spostamenti di studenti fatti dal giocatore con tale id
    private int indexOfPlayerOnTurn;               // Indice in playerOrder del giocatore che sta giocando
    private int[] playerOrder;                      // Da 0 al numero di player identifica l'ordine di essi in quella fase di gioco
    private PlayedAssistant[] playedAssistants;
    private boolean[] alreadyPlayedAssistants;
    private List<Assistant> playedAssistantsPF; ///// cosa è
    private boolean alreadyPlayedCharacter;

    public Round() { }

    public Round(Game game) {
        indexOfPlayerOnTurn = 0;
        playerOrder = new int[game.getNumberOfPlayers()];
        playerOrder[0] = calculateFirstPlayer();
        for (int i = 1; i < game.getNumberOfPlayers(); i++)
            playerOrder[i] = (playerOrder[i - 1] + 1) % 4;
        roundState = 0;
        movesCounter = new int[game.getNumberOfPlayers()];
        for (int i = 0; i < game.getNumberOfPlayers(); i++)
            movesCounter[i] = 0;
        playedAssistants = new PlayedAssistant[game.getNumberOfPlayers()];
        this.game = game;
        alreadyPlayedCharacter = false;


        alreadyPlayedAssistants = new boolean[game.getNumberOfPlayers()];
        for (int i = 0; i < alreadyPlayedAssistants.length; i++)
            alreadyPlayedAssistants[i] = false;
        playedAssistantsPF = new ArrayList<>();


        setMessageToAPlayerAndWaitingMessageForOthers(playerOrder[0], "Select an assistant");
    }

    public Round(Game game, int[] playerOrder) {
        this(game);
        for(int i = 0; i < game.getNumberOfPlayers(); i++)
            this.playerOrder[i] = playerOrder[i];
        setMessageToAPlayerAndWaitingMessageForOthers(playerOrder[0], "Select an assistant");
    }

    /**
     * Returns the instance of itself
     * @return itself
     */
    public Round getRound() { return this; }

    /**
     * Returns the game of which the round is part
     * @return the game of which the round is part
     */
    public Game getGame() {
        return game;
    }

    public class PlayedAssistant implements Serializable {
        private int playerIndex;
        private Assistant assistant;

        public PlayedAssistant(int playerIndex, Assistant assistant) {
            this.playerIndex = playerIndex;
            this.assistant = assistant;
        }

        public int getPlayerIndex() {
            return playerIndex;
        }

        public Assistant getAssistant() {
            return assistant;
        }
    }

    public PlayedAssistant[] getPlayedAssistants(){
        return  playedAssistants;
    }

    public int[] getPlayerOrder(){
        return this.playerOrder.clone();
    }

    public void checkPlayerOnTurn(int playerId) throws PlayerNotOnTurnException {
        if(playerOrder[indexOfPlayerOnTurn] != playerId) {
            setPlayerMessage(playerId, "You are not the current player");
            throw new PlayerNotOnTurnException();
        }
    }

    public void setRoundState(int state){
        if (state>=0 && state<4)
            this.roundState=state;
        else roundState = -1;
    }

    public int getRoundState(){
        return roundState;
    }

    public int[] getMovesCounter(){
        return movesCounter;
    }

    public void setMovesCounter(int playerId, int moves){
        movesCounter[playerId] = moves;
    }

    public int getIndexOfPlayerOnTurn(){
        return indexOfPlayerOnTurn;
    }

    public void setIndexOfPlayerOnTurn(int index){
        if(index>=0 && index<game.getNumberOfPlayers())
            indexOfPlayerOnTurn=index;
    }

    public void setAlreadyPlayedCharacter(boolean alreadyPlayedCharacter) {
        this.alreadyPlayedCharacter = alreadyPlayedCharacter;
    }

    public int calculateFirstPlayer(){
        //Random generator = new Random();
        int firstPlayer = 0;//generator.nextInt(game.getNumberOfPlayers());
        return firstPlayer;
    }

    public void checkStatusAndMethod(int methodId) throws InvalidMethodException {
        if (methodId != roundState) throw new InvalidMethodException();
    }

    public void checkNumberOfMoves(int playerId) throws TooManyMovesException {
        if(movesCounter[playerId] > 3) throw new TooManyMovesException();
    }

    public void setPlayerMessage(int playerId, String message) {
        game.getPlayer(playerId).setPlayerMessage(message);
    }

    private void setMessageToAPlayerAndWaitingMessageForOthers(int playerId, String message) {
        setPlayerMessage(playerId, message);
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            if (i != playerId) setPlayerMessage(i, "The player on turn is " + game.getPlayer(playerId).getNickname());
        }
    }

    private String getStateMessage() {
        String message = null;
        if (roundState == 0) message = "Select an assistant";
        else if (roundState == 1) message = "Make your move:\n1 : Move a student from entrance to table\n2 : Move a student from entrance to an island";
        else if (roundState == 2) message = "Select an island where mother nature has to move";
        else if (roundState == 3) message = "Select a cloud";
        return message;
    }

    public boolean isPianificationPhaseEnded() {
        if (roundState == 0) {
            if (indexOfPlayerOnTurn == game.getNumberOfPlayers() - 1) return true;
        }
        return false;
    }

    public boolean isActionPhaseEnded() {
        if (roundState == 3 && (indexOfPlayerOnTurn == game.getNumberOfPlayers() - 1)) return true;
        return false;
    }

    public boolean isTimeToChooseTheNextStudent() {
        if(roundState == 1 && movesCounter[playerOrder[indexOfPlayerOnTurn]] < 3 )
            return true;
        return false;
    }

    public boolean isTimeToMoveMotherNature() {
        if (roundState == 1 && 3 <= movesCounter[playerOrder[indexOfPlayerOnTurn]])
            return true;

        return false;
    }

    public boolean isTimeToChooseACloud() {
        if (roundState == 2) return true;
        return false;
    }

    public boolean cloudHasBeenChosen() {
        if (roundState == 3) return true;
        return false;
    }

    public boolean isTheGameEnded() {
        if(roundState == 100) return true;
        return false;
    }

    public void setPianificationPhaseOrder() {
        int minimumAssistantValue = 11;
        int nextTurnFirstPlayer = 0;
        for(int i = 0; i < playedAssistants.length; i++) {
            if(playedAssistants[i].getAssistant().getCardValue() < minimumAssistantValue) {
                minimumAssistantValue = playedAssistants[i].getAssistant().getCardValue();
                nextTurnFirstPlayer = playedAssistants[i].playerIndex;
            }
        }
        playerOrder[0] = nextTurnFirstPlayer;

        for(int i = 1; i < playedAssistants.length; i++)
            playerOrder[i] = (playerOrder[i - 1] + 1) % playedAssistants.length;
    }

    public void setActionPhaseOrder() {
        PlayedAssistant[] playedAssistantsOrdered = new PlayedAssistant[playedAssistants.length];
        for (int i = 0; i < playedAssistants.length; i++) {
            int j = 0;
            while (j < i && playedAssistants[i].getAssistant().getCardValue() >= playedAssistantsOrdered[j].getAssistant().getCardValue())
                j++;
            int k = i;
            while(k > j) {
                playedAssistantsOrdered[k] = playedAssistantsOrdered[k - 1];
                k--;
            }
            playedAssistantsOrdered[j] = playedAssistants[i];
        }
        for (int i = 0; i < game.getNumberOfPlayers(); i++)
            playerOrder[i] = playedAssistantsOrdered[i].getPlayerIndex();
    }

    public void switchToPianificationPhase() {
        System.out.println("Abbiamo eseguito la switch to action phase");
        setPianificationPhaseOrder();
        game.startRound(playerOrder);
    }

    public void switchToActionPhase() {
        setActionPhaseOrder();
        roundState = 1;
        indexOfPlayerOnTurn = 0;
    }

    public void calculateNextPlayer() {
        boolean roundEnded = false;
        if (isTheGameEnded()) {
        } else if (isPianificationPhaseEnded()) {
            switchToActionPhase();
        } else if (isTimeToChooseTheNextStudent()) {

        } else if (isActionPhaseEnded()) {
            switchToPianificationPhase();
            roundEnded = true;
            if(game.getPlayer(playerOrder[indexOfPlayerOnTurn]).getAssistants().size() == 0) {
                roundState = 100;
                game.endTheMatch();
            }
        } else if (isTimeToMoveMotherNature()) {
            roundState = 2;
        } else if (isTimeToChooseACloud()) {
            roundState = 3;
        } else if (cloudHasBeenChosen()) {
            roundState = 1;
            indexOfPlayerOnTurn++;
            alreadyPlayedCharacter = false;
        } else {
            if (indexOfPlayerOnTurn + 1 < game.getNumberOfPlayers()){
                indexOfPlayerOnTurn++;
            }
        }
        if (!roundEnded) {
            setMessageToAPlayerAndWaitingMessageForOthers(playerOrder[indexOfPlayerOnTurn], getStateMessage());
            game.sendGame();
        }
    }

    public boolean assistantNoChoice(List<Assistant> outer, List<Assistant> inner) {
        if (outer.size() < inner.size())
            return false;
        return outer.containsAll(inner);
    }

    public void removeAssistant(int playerId, int assistantPosition) throws InvalidIndexException {
        Assistant assistantToPlay = game.getPlayer(playerId).getAssistant(assistantPosition);

        for (int i = 0; i < playedAssistants.length; i++) {
            if (alreadyPlayedAssistants[i] == true && i != playerId) {
                if (assistantToPlay.equals(playedAssistants[i].getAssistant())) {
                    if (!assistantNoChoice(playedAssistantsPF, game.getPlayer(playerId).getAssistants())) {
                        game.getPlayer(playerId).setPlayerMessage("Someone has already choose that assistant. Select a different one");
                        game.sendGame();
                        return;
                    }
                }
            }
        }
        game.getPlayer(playerId).setPlayerMessage("Assistant played");
        assistantToPlay = game.getPlayer(playerId).removeAssistant(assistantPosition);
        playedAssistants[playerId] = new PlayedAssistant(playerId, assistantToPlay);
        alreadyPlayedAssistants[playerId] = true;
        playedAssistantsPF.add(assistantToPlay);
    }

    public void playAssistant(int playerId, int assistantPosition) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(0);
            removeAssistant(playerId, assistantPosition);
            if (!game.getPlayer(playerId).getPlayerMessage().equals("Someone has already choose that assistant. Select a different one")) calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You cannot play any assistant now");
        } catch (IndexOutOfBoundsException e) {
            setPlayerMessage(playerId,"You can't choose that assistant");
        } catch (InvalidIndexException e) {
            setPlayerMessage(playerId, e.getMessage());
        }
    }

    public void addStudentOnIsland(int playerId, int studentIndex, int islandIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(1);
            checkNumberOfMoves(playerId);
            game.getPlayer(playerId).moveStudentOnIsland(studentIndex, islandIndex);
            movesCounter[playerId]++;
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You cannot move students now");
        } catch (TooManyMovesException e) {
            setPlayerMessage(playerId, "You can move no more students");
        } catch (InvalidIndexException e) {
            setPlayerMessage(playerId, e.getMessage());
        }
    }

    public void addStudentOnTable(int playerId, int studentIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(1);
            checkNumberOfMoves(playerId);
            System.out.println("Player: " + playerId + " number of  moves: " + movesCounter[playerId]);
            game.getPlayer(playerId).moveStudentOnTable(studentIndex);
            if (game instanceof ExpertGame){
                PawnColor studentColor = game.getGameTable().getSchoolBoards()[playerId].getStudentsFromEntrance()[studentIndex].getColor();
                if (game.getGameTable().getSchoolBoards()[playerId].getNumberOfStudentsOnTable(studentColor) % 3 == 0) ((ExpertGame)game).addCoinToAPlayer(playerId);
            }
            movesCounter[playerId]++;
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You cannot move students now");
        } catch (TooManyMovesException e) {
            setPlayerMessage(playerId, "You can move no more students");
        } catch (FullTableException e) {
            setPlayerMessage(playerId, "You can't move that student, his table has no more free seats");
        } catch (NotEnoughCoins e) {
            setPlayerMessage(playerId, "You can't take a coin from a table");
        }
    }

    public boolean isANewAllowedPositionForMotherNature(Assistant assistant, int islandIndex) {
        int motherNaturePosition = game.getGameTable().getMotherNaturePosition();
        int numberOfIsland = game.getGameTable().getNumberOfIslands();
        if(islandIndex < motherNaturePosition)
            if((numberOfIsland - motherNaturePosition + islandIndex) <= assistant.getMotherNatureMoves())
                return true;
            else
                return false;
        else {
            if ((islandIndex - motherNaturePosition) <= assistant.getMotherNatureMoves())
                return true;
            else
                return false;
        }
    }

    /**
     * Checks if someone has won or if the match ends with a draw. The method sets the parameters victory, draw, winner, inside game, according to the state of the match
     */
    public void checkEndgameAndSetTheWinner() {
        List<TowerColor> possibleWinner = game.getGameTable().teamWithLessTowersOnSchoolboards();
        if (possibleWinner.size() > 1) {
            possibleWinner = game.getGameTable().teamWithMoreProfessors(possibleWinner);
            if (possibleWinner.size() > 1) {
                game.setDraw();
            } else {
                game.setVictory();
                game.setWinner(possibleWinner.get(0));
            }
        } else {
            game.setVictory();
            game.setWinner(possibleWinner.get(0));
        }
    }

    /**
     * Changes mother nature position, calculate the influences of the players on the island and puts or changes the tower on the island
     * @param playerId player ID of the player which want to make the move
     * @param islandIndex index of the island on which the player wants to move mothter nature
     */
    public void changeMotherNaturePosition (int playerId, int islandIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(2);
            try {
                int i = 0;
                while (i < playedAssistants.length) {
                    if (playedAssistants[i].playerIndex == playerId) break;
                    i++;
                }
                if (!isANewAllowedPositionForMotherNature(playedAssistants[i].getAssistant(), islandIndex)) throw new TooFarIslandException();
                game.getGameTable().changeMotherNaturePosition(islandIndex);
                int[] influenceValues = game.getGameTable().calculateInfluenceValuesGivenByStudents();
                for (i = 0; i < influenceValues.length; i++)
                    influenceValues[i] += game.getGameTable().calculateInfluenceValuesGivenByTowers()[i];
                try {
                    game.getGameTable().putTowerOrChangeColorIfNecessary(influenceValues);
                } catch (NoMoreTowersException e) {
                    game.setVictory();
                    game.setWinner(e.getEmptySchoolboardColor());
                } catch (ThreeOrLessIslandException e) {
                    checkEndgameAndSetTheWinner();
                }
                if(game.isGameEnded()) {
                    roundState = 100;
                    game.endTheMatch();
                }
                calculateNextPlayer();
            } catch (TooFarIslandException e) {
                setPlayerMessage(playerId, "You cannot put mother nature in the chosen island");
            } catch (InvalidIndexException e) {
                setPlayerMessage(playerId, "You cannot put mother nature in the chosen island, it does not exist");
            }
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You cannot move mother nature now");
        }
    }

    public void getStudentsFromCloud(int playerId, int cloudIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(3);
            game.getPlayer(playerId).takeStudentsFromCloud(cloudIndex);
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You cannot get students from cloud now");
        } catch (InvalidIndexException e) {
            setPlayerMessage(playerId, e.getMessage());
        } catch (EmptyCloudException e)  {
            setPlayerMessage(playerId, "The chosen cloud is empty. Chose another one!");
        }
    }

    public void activateEffect(int playerId, int indexCard) {
        try {
            checkPlayerOnTurn(playerId);
            if (roundState == 0) throw new InvalidMethodException();
            if (alreadyPlayedCharacter) throw new AlreadyPlayedCharcaterException();
            game.activateEffect(playerId, indexCard);
            alreadyPlayedCharacter = true;
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (AlreadyPlayedCharcaterException e) {
            setPlayerMessage(playerId, "You already played a character");
        } catch (InvalidMethodException e) {
            setPlayerMessage(playerId, "You can't play a character during the pianification phase");
        }
    }

    public void doYourJob(int playerId, int parameter) throws InvalidIndexException { }
}

