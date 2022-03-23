package it.polimi.ingsw.Model;


import java.util.ArrayList;
import java.util.List;

public class GameTable {
    private int numberOfPlayers;
    private Player[] players;
    private List<Island> islands;
    private Cloud[] clouds;
    private SchoolBoard[] schoolBoards;
    private Assistant[] assistants; /////////non mi ricordo come li utilizzavamo quindi non ci saranno nei metodi
    private int motherNaturePosition;

    public GameTable(int numberOfPlayers, Player[] players) {
        // if(numberOfPlayers > 4 || numberOfPlayers <= 1;) throw new Exception();
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++)
            this.players[i] = players[i];

        List<Island> islands = new ArrayList<>();
        for(Island island : islands)
            island = new Island();

        int numberOfstudentsOnClouds;
        int numberOfStudentsOnEntrance;
        int numberOfTowers;
        if(numberOfPlayers == 3) {
            numberOfstudentsOnClouds = 4;
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 6;
        }else {
            numberOfstudentsOnClouds = 3;
            numberOfStudentsOnEntrance = 9;
            numberOfTowers = 8;
        }
        clouds = new Cloud[numberOfPlayers];
        for(Cloud cloud : clouds)
            cloud = new Cloud(numberOfstudentsOnClouds);

        schoolBoards = new SchoolBoard[numberOfPlayers];
        switch(numberOfPlayers) {
            case 2:
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                break;
            case 3:
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[3] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.GREY, numberOfTowers);
                break;
            case 4:
                schoolBoards[1] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, numberOfTowers);
                schoolBoards[2] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, numberOfTowers);
                schoolBoards[3] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.WHITE, 0);
                schoolBoards[4] = new SchoolBoard(numberOfStudentsOnEntrance, TowerColor.BLACK, 0);
                break;
        }
        for(int i = 0; i < numberOfPlayers; i++)
            players[i].addSchoolBoard(schoolBoards[i]);


        assistants = new Assistant[numberOfPlayers * 10]; /////va controllato
        ////inizializzazione degli assistant




        motherNaturePosition = 0;
    }

    public void addStudentsOnClouds() {
        for(int i = 0; i < numberOfPlayers; i++)
            clouds[i].addStudents(Bag.getBag().drawStudents(clouds[i].getNumberOfStudents()));
    }

    public void addStudentOnIsland(Student s, int islandIndex) {
        if(islandIndex >= 0 && islandIndex < islands.size())
            this.islands.get(islandIndex).addStudents(s);
    }
    public void changeMotherNaturePosition(int newPosition) {
        motherNaturePosition = newPosition;
    }

    /* The mothod put the towers on the island/islands where mother nature is. If the istand hasn't the tower
    * then metodo set it otherwise the method check if it has to change the color of the towers
     */
    public List<Tower> putTowersOnIslands(List<Tower> towers) {
        //dovrà essere precedentemente controllato che le torri siano tutte dello stesso colore
        if(islands.get(motherNaturePosition).getAggregation() != towers.size()) { } //exception
        if(islands.get(motherNaturePosition).getTower() == null)////////////////////////////////////////////////
            islands.get(motherNaturePosition).setTower(towers);
        else {
            if(islands.get(motherNaturePosition).getTower().get(0).getColor() != towers.get(0).getColor()) { } // exception
            if(islands.get(motherNaturePosition).getTower().get(0).getColor() != towers.get(0).getColor()) {
                return islands.get(motherNaturePosition).removeTower();
            }
        }
        return null;
        //islands.get(motherNaturePosition).setTower(towers);
    }

    public List<Student> getStudentsOnCloud(int cloudIndex) {
        ///// controllo if(clouds[cloudIndex] == null) throw SomeTypeOfException("This cloud doesn't have students on it");
        if(cloudIndex >= 0 && cloudIndex < clouds.length)
            return clouds[cloudIndex].getStudents();
        else
            return null;
    }

    ///// non mi ricordo cosa doveva fare control professor dunque l'ho lasciata così cambiando nome
    public void MoveProfessorToTheRightPosition() {

    }

    ///////da implemntare
    private int calculateInfluence() {
        int[] influence = new int[numberOfPlayers];
        List<Student> studentsOnTheIslands = islands.get(motherNaturePosition).getStudents();
        List<Tower> towersOnTheIslands = islands.get(motherNaturePosition).getTower();
        influence[0] = 0;
        for(int el : influence) {

        }

        return influence[0];
    }
    ////// da implementare (secondo me non sono molto significativi così divisi)
    private SchoolBoard calculateGeneralInfluence() {
        SchoolBoard s = null;
        return s;
    }
}