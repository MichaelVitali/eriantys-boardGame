package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import java.util.*;

public class Round {

    private PianificationPhase pianificationPhase;
    private int currentPhase;
    private int roundState;
    private int[] movesCounter;                     // In indice playerId si trovano gli spostamenti di studenti fatti dal giocatore con tale id
    private int indexOfPlayerOnTurn;                // Indice in playerOrder del giocatore che sta giocando
    private int[] playerOrder;                      // Da 0 al numero di player identifica l'ordine di essi in quella fase di gioco
    private boolean roundFinished;
    private PlayedAssistant[] playedAssistants;
    private Game game;

    public Round(Game game) {
        pianificationPhase = new PianificationPhase(game);
        currentPhase = 0;
        indexOfPlayerOnTurn = 0;
        playerOrder = new int[game.getNumberOfPlayers()];
        playerOrder[0] = pianificationPhase.calculateFirstPlayer();
        for(int i = 1; i < game.getNumberOfPlayers(); i++)
            playerOrder[i] = (playerOrder[i - 1] + 1) % 4;
        roundFinished = false;
        roundState = 0;
        movesCounter = new int[game.getNumberOfPlayers()];
        for(int i = 0; i < game.getNumberOfPlayers(); i++)
            movesCounter[i] = 0;
        playedAssistants = new PlayedAssistant[game.getNumberOfPlayers()];
        this.game = game;
    }

    public Round(Game game, int[] playerOrder) {
        this(game);
        for(int i = 0; i < game.getNumberOfPlayers(); i++)
            this.playerOrder[i] = playerOrder[i];
    }

    public class PianificationPhase {

        private Game game;
        private boolean[] alreadyPlayedAssistants;
        private List<Assistant> playedAssistantsPF;

        public PianificationPhase(Game game) {
            this.game=game;
            alreadyPlayedAssistants=new boolean[game.getNumberOfPlayers()];
            for(int i=0; i<alreadyPlayedAssistants.length; i++)
                alreadyPlayedAssistants[i]=false;
            playedAssistantsPF=new ArrayList<>();
        }

        public int calculateFirstPlayer(){
            //return new Random().nextInt(game.getNumberOfPlayers());
            return 0;
        }

        public boolean assistantNoChoice(List<Assistant> outer, List<Assistant> inner) {
            if (outer.size()<inner.size())
                return false;

            return outer.containsAll(inner);
        }

        public void playAssistant(int playerId, int assistantPosition) throws InvalidIndexException {
            Assistant toPlay=game.getPlayer(playerId).getAssistant(assistantPosition);

            for (int i=0; i<playedAssistants.length ; i++){
                if (alreadyPlayedAssistants[i]==true && i!=playerId){
                    if(toPlay.equals(playedAssistants[i].getAssistant())){
                        if(assistantNoChoice(playedAssistantsPF, game.getPlayer(playerId).getAssistants())==false){
                            game.getPlayer(playerId).setErrorMessage("Assistant not playable");
                            return;
                        }
                    }
                }
            }
            playedAssistants[playerId] = new PlayedAssistant(playerId, toPlay);
            alreadyPlayedAssistants[playerId]=true;
            playedAssistantsPF.add(toPlay);
        }
    }


    public class PlayedAssistant {
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

    public PianificationPhase getPianificationPhase() {
        return pianificationPhase;
    }

    public PlayedAssistant[] getPlayedAssistants(){
        return  playedAssistants;
    }

    public int[] getPlayerOrder(){
        return this.playerOrder.clone();
    }

    public void checkPlayerOnTurn(int playerId) throws PlayerNotOnTurnException {
        if(playerOrder[indexOfPlayerOnTurn] != playerId) {
            setErrorMessage(playerId, "You are not the current player");
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

    public void checkStatusAndMethod(int methodId) throws InvalidMethodException {
        if (methodId != roundState) throw new InvalidMethodException();
    }

    public void setMovesCounter(int playerId, int moves){
            movesCounter[playerId] = moves;
    }

    public int[] getMovesCounter(){
        return movesCounter;
    }

    public void checkNumberOfMoves(int playerId) throws TooManyMovesException {
        if(movesCounter[playerId] > 3) throw new TooManyMovesException();
    }

    public void setErrorMessage(int playerId, String errorMessage) {
        game.getPlayer(playerId).setErrorMessage(errorMessage);
    }

    public void setIndexOfPlayerOnTurn(int index){
        if(index>=0 && index<game.getNumberOfPlayers())
            indexOfPlayerOnTurn=index;
    }

    public int getIndexOfPlayerOnTurn(){
        return indexOfPlayerOnTurn;
    }

    public boolean isPianificationPhaseEnded() {
        if (currentPhase == 0) {
            if (indexOfPlayerOnTurn == game.getNumberOfPlayers() - 1) return true;
        }
        return false;
    }

    public boolean isActionPhaseEnded() {
        if (currentPhase == 1 && roundState == 3)
            if (indexOfPlayerOnTurn == game.getNumberOfPlayers() - 1) return true;
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

    public boolean islandHasBeenChosen() {
        if (roundState == 3) return true;
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
        setPianificationPhaseOrder();
        game.startRound(playerOrder);
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(int currentPhase){
        if(currentPhase>=0 && currentPhase<=1)
            this.currentPhase=currentPhase;
    }

    public void switchToActionPhase() {
        setActionPhaseOrder();
        roundState = 1;
        indexOfPlayerOnTurn = 0;
        currentPhase = 1;
    }

    public void calculateNextPlayer() {
        if (isPianificationPhaseEnded()) {
            switchToActionPhase();
        } else if (isActionPhaseEnded()) {
            switchToPianificationPhase();
        } else if (isTimeToMoveMotherNature()) {
            roundState = 2;
        } else if (isTimeToChooseACloud()) {
            roundState = 3;
        } else if (islandHasBeenChosen()) {
            roundState = 1;
            indexOfPlayerOnTurn++;
        } else {
            if (indexOfPlayerOnTurn + 1 < game.getNumberOfPlayers()) // non dovrebbe mai accadere il contrario visti i controlli precedenti
                indexOfPlayerOnTurn++;
        }
    }

    public void playAssistant(int playerId, int assistantPosition) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(0);
            pianificationPhase.playAssistant(playerId, assistantPosition);
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setErrorMessage(playerId, "You cannot play any assistant now");
        } catch (IndexOutOfBoundsException e) {
            setErrorMessage(playerId,"You can't choose that assistant");
        } catch (InvalidIndexException e) {
            setErrorMessage(playerId, e.getMessage());
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
            setErrorMessage(playerId, "You cannot move students now");
        } catch (TooManyMovesException e) {
            setErrorMessage(playerId, "You can move no more students");
        } catch (InvalidIndexException e) {
            setErrorMessage(playerId, e.getMessage());
        }
    }

    public void addStudentOnTable(int playerId, int studentIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(1);
            checkNumberOfMoves(playerId);
            game.getPlayer(playerId).moveStudentOnTable(studentIndex);
            movesCounter[playerId]++;
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setErrorMessage(playerId, "You cannot move students now");
        } catch (TooManyMovesException e) {
            setErrorMessage(playerId, "You can move no more students");
        } catch (FullTableException e) {
            setErrorMessage(playerId, "You can't move that student, his table has no more free seats");
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
                if(!isANewAllowedPositionForMotherNature(playedAssistants[i].getAssistant(), islandIndex)) throw new TooFarIslandException();
                game.getGameTable().changeMotherNaturePosition(islandIndex);
                calculateNextPlayer();
            } catch (TooFarIslandException e) {
                setErrorMessage(playerId, "You cannot put mother nature in the chosen island");
            } catch (InvalidIndexException e) {
                // Stato di errore sasrà da togliere dal codicec
            }
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setErrorMessage(playerId, "You cannot move mother nature now");
        }
    }

    public void getStudentsFromCloud(int playerId, int cloudIndex) {
        try {
            checkPlayerOnTurn(playerId);
            checkStatusAndMethod(3);
            if(cloudIndex < 0 || game.getNumberOfPlayers() < cloudIndex) throw new InvalidIndexException("");
            game.getPlayer(playerId).takeStudentsFromCloud(cloudIndex);
            calculateNextPlayer();
        } catch (PlayerNotOnTurnException e) {
            // The player is not the current player so the round tate doesn't change
        } catch (InvalidMethodException e) {
            setErrorMessage(playerId, "You cannot get students from cloud now");
        } catch (InvalidIndexException e) {
            setErrorMessage(playerId, "The chosen cloud doesn't exist");
        } catch (EmptyCloudException e)  {
            setErrorMessage(playerId, "The chosen cloud is empty. Chose another one!");
        }
    }
}