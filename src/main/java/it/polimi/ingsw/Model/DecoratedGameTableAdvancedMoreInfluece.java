package it.polimi.ingsw.Model;

import java.util.List;

public class DecoratedGameTableAdvancedMoreInfluece extends GameTableAdvanced{
    private GameTableAdvanced gameTable;
    private int playerIndex;

    public DecoratedGameTableAdvancedMoreInfluece(GameTableAdvanced gameTable, int  playerIndex){
        super();
        this.gameTable = gameTable;
        this.playerIndex = playerIndex;
    }
    @Override
    public int getMotherNaturePosition(){
        return this.gameTable.getMotherNaturePosition();
    }

    @Override
    public int[] calculateInfluences(){
        int[] influences = this.gameTable.calculateInfluences();
        influences[playerIndex] += 2;
        return influences;
    }

    @Override
    public void addStudentsOnClouds() throws EmptyBagException{
        this.gameTable.addStudentsOnClouds();
    }

    @Override
    public void moveProfessorToTheRightPosition(PawnColor colorOfTheMovedStudent){
        this.gameTable.moveProfessorToTheRightPosition(colorOfTheMovedStudent);
    }

    @Override
    public void addStudentOnIsland(Student s, int islandIndex){
        this.gameTable.addStudentOnIsland(s, islandIndex);
    }

    @Override
    public void changeMotherNaturePosition(int newPosition){
        this.gameTable.changeMotherNaturePosition(newPosition);
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
    public List<Student> getStudentsOnCloud(int cloudIndex){
        return this.gameTable.getStudentsOnCloud(cloudIndex);
    }

    @Override
    protected TowerColor teamWithLessTowersOnSchoolboars(){
        return this.gameTable.teamWithLessTowersOnSchoolboars();
    }

    @Override
    protected TowerColor teamWithMoreProfessors(){
        return this.teamWithMoreProfessors();
    }

    @Override
    public void addStudentOnTableFromEntrance(int indexStudent, int schoolBoardIndex){
        this.gameTable.addStudentOnTableFromEntrance(indexStudent, schoolBoardIndex);
    }

    @Override
    public void addCoins(int coins){
        this.gameTable.addCoins(coins);
    }

    @Override
    public int getCoins(){
        return this.gameTable.getCoins();
    }

    @Override
    public void removeCoin(){
        this.gameTable.removeCoin();
    }

    public GameTableAdvanced getGameTableAdvancedInstance(){
        return this.gameTable;
    }
}
