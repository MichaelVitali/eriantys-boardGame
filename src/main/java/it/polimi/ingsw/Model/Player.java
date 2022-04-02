package it.polimi.ingsw.Model;

import java.util.*;

public class Player {
    private SchoolBoard schoolBoard;
    private GameTable gameTable;
    protected final String nickName;
    protected final int id;
    protected List<Assistant> assistants;

    public Player(String nickName, int id){
        this.nickName = nickName;
        this.id=id;
        this.assistants = new ArrayList<Assistant>();
    }

    public void addGameTable(GameTable gameTable){
        this.gameTable = gameTable;
    }

    public void addSchoolBoard(SchoolBoard s){
        this.schoolBoard = s;
    }

    public Assistant playAssistant(int pos){ // MT: ho modificato il ritorno del metodo da void ad Assistant: mi serve in PianificationPhase

        if(pos >= 0 && pos < assistants.size())
            return this.assistants.remove(pos);
        else
            //TODO out of bound exception
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
    //potrebbe non servire se mettiamo il game
    public void moveMotherNature(int pos){
        this.gameTable.changeMotherNaturePosition(pos);
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
