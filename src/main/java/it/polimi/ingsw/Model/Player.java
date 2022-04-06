package it.polimi.ingsw.Model;
import it.polimi.ingsw.Model.exception.*;

import java.util.*;

public class Player {
    private SchoolBoard schoolBoard;
    private GameTable gameTable;
    protected final String nickName;
    protected final int playerId;
    protected List<Assistant> assistants;
    private String errorMessage;

    public Player(String nickName, int playerId, List<Assistant> assistants) {
        this.nickName = nickName;
        this.playerId = playerId;
        this.assistants = new ArrayList<>();
        this.assistants.addAll(assistants);
    }

    public void addGameTable(GameTable gameTable){
        this.gameTable = gameTable;
    }

    public void addSchoolBoard(SchoolBoard s){
        this.schoolBoard = s;
    }

    public Assistant playAssistant(int pos) throws OutOfBoundException {
        if(pos < 0 || pos >= assistants.size()) throw new OutOfBoundException("Assistant index out of bound");
        return this.assistants.remove(pos);
    }

    public Assistant getAssistant(int pos) {
        if(pos < 0 && pos >= assistants.size()) {} // throw new InvalidIndexException("No such index in assistants"); TODO out of bound exception
        return this.assistants.get(pos);
    }

    public void moveStudentOnTable(int pos){
        this.schoolBoard.addStudentOnTable(pos);
    }

    // non so se serva
    public void moveStudentOnIsland(int posStudent, int posIsland) throws InvalidIndexException {
        Student s = this.schoolBoard.removeStudentFromEntrance(posStudent);
        this.gameTable.addStudentOnIsland(s, posIsland);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void takeStudentsFromCloud(int indexCloud) throws EmptyCloudException {
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
