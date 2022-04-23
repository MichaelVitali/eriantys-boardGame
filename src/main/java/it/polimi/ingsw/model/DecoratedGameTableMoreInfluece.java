package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EmptyBagException;
import it.polimi.ingsw.model.exception.EmptyCloudException;
import it.polimi.ingsw.model.exception.FullTableException;
import it.polimi.ingsw.model.exception.InvalidIndexException;

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
    public void addStudentOnIsland(Student s, int islandIndex)throws InvalidIndexException {
        this.gameTable.addStudentOnIsland(s, islandIndex);
    }
    @Override
    public SchoolBoard[] getSchoolBoards() {
        return gameTable.getSchoolBoards();
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
    public void putTowerOrChangeColorIfNecessary(){
        this.gameTable.putTowerOrChangeColorIfNecessary();
    }

    @Override
    public void mergeIslandsIfNecessary(){
        this.gameTable.mergeIslandsIfNecessary();
    }

    @Override
    public List<Student> getStudentsOnCloud(int cloudIndex) throws EmptyCloudException {
        return this.gameTable.getStudentsOnCloud(cloudIndex);
    }

    @Override
    public List<TowerColor> teamWithLessTowersOnSchoolboards() {
        return this.gameTable.teamWithLessTowersOnSchoolboards();
    }

    @Override
    public List<TowerColor> teamWithMoreProfessors(List<TowerColor> teamWithLessTowersOnSchoolboards){
        return gameTable.teamWithMoreProfessors(teamWithLessTowersOnSchoolboards);
    }

    @Override
    public void addStudentOnTableFromEntrance(int indexStudent, int schoolBoardIndex) throws FullTableException {
        this.gameTable.addStudentOnTableFromEntrance(indexStudent, schoolBoardIndex);
    }

    @Override
    public GameTable getGameTableInstance(){
        return this.gameTable;
    }
}
