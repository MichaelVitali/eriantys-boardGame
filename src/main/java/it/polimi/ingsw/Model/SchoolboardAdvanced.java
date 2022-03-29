package it.polimi.ingsw.Model;

public class SchoolboardAdvanced extends SchoolBoard{
    private int coins;

    public SchoolboardAdvanced(int numberOfStudentsOnEntrance, TowerColor schoolBoardTowerColor, int numberOfTowers) {
        super(numberOfStudentsOnEntrance, schoolBoardTowerColor, numberOfTowers);
        this.coins = 1;
    }

    public void addStudentOnTable(int index) {
        // if(index >= 0 && index < numberOfStudentsOnEntrance)
        tables[entrance[index].getColor().getIndex()].addStudent(entrance[index]);
        int pos = tables[entrance[index].getColor().getIndex()].getLastStudentPosition();
        if(pos % 3 == 0) this.coins++;
        entrance[index] = null; // valutare se l'utilizzo del null va bene
    }

    public void addStudentOnTableFromCard(Student s){
        this.tables[s.getColor().getIndex()].addStudent(s);
        int pos = tables[s.getColor().getIndex()].getLastStudentPosition();
        if(pos % 3 == 0) this.coins++;
    }

    public void removeCoins(int numberOfCoin){
        if((this.coins - numberOfCoin) >= 0) this.coins -= numberOfCoin;
    }

    public int getNumberOfCoins(){
        return this.coins;
    }


}
