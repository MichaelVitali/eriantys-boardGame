package it.polimi.ingsw.Model;

import java.util.*;

public class Player {
    private SchoolBoard schoolBoard;
    private final GameTable gameTable;
    private final String nickName;
    private List<Assistant> assistants;

    public Player(String nickName, GameTable gameTable){
        this.nickName = nickName;
        this.gameTable = gameTable;
        this.assistants = new ArrayList<Assistant>();
    }

    public void addSchoolBoard(SchoolBoard s){
        this.schoolBoard = s;
    }

    public void playAssistant(int pos){
        if(pos >= 0 && pos < assistants.size())
            this.assistants.remove(pos);
    }
    //conviene creare un'eccezioone che gestisce le posizioni errate
    public Assistant getAssistant(int pos){
        if(pos >= 0 && pos < assistants.size())
            return this.assistants.get(pos);
        else
            return null;
    }

    public void moveStudentOnTable(int pos){
        this.schoolBoard.addStudentOnTable(pos);
    }

    public void moveStudentOnIsland(int posStudent, int posIsland){
        Student s = this.schoolBoard.removeStudentFromEntrance(posStudent);
        this.gameTable.addStudentOnIsland(s, posIsland);
    }

    public void moveMotherNature(int pos){
        this.gameTable.changeMotherNaturePosition(pos);
    }

    public List<PawnColor> getProfessors(){
        return this.schoolBoard.getProfessors();
    }

    public void putTowers(List<Integer> posTowers) {

        List<Tower> towers = new ArrayList<>();
        for(int i=0; i<posTowers.size(); i++){
            towers.add(schoolBoard.removeTower(posTowers.get(i)));
        }
        this.gameTable.putTowerOnIsland(towers);
    }

    public void takeStudentsFromCloud(int indexCloud) {
        List<Student> s = this.gameTable.getStudentsOnCloud(indexCloud);
        this.schoolBoard.addStudentsOnEntrance(s);
    }

    public void addAssistants(List<Assistant> l) {
        if(this.assistants.size() == 0)
            this.assistants.addAll(l);
    }
}
