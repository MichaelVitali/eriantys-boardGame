package it.polimi.ingsw.model;
import it.polimi.ingsw.model.exception.EmptyCloudException;
import it.polimi.ingsw.model.exception.FullTableException;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import it.polimi.ingsw.model.exception.OutOfBoundException;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private SchoolBoard schoolBoard;
    private GameTable gameTable;
    private final String nickName;
    private final int playerId;
    private List<Assistant> assistants;
    private String message;

    public Player(String nickName, int playerId, List<Assistant> assistants) {
        this.nickName = nickName;
        this.playerId = playerId;
        this.assistants = new ArrayList<>();
        this.assistants.addAll(assistants);
    }

    public String getNickname(){
        return this.nickName;
    }

    public void addGameTable(GameTable gameTable){
        this.gameTable = gameTable;
    }

    public void addSchoolBoard(SchoolBoard s){
        this.schoolBoard = s;
    }

    public Assistant playAssistant(int pos) throws InvalidIndexException {
        if(pos < 0 || pos >= assistants.size()) throw new InvalidIndexException("Assistant index out of bound");
        return this.assistants.remove(pos);
    }

    /**
     * Returns the list containing the player's assistants
     * @return list containing the assistants the player hasn't played yet
     */
    public List<Assistant> getAssistants() {
        List<Assistant> returnedAssistants = new ArrayList<>();
        returnedAssistants.addAll(assistants);
        return returnedAssistants;
    }

    public Assistant getAssistant(int position) throws InvalidIndexException{
        if(position < 0 && position >= assistants.size()) throw new InvalidIndexException("Choose a valid assistant");
        return this.assistants.get(position);
    }

    public Assistant removeAssistant(int position) throws InvalidIndexException{
        if(position < 0 && position >= assistants.size()) throw new InvalidIndexException("Choose a valid assistant");
        return this.assistants.remove(position);
    }

    public void moveStudentOnTable(int pos) throws FullTableException {
        this.schoolBoard.addStudentOnTable(pos);
    }

    public void moveStudentOnIsland(int posStudent, int posIsland) throws InvalidIndexException {
        Student s = this.schoolBoard.removeStudentFromEntrance(posStudent);
        this.gameTable.addStudentOnIsland(s, posIsland);
    }

    public String getPlayerMessage() {
        return message;
    }

    public void setPlayerMessage(String message) {
        this.message = message;
    }

    public void takeStudentsFromCloud(int indexCloud) throws EmptyCloudException, InvalidIndexException {
        List<Student> s = this.gameTable.getStudentsOnCloud(indexCloud);
        this.schoolBoard.addStudentsOnEntrance(s);
    }

    public void addAssistants(List<Assistant> l) {
        if(this.assistants.size() == 0)
            this.assistants.addAll(l);
    }

    public Student[] getStudentsFormEntrance(){
        return this.schoolBoard.getStudentsFromEntrance();
    }
}
