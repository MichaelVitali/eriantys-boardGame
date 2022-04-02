
/*///////// dovrebbe essere inutile ora
package it.polimi.ingsw.Model;

public class SchoolboardAdvanced extends SchoolBoard{
    private int coins;
    private boolean addCoin;

    public SchoolboardAdvanced(int numberOfStudentsOnEntrance, TowerColor schoolBoardTowerColor, int numberOfTowers) {
        super(numberOfStudentsOnEntrance, schoolBoardTowerColor, numberOfTowers);
        this.coins = 1;
        this.addCoin = false;
    }

    @Override
    public void addStudentOnTable(int index) {
        // if(index >= 0 && index < numberOfStudentsOnEntrance)
        tables[entrance[index].getColor().getIndex()].addStudent(entrance[index]);
        int pos = tables[entrance[index].getColor().getIndex()].getLastStudentPosition();
        if(pos % 3 == 0) this.addCoin = true;
        entrance[index] = null; // valutare se l'utilizzo del null va bene
    }
//////
    public void addStudentOnTableFromCard(Student s){
        this.tables[s.getColor().getIndex()].addStudent(s);
        int pos = tables[s.getColor().getIndex()].getLastStudentPosition();
        if(pos % 3 == 0) this.addCoin = true;
    }
/////
    public void removeCoins(int numberOfCoins){
        if((this.coins - numberOfCoins) >= 0) this.coins -= numberOfCoins;
    }
/////
    public int getNumberOfCoins(){
        return this.coins;
    }
/////
    public void addCoin(){
        this.coins++;
        this.addCoin = true;
    }
//////
    public void setAddCoinFalse(){
        this.addCoin = false;
    }
/////
    public boolean getAddCoin(){
        return this.addCoin;
    }
}
*/