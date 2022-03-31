package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdvanced extends Player{
    private GameTableAdvanced gameTable;
    private SchoolboardAdvanced schoolBoard;

    public PlayerAdvanced(String nickName) {
        super(nickName);
    }

    public void addGameTable(GameTableAdvanced gameTableAdvanced){
        if(gameTableAdvanced != null)
            this.gameTable = gameTableAdvanced;
    }
    public void addSchoolBoard(SchoolboardAdvanced schoolBoard){
        if(schoolBoard != null)
            this.schoolBoard = schoolBoard;
    }

    public void effectActivation(int indexCard, List<Integer> studentsIndex, List<Integer> studentsIndexEntrance, int islandIndex, List<Integer> indexTable, String color) throws EmptyBagException{
        try{
            int ID = this.gameTable.getIDCharacter(indexCard);
            int cost = this.gameTable.getCostCharacter(indexCard);

            //se ho abbastanza coin per la carta eseguo l'effetto
            if(cost <= this.schoolBoard.getNumberOfCoins()) {
                this.schoolBoard.removeCoins(cost);
                this.gameTable.addCoins(cost);
                if(!this.gameTable.getCharacter(indexCard).getFirstUse()) this.gameTable.getCharacter(indexCard).setFirstUse();

                switch (ID) {
                    case 1:
                        List<Student> s = new ArrayList<>(this.gameTable.getStudentFromCard(indexCard, studentsIndex));
                        this.gameTable.addStudentOnIsland(s.remove(0), islandIndex);
                        break;
                    case 3:
                        //NON FUNZIONE
                        //vedere se viene modificata gameTable e se serve un metodo per il cambio di posizione
                        int lastMotherNaturePosition = this.gameTable.getMotherNaturePosition();
                        this.gameTable.changeMotherNaturePosition(islandIndex);
                        //richiamo a calculateInfluece
                        this.gameTable.changeMotherNaturePosition(lastMotherNaturePosition);
                        break;
                    case 6:
                        DecoratedGameTableAdvancedNoTowerInfluence decoratedGameTableNoTower = new DecoratedGameTableAdvancedNoTowerInfluence(this.gameTable);
                        this.addGameTable(decoratedGameTableNoTower);
                        break;
                    case 7:
                        if(studentsIndex.size() <= 3 && studentsIndexEntrance.size() <= 3 && studentsIndexEntrance.size() == studentsIndex.size()){
                            List<Student> newStudentsEntrance = new ArrayList<>(this.gameTable.getStudentFromCard(indexCard, studentsIndex));
                            List<Student> newStudentsCard = new ArrayList<>();
                            for(Integer i: studentsIndexEntrance) newStudentsCard.add(this.schoolBoard.removeStudentFromEntrance(i));
                            this.gameTable.addStudentsOnCard(indexCard, newStudentsCard);
                            this.schoolBoard.addStudentsOnEntrance(newStudentsEntrance);
                        }else throw new InvalidNumberException("Error effect 7");
                        break;
                    case 8:
                        //INDICE ERRATO IN INPUT AL METODO. Capire se mettere un parametro o spostare tutto su game
                        DecoratedGameTableAdvancedMoreInfluece decoratedGameTableMoreInfluence = new DecoratedGameTableAdvancedMoreInfluece(this.gameTable, 0);
                        this.addGameTable(decoratedGameTableMoreInfluence);
                        break;
                    case 10:
                        if(indexTable.size() <= 2 && indexTable.size() > 0 && studentsIndexEntrance.size() <= 2){
                            List<Student> newStudentsEntrance = new ArrayList<>();
                            //estrae gli studenti dai tavoli selezionati
                            if(indexTable.size() == 1){
                                if(studentsIndexEntrance.size() == 1){
                                    newStudentsEntrance.add(this.schoolBoard.tables[indexTable.get(0)].removeStudentTable());
                                }else{
                                    for(int j = 0; j < 2; j++){
                                        newStudentsEntrance.add(this.schoolBoard.tables[indexTable.get(0)].removeStudentTable());
                                    }
                                }
                            }else{
                                for(Integer i: indexTable){
                                    newStudentsEntrance.add(this.schoolBoard.tables[i].removeStudentTable());
                                }
                            }
                            //mette gli studenti selezionati dalla entrance ai tavoli
                            for(Integer i: studentsIndexEntrance){
                                this.schoolBoard.addStudentOnTable(i);
                            }
                            //mette li studenti nei tavoli nella entrance
                            for(Student st: newStudentsEntrance){
                                this.schoolBoard.addStudentsOnEntrance(newStudentsEntrance);
                                if(this.schoolBoard.getAddCoin() && this.gameTable.getCoins() > 0){
                                    this.gameTable.removeCoin();
                                    this.schoolBoard.addCoin();
                                }
                            }
                        }
                        break;
                    case 11:
                        if(studentsIndex.size() == 1){
                            //da ricontrollare
                            List<Student> newStudent = new ArrayList<>(this.gameTable.getStudentFromCard(indexCard, studentsIndex));
                            this.schoolBoard.addStudentOnTableFromCard(newStudent.remove(0));
                            if(this.schoolBoard.getAddCoin() && this.gameTable.getCoins() > 0){
                                this.gameTable.removeCoin();
                                this.schoolBoard.addCoin();
                            }
                            this.gameTable.addStudentsOnCard(indexCard, Bag.getBag().drawStudents(1));
                        }
                        break;
                    case 12:
                        int tableIndex = -1;
                        for(PawnColor c: PawnColor.values()){
                            if(c.toString().equals(color)) tableIndex = c.getIndex();
                        }
                        if(tableIndex == -1) throw new InvalidNumberException("Error color effect");
                        else{
                            List<Student> newStudentsBag = new ArrayList<>();
                            for(int i = 0; i < 3; i++){
                                newStudentsBag.add(this.schoolBoard.tables[tableIndex].removeStudentTable());
                            }
                            Bag.getBag().addStudents(newStudentsBag);
                        }
                        break;
                }
            }
        }catch(InvalidNumberException e){
            e.printStackTrace();
        }


    }

}