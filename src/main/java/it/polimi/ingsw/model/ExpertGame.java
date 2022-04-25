package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpertGame extends Game {
    private Game game;
    private Character[] characters;
    private int coinsOfTheTable;
    private int[] playersCoins;
    //// private boolean[] newCoinOrNot;

    public ExpertGame(int numberOfPlayers, String[] nicknames) {
        super(numberOfPlayers, nicknames);
        game = new Game(numberOfPlayers, nicknames);
        coinsOfTheTable = 20 - numberOfPlayers;
        playersCoins = new int[numberOfPlayers];
        characters=new Character[3];
        for(int coins : playersCoins)
            coins = 1;
        try {
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
        JSONParser parser = new JSONParser();
        List<Character> c = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\Manuel\\IdeaProjects\\ing-sw-2022-Vitali-Tacca-Simionato\\src\\main\\java\\it\\polimi\\ingsw\\model\\Characters.js"));
            for (Object o : a) {
                JSONObject assistant = (JSONObject) o;

                int ID =  Integer.parseInt(assistant.get("ID").toString());
                int cost = Integer.parseInt(assistant.get("cost").toString());

                if (ID == 1 || ID == 11) {
                    CharacterWithStudent character = new CharacterWithStudent(ID,cost,4);
                    character.addStudents(game.getGameTable().getBag().drawStudents(4));
                    c.add(character);

                } else if(ID == 7) {
                    CharacterWithStudent character = new CharacterWithStudent(ID,cost,6);
                    character.addStudents(game.getGameTable().getBag().drawStudents(6));
                    c.add(character);
                } else {
                    c.add(new Character(ID, cost));
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
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

    public void effectActivation(int playerIndex, int indexCard, List<Integer> studentsIndex, List<Integer> studentsIndexEntrance, int islandIndex, List<Integer> indexTable, String color) {
        GameTable gameTable = game.getGameTable();
        SchoolBoard[] schoolBoards = gameTable.getSchoolBoards();
        try {
            int id = getIdCharacter(indexCard);
            int cost = getCostCharacter(indexCard);

            if (playersCoins[playerIndex] < cost) throw new NotEnoughCoins(); //se ho abbastanza coin per la carta eseguo l'effetto
            removeCoinsFromAPlayer(playerIndex, cost);
            addCoinsToTheTable(cost);
            if (!getCharacter(indexCard).getFirstUse()) getCharacter(indexCard).setFirstUse();

            switch (id) {
                case 1:
                    List<Student> studentsOnCard = new ArrayList<>(getStudentFromCard(indexCard, studentsIndex));
                    try {
                        if (studentsOnCard.size() <= 0) throw new NoMoreStudentsException();
                        Student newStudent = studentsOnCard.remove(0);
                        gameTable.addStudentOnIsland(newStudent, islandIndex);
                    } catch (NoMoreStudentsException e) {
                        // bisogna capire come dire all'utente che non ci sono più studenti e non può usare l'effetto
                    }
                    break;
                case 2:
                    break;
                case 3:
                    int oldPosition = gameTable.getMotherNaturePosition();
                    // exception da togliere
                    try {
                        if (isAValidPositionForMotherNature(islandIndex)) {
                            gameTable.changeMotherNaturePosition(islandIndex);
                            gameTable.putTowerOrChangeColorIfNecessary();
                        }
                        gameTable.changeMotherNaturePosition(oldPosition);
                    } catch (InvalidIndexException e) {
                        System.out.println("Error in effect 3");
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    DecoratedGameTableNoTowerInfluence decoratedGameTableNoTower = new DecoratedGameTableNoTowerInfluence(gameTable);
                    game.setGameTable(decoratedGameTableNoTower);
                    break;
                case 7:
                    if (studentsIndex.size() > 3 || studentsIndexEntrance.size() > 3 || studentsIndexEntrance.size() != studentsIndex.size()) throw new InvalidIndexException("Error Effect 7");
                    List<Student> newStudentsOnEntrance = new ArrayList<>(getStudentFromCard(indexCard, studentsIndex));
                    List<Student> newStudentsOnCard = new ArrayList<>();
                    for (Integer i : studentsIndexEntrance)
                        newStudentsOnCard.add(schoolBoards[playerIndex].removeStudentFromEntrance(i));
                    addStudentsOnCard(indexCard, newStudentsOnCard);
                    schoolBoards[playerIndex].addStudentsOnEntrance(newStudentsOnEntrance);

                    /////// mancano i controlli per la carta vuota o l'entrance vuota -- non so se servono

                    break;
                case 8:
                    DecoratedGameTableMoreInfluece decoratedGameTableMoreInfluence = new DecoratedGameTableMoreInfluece(gameTable, playerIndex);
                    game.setGameTable(decoratedGameTableMoreInfluence);
                    break;
                case 9:
                    break;
                case 10: ////////////// da rivedere
                    if (indexTable.size() > 2 || indexTable.size() <= 0 || studentsIndexEntrance.size() > 2 || studentsIndexEntrance.size() <= 0) throw new InvalidIndexException("Error Effect 10");
                        newStudentsOnEntrance = new ArrayList<>();
                        //estrae gli studenti dai tavoli selezionati
                        try {
                            if (indexTable.size() == 1) {
                                if (studentsIndexEntrance.size() == 1) {
                                    newStudentsOnEntrance.add(schoolBoards[playerIndex].removeStudentFromTable(indexTable.get(0)));
                                } else {
                                    for (int j = 0; j < 2; j++) {
                                        newStudentsOnEntrance.add(schoolBoards[playerIndex].removeStudentFromTable(indexTable.get(0)));
                                    }
                                }
                            } else {
                                for (Integer i : indexTable) {
                                    newStudentsOnEntrance.add(schoolBoards[playerIndex].removeStudentFromTable(i));
                                }
                            }
                            //mette gli studenti selezionati dalla entrance ai tavoli
                            for (Integer i : studentsIndexEntrance) {
                                schoolBoards[playerIndex].addStudentOnTable(i);
                            }
                        } catch (EmptyTableException e) {
                            // se arrivo qua c'è un problema nel codice
                            ////////////////////////////////////////////////////// da vedere se fare qualcosa
                            e.printStackTrace();
                        } catch (FullTableException e) {
                            e.printStackTrace();
                            ////////////////////////////////////////////////////// da vedere se fare qualcosa
                        }

                        //mette li studenti nei tavoli nella entrance
                        for (Student student : newStudentsOnEntrance) {
                            schoolBoards[playerIndex].addStudentsOnEntrance(newStudentsOnEntrance);
                            if (schoolBoards[playerIndex].getNumberOfStudentsOnTable(student.getColor()) % 3 == 0) {
                                try {
                                    removeCoinFromTheTable();
                                    addCoinToAPlayer(playerIndex);
                                } catch (NotEnoughCoins e) {
                                    ///////// da vedere cosa fare se sono finiti i coins
                                }
                            }
                        }

                    break;
                case 11:
                    if (studentsIndex.size() == 1) {
                        try {
                            studentsOnCard = new ArrayList<>(getStudentFromCard(indexCard, studentsIndex)); /// potrebbe aver finito gli studenti?
                            Student student = studentsOnCard.remove(0);
                            schoolBoards[playerIndex].addStudentOnTable(student);
                            if (schoolBoards[playerIndex].getNumberOfStudentsOnTable(student.getColor()) % 3 == 0) {
                                try {
                                    removeCoinFromTheTable();
                                    addCoinToAPlayer(playerIndex);
                                } catch (NotEnoughCoins e) {
                                    ///////// da vedere cosa fare se sono finiti i coins
                                }
                            }
                            try {
                                addStudentsOnCard(indexCard, game.getGameTable().getBag().drawStudents(1));
                            } catch (EmptyBagException e) {
                                // The bag is empty : we continue without adding students on the character
                            }
                        } catch (FullTableException e) {
                            game.getRound().setErrorMessage(playerIndex, "Tou cannot add that student on the table");
                        }
                    }
                    break;
                case 12:
                    int tableIndex = -1;
                    for (PawnColor c : PawnColor.values()) {
                        if (c.toString().equals(color)) tableIndex = c.getIndex();
                    }
                    if (tableIndex == -1) throw new InvalidIndexException("Error color effect");
                    List<Student> newStudentsBag = new ArrayList<>();
                    for (SchoolBoard schoolBoard : game.getGameTable().getSchoolBoards()) {
                        try {
                            for (int i = 0; i < 3; i++)
                                newStudentsBag.add(schoolBoard.removeStudentFromTable(tableIndex));
                        } catch (EmptyTableException e) { } // non faccio nulla se il tavolo è vuoto e proseguo con gli altri giocatori
                        game.getGameTable().getBag().addStudents(newStudentsBag);
                    }
                    break;
            }
        } catch (NotEnoughCoins e) {
            // Il giocatore non aveva abbastanza soldi, bisogna andare in uno stato di essore o notificarglielo
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    public int getCoinsOfTheTable() {
        return coinsOfTheTable;
    }

    public int[] getPlayersCoins() {
        return playersCoins;
    }

    public void addCoinToAPlayer(int playerIndex) {
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
    }
 */
}
