package it.polimi.ingsw.Model;


import java.util.List;

public class GameTable {
    private int playersNumber;
    private List<Island> islands;
    private int numberOfClouds;
    private Cloud[] clouds;
    private SchoolBoard[] schoolBoards;
    private Assistant[] assistants; /////////non mi ricordo come li utilizzavamo quindi non ci saranno nei metodi


    public GameTable(int playersNumber) {
        this.playersNumber = playersNumber;

        //////// va inizializzato tutto
    }

    public void addStudentsOnClouds() {}
    public void addStudentOnIsland(int player, int entranceIndex) {}
    public void changeMotherNaturePosition(int islandIndex) {
        //////valutare come farlo e se mantenere MotherNature
    }

    public Island getMotherNatureIsland() {
        return islands.get(MotherNature.getPosition());
    }

    public void putTowerOnIsland(Tower tower) {
        islands.get(MotherNature.getPosition()).setTower(tower);
    }

    public List<Student> getStudentsOnCloud(int cloudIndex) {
        ///// controllo if(clouds[cloudIndex] == null) throw SomeTypeOfException("This cloud doesn't have students on it");
        return clouds[cloudIndex].getStudents();
    }

    ///// non mi ricordo cosa doveva fare control professor dunque l'ho lasciata così cambiando nome
    public void MoveProfessorToTheRightPosition() {

    }

    ///////da implemntare
    private int calculateInfluence(SchoolBoard schoolBoard) {
        int influence = 0;
        return influence;
    }
    ////// da implementare (secondo me non sono molto significativi così divisi)
    private SchoolBoard calculateGeneralInfluence() {
        SchoolBoard s = null;
        return s;
    }

}
