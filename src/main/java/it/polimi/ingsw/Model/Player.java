package it.polimi.ingsw.Model;

import java.util.*;

public class Player {
    private SchoolBoard schoolBoard;
    private GameTable gameTable;
    protected final String nickName;
    protected List<Assistant> assistants;

    public Player(String nickName){
        this.nickName = nickName;
        this.assistants = new ArrayList<Assistant>();
    }

    public void addGameTable(GameTable gameTable){
        this.gameTable = gameTable;
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

    public void takeStudentsFromCloud(int indexCloud) {
        List<Student> s = this.gameTable.getStudentsOnCloud(indexCloud);
        this.schoolBoard.addStudentsOnEntrance(s);
    }

    public void addAssistants(List<Assistant> l) {
        this.assistants.addAll(l);
    }
}
