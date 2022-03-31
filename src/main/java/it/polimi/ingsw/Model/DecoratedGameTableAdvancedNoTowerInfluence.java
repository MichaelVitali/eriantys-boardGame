package it.polimi.ingsw.Model;

import java.util.List;

public class DecoratedGameTableAdvancedNoTowerInfluence extends GameTableAdvanced{
    private GameTableAdvanced gameTable;

    public DecoratedGameTableAdvancedNoTowerInfluence(GameTableAdvanced gameTable){
        super();
        this.gameTable = gameTable;
    }

    @Override
    public int getMotherNaturePosition(){
        return this.gameTable.getMotherNaturePosition();
    }

    @Override
    public int[] calculateInfluences(){
        int numberOfIteration = numberOfPlayers == 4 ? 2 : numberOfPlayers;
        int[] influence = new int[numberOfIteration];
        List<Student> studentsOnTheIslands = islands.get(motherNaturePosition).getStudents();
        List<Tower> towersOnTheIslands = islands.get(motherNaturePosition).getTowers();
        influence[0] = 0;

        for(int i = 0; i < numberOfIteration; i++) {
            influence[i] = 0;
            List<PawnColor> professors = schoolBoards[i].getProfessors();
            if (numberOfPlayers == 4) professors.addAll(schoolBoards[i + 2].getProfessors());
            for (PawnColor professor : professors) {
                for (Student student : studentsOnTheIslands) {
                    if (professor == student.getColor()) influence[i]++;
                }
            }
        }
        return influence;
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
