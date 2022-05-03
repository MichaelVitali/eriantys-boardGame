package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpertGame extends Game {
    private Game game;
    private Character[] characters;
    private int coinsOfTheTable;
    private int[] playersCoins;

    public ExpertGame(int numberOfPlayers, List<String> nicknames) {
        super(numberOfPlayers, nicknames);
        game = new Game(numberOfPlayers, nicknames);
        coinsOfTheTable = 20 - numberOfPlayers;
        playersCoins = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) playersCoins[i] = 1;
        try {
            characters = new Character[3];
            createCharacters();
        } catch (EmptyBagException e) {
            e.printStackTrace();
        }
    }

    public Character[] getCharacters(){
        return characters;
    }

    public void setCharacters(Character[] characters){
        this.characters=characters;
    }

    public void createCharacters() throws EmptyBagException {
        List<Character> c = new ArrayList<>();

        String characters = CharactersStorage.getCharacters();
        String[] charactersParsed = characters.split(";");
        for (String s : charactersParsed) {
            String[] character = s.split(",");
            int ID = Integer.parseInt(character[0]);
            int cost = Integer.parseInt(character[1]);

            if(ID == 1 || ID == 11){
                CharacterWithStudent characterWithStudent = new CharacterWithStudent(ID, cost, 4);
                characterWithStudent.addStudents(game.getGameTable().getBag().drawStudents(4));
                c.add(characterWithStudent);
            }else if (ID == 7) {
                CharacterWithStudent characterWithStudent = new CharacterWithStudent(ID, cost, 6);
                characterWithStudent.addStudents(game.getGameTable().getBag().drawStudents(6));
                c.add(characterWithStudent);
            } else {
                c.add(new Character(ID, cost));
            }
        }

        Random rnd = new Random();
        int numberOfCharacter = 8;
        for (int i = 0; i < 3; i++) {
            this.characters[i] = c.remove(rnd.nextInt(numberOfCharacter));
            numberOfCharacter--;
        }
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
    public void activateEffect(int playerId, int indexCard) {
        GameTable gameTable = game.getGameTable();
        SchoolBoard[] schoolBoards = gameTable.getSchoolBoards();
        try {
            //int id = getIdCharacter(indexCard);
            int cost = getCostCharacter(indexCard);

            if (playersCoins[playerId] < cost) throw new NotEnoughCoins(); //se ho abbastanza coin per la carta eseguo l'effetto
            removeCoinsFromAPlayer(playerId, cost);
            addCoinsToTheTable(cost);
            if (!getCharacter(indexCard).getFirstUse()) getCharacter(indexCard).setFirstUse();
            setRound(characters[indexCard].activateEffect(playerId, getRound()));
        } catch (InvalidIndexException e) {
            e.printStackTrace(); // Non esiste quell'indice
        } catch (NotEnoughCoins e) {
            // Il giocatore non ha abbastanza soldi
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
