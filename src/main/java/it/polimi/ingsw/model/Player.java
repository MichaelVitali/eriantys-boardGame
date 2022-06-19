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
    private String messageCli;
    private String messageGui;
    private boolean error;
    private Wizard wizard;

    public Player(String nickName, int playerId, List<Assistant> assistants) {
        this.nickName = nickName;
        this.playerId = playerId;
        this.assistants = new ArrayList<>();
        this.assistants.addAll(assistants);
        error = false;
        wizard = null;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setWizard(Wizard wizard){
        this.wizard = wizard;
    }

    public Wizard getWizard(){
        return this.wizard;
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

    public Assistant getAssistant(int id) throws InvalidIndexException{
        boolean flag = false;
        int pos = -1;
        for(int i = 0; i < assistants.size(); i++) {
            if (assistants.get(i).getCardValue() == id) {
                flag = true;
                pos = i;
                break;
            }
        }
        if (flag) return assistants.get(pos);
        else throw new InvalidIndexException("The assistant doesn't exist!\n");
    }

    public Assistant removeAssistant(int id) throws InvalidIndexException{
        boolean flag = false;
        int pos = -1;
        for(int i = 0; i < assistants.size(); i++) {
            if (assistants.get(i).getCardValue() == id) {
                flag = true;
                pos = i;
                break;
            }
        }
        if (flag) return assistants.remove(pos);
        else throw new InvalidIndexException("The assistant doesn't exist!\n");
    }

    public void moveStudentOnTable(int pos) throws FullTableException, InvalidIndexException {
        this.schoolBoard.addStudentOnTable(pos);
    }

    public void moveStudentOnIsland(int posStudent, int posIsland) throws InvalidIndexException {
        Student s = this.schoolBoard.removeStudentFromEntrance(posStudent);
        this.gameTable.addStudentOnIsland(s, posIsland);
    }

    public String getPlayerMessageCli() {
        return messageCli;
    }

    public String getPlayerMessageGui() {
        return messageGui;
    }

    public void setPlayerMessageCli(String message) {
        messageCli = message;
    }

    public void setPlayerMessageGui(String message) {
        messageGui = message;
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
