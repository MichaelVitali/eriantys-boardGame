package it.polimi.ingsw.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameTableAdvanced extends GameTable{
    private Character[] characters;
    private SchoolboardAdvanced schoolBoards[];
    private int coins;


    public GameTableAdvanced(int numberOfPlayers, Player[] players) {
        super(numberOfPlayers, players);
        try {
            this.createCharacters();
            this.coins = 20;
        }catch(EmptyBagException e) {
            e.printStackTrace();
        }
    }

    public void createCharacters() throws EmptyBagException{
        JSONParser parser = new JSONParser();
        List<Character> c = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("it\\polimi\\ingsw\\Model\\Characters.js"));
            for (Object o : a) {
                JSONObject assistant = (JSONObject) o;

                int  ID =  Integer.parseInt((String) assistant.get("ID"));
                int cost = Integer.parseInt((String) assistant.get("cost"));

                if(ID == 1 || ID == 11){
                    CharacterWithStudent character = new CharacterWithStudent(ID,cost,4);
                    character.addStudents(Bag.getBag().drawStudents(4));
                    c.add(character);

                }else if(ID == 7){
                    CharacterWithStudent character = new CharacterWithStudent(ID,cost,6);
                    character.addStudents(Bag.getBag().drawStudents(6));
                    c.add(character);
                }else{
                    c.add(new Character(ID, cost));
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        Random rnd = new Random();
        int numberOfCharacter = 8;
        for(int i = 0; i < 3; i++){
            this.characters[i] = c.remove(rnd.nextInt(numberOfCharacter));
            numberOfCharacter--;
        }
    }

    public int getIDCharacter(int indexCard) throws InvalidNumberException{
        if(indexCard < 0 || indexCard > 3) throw new InvalidNumberException("Error character index gameTable");
        else return this.characters[indexCard].getID();
    }

    public int getCostCharacter(int indexCard) throws InvalidNumberException{
        if(indexCard < 0 || indexCard > 3) throw new InvalidNumberException("Error character index gameTable");
        else return this.characters[indexCard].getCost();
    }

    public List<Student> getStudentFromCard(int indexCard, List<Integer> studentsPositions) throws EmptyBagException{
        List<Student> returnStudents = new ArrayList<>();
        returnStudents.addAll(((CharacterWithStudent)characters[indexCard]).getStudents(studentsPositions));
        ((CharacterWithStudent)characters[indexCard]).addStudents(Bag.getBag().drawStudents(studentsPositions.size()));
        return returnStudents;
    }

    public void addStudentsOnCard(int cardIndex, List<Student> newStudents){
        ((CharacterWithStudent)this.characters[cardIndex]).addStudents(newStudents);
    }

    public Character getCharacter(int indexCard){
        return this.characters[indexCard];
    }

    //da sistemare schollboard advance
    @Override
    public void addStudentOnTableFromEntrance(int indexStudent, int schoolboardIndex){
        this.schoolBoards[schoolboardIndex].addStudentOnTable(indexStudent);
        if(this.schoolBoards[schoolboardIndex].getAddCoin() && this.coins > 0){
            this.schoolBoards[schoolboardIndex].addCoin();
            this.coins--;
        }else{
            this.schoolBoards[schoolboardIndex].setAddCoinFalse();
        }
    }

    public void addCoins(int coins){
        if(coins >= 0) this.coins += coins;
    }

    public void addSchoolBoards(SchoolboardAdvanced[] schoolBoards){
        if(schoolBoards != null)
            this.schoolBoards = schoolBoards;
    }

    public int getCoins(){
        return this.coins;
    }

    public void removeCoin(){
        if(this.coins > 0)
            this.coins--;
    }
}
