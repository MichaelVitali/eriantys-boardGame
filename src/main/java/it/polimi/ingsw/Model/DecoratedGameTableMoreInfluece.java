package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.exception.EmptyBagException;
import it.polimi.ingsw.Model.exception.EmptyCloudException;
import it.polimi.ingsw.Model.exception.InvalidIndexException;

import java.util.List;

public class DecoratedGameTableMoreInfluece extends GameTable {
    private GameTable gameTable;
    private int playerIndex;

    public DecoratedGameTableMoreInfluece(GameTable gameTable, int  playerIndex) {
        super();
        this.gameTable = gameTable;
        this.playerIndex = playerIndex;
    }
    @Override
    public int getMotherNaturePosition() {
        return this.gameTable.getMotherNaturePosition();
    }

    @Override
    public int[] calculateInfluences() {
        int[] influences = this.gameTable.calculateInfluences();
        influences[playerIndex] += 2;
        return influences;
    }

    @Override
    public void addStudentsOnClouds() throws EmptyBagException {
        this.gameTable.addStudentsOnClouds();
    }

    @Override
    public void moveProfessorToTheRightPosition(PawnColor colorOfTheMovedStudent) {
        this.gameTable.moveProfessorToTheRightPosition(colorOfTheMovedStudent);
    }

    @Override
    public void addStudentOnIsland(Student s, int islandIndex){
        this.gameTable.addStudentOnIsland(s, islandIndex);
    }

    @Override
    public void changeMotherNaturePosition(int newPosition){
        try {
            this.gameTable.changeMotherNaturePosition(newPosition);
        } catch (InvalidIndexException e) {
            System.out.println("Error in effect 8");
        }
    }

    @Override
    protected void putTowerOrChangeColorIfNecessary(){
        this.gameTable.putTowerOrChangeColorIfNecessary();
    }

    @Override
    protected void mergeIslandsIfNecessary(){
        this.gameTable.mergeIslandsIfNecessary();
    }

    @Override
    public List<Student> getStudentsOnCloud(int cloudIndex) throws EmptyCloudException {
        return this.gameTable.getStudentsOnCloud(cloudIndex);
    }

    @Override
    protected TowerColor teamWithLessTowersOnSchoolboars(){
        return this.gameTable.teamWithLessTowersOnSchoolboards();
    }

    @Override
    protected TowerColor teamWithMoreProfessors(){
        return this.teamWithMoreProfessors();
    }

    @Override
    public void addStudentOnTableFromEntrance(int indexStudent, int schoolBoardIndex) {
        this.gameTable.addStudentOnTableFromEntrance(indexStudent, schoolBoardIndex);
    }

    @Override
    public GameTable getGameTableInstance(){
        return this.gameTable;
    }
}
