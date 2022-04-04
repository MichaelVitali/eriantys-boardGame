package it.polimi.ingsw.Model;

import java.util.*;

public class Player {
    private SchoolBoard schoolBoard;
    private GameTable gameTable;
    protected final String nickName;
    protected final int playerId;
    protected List<Assistant> assistants;

    public Player(String nickName, int playerId, List<Assistant> assistants) {
        this.nickName = nickName;
        this.playerId = playerId;
        this.assistants = new ArrayList<>();
        this.assistants.addAll(assistants);
    }
    // secondo me non serve
    public void addGameTable(GameTable gameTable){
        this.gameTable = gameTable;
    }

    public void addSchoolBoard(SchoolBoard s){
        this.schoolBoard = s;
    }

    public Assistant playAssistant(int pos) { // MT: ho modificato il ritorno del metodo da void ad Assistant: mi serve in PianificationPhase
        if(pos < 0 && pos >= assistants.size()) {} // throw new InvalidIndexException("No such index in assistants"); TODO out of bound exception
        return this.assistants.remove(pos);
    }

    public Assistant getAssistant(int pos) {
        if(pos < 0 && pos >= assistants.size()) {} // throw new InvalidIndexException("No such index in assistants"); TODO out of bound exception
        return this.assistants.get(pos);
    }
    // non so se serva
    public void moveStudentOnTable(int pos){
        this.schoolBoard.addStudentOnTable(pos);
    }
    // non so se serva
    public void moveStudentOnIsland(int posStudent, int posIsland){
        Student s = this.schoolBoard.removeStudentFromEntrance(posStudent);
        this.gameTable.addStudentOnIsland(s, posIsland);
    }

    /*public void putTowers(List<Integer> posTowers) {

        List<Tower> towers = new ArrayList<>();
        for(int i=0; i<posTowers.size(); i++){
            towers.add(schoolBoard.removeTower(posTowers.get(i)));
        }
        this.gameTable.putTowerOnIsland(towers);
    }*/

    public void takeStudentsFromCloud(int indexCloud) {
        List<Student> s = this.gameTable.getStudentsOnCloud(indexCloud);
        this.schoolBoard.addStudentsOnEntrance(s);
    }

    public void addAssistants(List<Assistant> l) {
        if(this.assistants.size() == 0)
            this.assistants.addAll(l);
    }
}
