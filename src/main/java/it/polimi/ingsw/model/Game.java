package it.polimi.ingsw.model;

import it.polimi.ingsw.client.DisplayedBoard;
import it.polimi.ingsw.model.exception.EffectCannotBeActivatedException;
import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.observer.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game extends Observable<Game> implements Serializable {

    private final int numberOfPlayers;
    private GameMode gameMode;
    private GameTable gameTable;
    private List<Assistant>[] assistants;
    private Player[] players;
    private Round round;
    private boolean victory;
    private boolean draw;
    private TowerColor winner;
    public Game(int numberOfPlayers) { this.numberOfPlayers = numberOfPlayers; }

    public Game(int numberOfPlayers, List<String> nicknames) {
        this.numberOfPlayers = numberOfPlayers;
        gameMode = GameMode.NORMAL;
        gameTable = createGameTable(numberOfPlayers);

        assistants = new ArrayList[numberOfPlayers];
        List<Assistant> assistantsList = createAssistants();
        for (int i = 0; i < numberOfPlayers; i++) {
            assistants[i] = new ArrayList<>();
            assistants[i].addAll(assistantsList);
        }

        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(nicknames.get(i), i, assistantsList);
            players[i].addSchoolBoard(gameTable.getSchoolBoards()[i]);
            players[i].addGameTable(gameTable);
        }

        round = startRound();

        victory = false;
        draw = false;
        winner = null;
    }

    public GameMode getGameMode() { return gameMode; }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public Player getPlayer(int playerId) {
        try {
            if (playerId < 0 || playerId >= numberOfPlayers) throw new InvalidIndexException("This player doesn't exit");
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }
        return this.players[playerId];
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public boolean isVictory() {
        return victory;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setVictory(){
        victory=true;
    }

    public void setDraw(){
        draw=true;
    }

    public TowerColor getWinner() {
        return winner;
    }

    public void setWinner(TowerColor winner) {
        this.winner = winner;
    }

    public GameTable createGameTable(int numberOfPlayers) {
        SchoolBoard[] schoolBoards = new SchoolBoard[numberOfPlayers];

        int numberOfStudentsOnEntrance = 7;
        int numberOfTowers = 8;
        if (numberOfPlayers == 3) {
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 6;
        } else {
            numberOfStudentsOnEntrance = 7;
            numberOfTowers = 8;
        }

        switch (numberOfPlayers) {
            case 2:
                schoolBoards[0] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                break;
            case 3:
                schoolBoards[0] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.GREY, numberOfTowers);
                break;
            case 4:
                schoolBoards[0] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, 0);
                schoolBoards[3] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, 0);
                break;
        }
        Bag bag = createBag();
        return new GameTable(numberOfPlayers, schoolBoards, bag);
    }

    public Bag createBag() {
        return new Bag();
    }

    public List<Assistant> createAssistants(){
        List<Assistant> l = new ArrayList<>();

        String assistants = AssistantStorage.getAssistants();
        String[] assistantsParsed = assistants.split(";");
        for (String s : assistantsParsed) {
            String[] assistant = s.split(",");
            int cardValue = Integer.parseInt(assistant[0]);
            int motherNatureMoves = Integer.parseInt(assistant[1]);
            l.add(new Assistant(cardValue, motherNatureMoves));
        }
        return l;
    }

    ///////potrebbe essere in più
    public boolean isAValidPositionForMotherNature(int position) {
        if (0 <= position && position < gameTable.getIslands().size()) return true;
        return false;
    }

    public Round startRound() {
        round = new Round(this);
        try {
            gameTable.addStudentsOnClouds();
        } catch (EmptyBagException e) {
            round = new Round(this);
        }
        return round;
    }

    public Round startRound(int[] playerOrder) {
        round = new Round(this, playerOrder);
        try {
            gameTable.addStudentsOnClouds();
        } catch (EmptyBagException e) {
            round = new Round(this, playerOrder);
        }
        sendGame();
        return round;
    }

    /**
     * Chech if the game is ended or not
     * @return true, if the game has to end, due to different causes; false, if the game must continue
     */
    public boolean isGameEnded() {
        if (isVictory() || isDraw()) return true;
        return false;
    }

    /**
     * Does the operations related to the end of the game
     */
    public void endTheMatch() {

    }

    public void activateEffect(int playerIndex, int indexCard) throws EffectCannotBeActivatedException {
    }

    public void sendGame() {
        notify(this);
    }

    public List<Assistant> getPlayerAssistant(int playerIndex) {
        return assistants[playerIndex];
    }
}