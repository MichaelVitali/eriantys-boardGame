package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;

public class ExpertGame extends Game {
    private Game game;
    private GameMode gameMode;
    private Character[] characters;
    private int coinsOfTheTable;
    private int[] playersCoins;

    /**
     * Creates an expert mode game instance.
     * @param numberOfPlayers number of players that will play the match
     * @param nicknames nicknames of the players in the match
     */
    public ExpertGame(int numberOfPlayers, List<String> nicknames) {
        super(numberOfPlayers, nicknames);
        gameMode = GameMode.EXPERT;
        game = new Game(numberOfPlayers, nicknames);
        coinsOfTheTable = 20 - numberOfPlayers;
        playersCoins = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) playersCoins[i] = 10;
        try {
            characters = new Character[3];
            createCharacters();
            for (int i = 0; i < 3; i++) {
                if (characters[i].getID() == 5) {
                    characters[i].setRound(this.getRound());
                    setRound(characters[i]);
                }
            }
        } catch (EmptyBagException e) {
            e.printStackTrace();
        }
    }

    public Character[] getCharacters(){
        return characters;
    }

    public GameMode getGameMode() {return gameMode;}

    public void setCharacters(Character[] characters){
        this.characters = characters;
    }

    /**
     * Sets a single character of the expert mode match. If the index is not valid the method doesn't do anything
     * @param character character to be set
     * @param position index of the character, in the 3 character array, that has to be associated to the given one
     */
    public void setASingleCharacter(Character character, int position) { characters[position] = character; }

    /**
     * Creates the instances of the three characters of the current match, choosing three random characters of the deck
     * @throws EmptyBagException if a character, which has students placed on it, tries to draw a student from an empty bag
     */
    public void createCharacters() throws EmptyBagException {
        List<Character> c = new ArrayList<>();

        String characters = CharactersStorage.getCharacters();
        String[] charactersParsed = characters.split(";");
        for (String s : charactersParsed) {
            String[] character = s.split(",");
            int ID = Integer.parseInt(character[0]);
            int cost = Integer.parseInt(character[1]);
            switch (ID) {
                case 1: //OK
                    Monk c1 = new Monk(ID, cost, 4);
                    c1.addStudents(game.getGameTable().getBag().drawStudents(4));
                    c.add(c1);
                    break;
                case 2: //OK
                    InnKeeper c2 = new InnKeeper(ID, cost);
                    c.add(c2);
                    break;
                case 3: //OK
                    Herald c3 = new Herald(ID, cost);
                    c.add(c3);
                    break;
                case 4: //OK
                    Postman c4 = new Postman(ID, cost);
                    c.add(c4);
                    break;
                case 5: //OK
                    Healer c5 = new Healer(ID, cost);
                    c.add(c5);
                    break;
                case 6: //OK
                    Centaur c6 = new Centaur(ID, cost);
                    c.add(c6);
                    break;
                case 7: //OK
                    Jester c7 = new Jester(ID, cost, 6);
                    c7.addStudents(game.getGameTable().getBag().drawStudents(6));
                    c.add(c7);
                    break;
                case 8: //OK
                    Knight c8 = new Knight(ID, cost);
                    c.add(c8);
                    break;
                case 9:  //OK
                    Villager c9 = new Villager(ID, cost);
                    c.add(c9);
                    break;
                case 10: //OK
                    Minstrel c10 = new Minstrel(ID, cost);
                    c.add(c10);
                    break;
                case 11:    //OK
                    Princess c11 = new Princess(ID, cost, 4);
                    c11.addStudents(game.getGameTable().getBag().drawStudents(4));
                    c.add(c11);
                    break;
                case 12:    //OK
                    Thief c12 = new Thief(ID, cost);
                    c.add(c12);
                    break;
            }
        }
        /*Random rnd = new Random();
        int numberOfCharacter = 8;
        for (int i = 0; i < 3; i++) {
            this.characters[i] = c.remove(rnd.nextInt(numberOfCharacter));
            numberOfCharacter--;
        }*/
        this.characters[0] = c.get(0);
        this.characters[1] = c.get(1);
        this.characters[2] = c.get(2);
    }

    public int getIdCharacter(int indexCard) throws InvalidIndexException {
        if (indexCard < 0 || indexCard > 3) throw new InvalidIndexException("Error character index gameTable");
        else return this.characters[indexCard].getID();
    }

    public int getCostCharacter(int indexCard) throws InvalidIndexException {
        if (indexCard < 0 || indexCard > 3) throw new InvalidIndexException("Error character index gameTable");
        else return this.characters[indexCard].getCost();
    }

    public List<Student> getStudentFromCard(int indexCard, List<Integer> studentsPositions) {
        List<Student> returnStudents = new ArrayList<>();
        try {
            if (characters[indexCard] instanceof CharacterWithStudent) {
                returnStudents.addAll(((CharacterWithStudent) characters[indexCard]).getStudents(studentsPositions));
                ((CharacterWithStudent) characters[indexCard]).addStudents(game.getGameTable().getBag().drawStudents(studentsPositions.size()));
            }
        } catch(EmptyBagException e) {
            // The bag is empty : we continue without adding students on the character
        }catch (InvalidIndexException e) {

        }
        return returnStudents;
    }

    public void addStudentsOnCard(int cardIndex, List<Student> newStudents) {
        if(characters[cardIndex] instanceof CharacterWithStudent)
            ((CharacterWithStudent)this.characters[cardIndex]).addStudents(newStudents);
    }

    public Character getCharacter(int indexCard) {
        return this.characters[indexCard];
    }

    @Override
    public void activateEffect(int playerId, int indexCard) throws EffectCannotBeActivatedException, NotEnoughCoins {
        try {
            int cost = getCostCharacter(indexCard);
            if (playersCoins[playerId] < cost) throw new NotEnoughCoins();
            if (!getCharacter(indexCard).getFirstUse()) getCharacter(indexCard).setFirstUse();
            setRound(characters[indexCard].activateEffect(playerId, getRound()));
            removeCoinsFromAPlayer(playerId, cost);
            addCoinsToTheTable(cost);
            sendGame();
        } catch (InvalidIndexException e) {
            e.printStackTrace(); // Non esiste quell'indice
        }
    }

    public int getCoinsOfTheTable() {
        return coinsOfTheTable;
    }

    public int[] getPlayersCoins() {
        return playersCoins;
    }

    public void addCoinToAPlayer(int playerIndex) throws NotEnoughCoins {
        if (getCoinsOfTheTable() <= 0) throw new NotEnoughCoins();
        playersCoins[playerIndex]++;
    }

    public void removeCoinsFromAPlayer(int playerIndex, int numberOfCoinsToRemove) throws NotEnoughCoins {
        if(playersCoins[playerIndex] < numberOfCoinsToRemove) throw new NotEnoughCoins();
        playersCoins[playerIndex] -= numberOfCoinsToRemove;
    }

    public void addCoinsToTheTable(int numberOfCoinsToAdd) {
        coinsOfTheTable += numberOfCoinsToAdd;
    }

    public void removeCoinFromTheTable() throws NotEnoughCoins {
        if(coinsOfTheTable < 1) throw new NotEnoughCoins();
        coinsOfTheTable -= 1;
    }
/*
    @Override
    public void endRound() {

        /// più tante altre cose immagino tipo game.endRound();
        game.setGameTable(game.getGameTable().getGameTableInstance());
    }*/
}
