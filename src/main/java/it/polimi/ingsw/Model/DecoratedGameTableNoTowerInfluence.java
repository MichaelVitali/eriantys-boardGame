package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.exception.EmptyBagException;
import it.polimi.ingsw.Model.exception.InvalidIndexException;

import java.util.List;

public class DecoratedGameTableNoTowerInfluence extends GameTable {
    private GameTable gameTable;

    public DecoratedGameTableNoTowerInfluence(GameTable gameTable) {
        super();
        this.gameTable = gameTable;
    }

    @Override
    public int getMotherNaturePosition(){
        return this.gameTable.getMotherNaturePosition();
    }

    @Override
    public int[] calculateInfluences() {
        int numberOfIteration = gameTable.getNumberOfPlayers() == 4 ? 2 : gameTable.getNumberOfPlayers();
        int[] influence = new int[numberOfIteration];
        List<Student> studentsOnTheIslands = gameTable.getIslands().get(gameTable.getMotherNaturePosition()).getStudents();
        List<Tower> towersOnTheIslands = getIslands().get(gameTable.getMotherNaturePosition()).getTowers();
        influence[0] = 0;

        for (int i = 0; i < numberOfIteration; i++) {
            influence[i] = 0;
            List<PawnColor> professors = gameTable.getSchoolBoards()[i].getProfessors();
            if (gameTable.getNumberOfPlayers() == 4) professors.addAll(gameTable.getSchoolBoards()[i + 2].getProfessors());
            for (PawnColor professor : professors) {
                for (Student student : studentsOnTheIslands) {
                    if (professor == student.getColor()) influence[i]++;
                }
            }
        }
        return influence;
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
            System.out.println("Error in effect 6");
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
    public List<Student> getStudentsOnCloud(int cloudIndex){
        return this.gameTable.getStudentsOnCloud(cloudIndex);
    }

    @Override
    public TowerColor teamWithLessTowersOnSchoolboars(){
        return this.gameTable.teamWithLessTowersOnSchoolboars();
    }

    @Override
    public TowerColor teamWithMoreProfessors(){
        return this.teamWithMoreProfessors();
    }

    @Override
    public void addStudentOnTableFromEntrance(int indexStudent, int schoolBoardIndex) {
        this.gameTable.addStudentOnTableFromEntrance(indexStudent, schoolBoardIndex);
    }

    public GameTable getGameTableInstance() {
        return gameTable;
    }
}
