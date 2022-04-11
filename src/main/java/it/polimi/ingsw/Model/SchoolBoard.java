package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.exception.EmptyBagException;
import it.polimi.ingsw.Model.exception.InvalidIndexException;
import it.polimi.ingsw.Model.exception.NoMoreTowersException;

import java.util.ArrayList;
import java.util.List;

public class SchoolBoard {
    protected final int numberOfStudentsOnEntrance;
    protected final Student[] entrance;
    protected final int numberOfTables = 5;
    protected final Table[] tables;
    protected final int numberOfTowers;
    protected final TowerColor towersColor;
    protected final List<Tower> towers;

    public SchoolBoard(int numberOfStudentsOnEntrance, TowerColor schoolBoardTowerColor, int numberOfTowers) {
        this.numberOfStudentsOnEntrance = numberOfStudentsOnEntrance;
        entrance = new Student[this.numberOfStudentsOnEntrance];
        try {
            List<Student> drawnStudents = Bag.getBag().drawStudents(numberOfStudentsOnEntrance);
            for (int i = 0; i < numberOfStudentsOnEntrance; i++) {
                entrance[i] = drawnStudents.get(i);
            }
        }catch(EmptyBagException e) {
            e.printStackTrace();
        }

        tables = new Table[numberOfTables];
        for(PawnColor tableColor : PawnColor.values())
            tables[tableColor.getIndex()] = new Table(tableColor);

        this.numberOfTowers = numberOfTowers;
        towersColor = schoolBoardTowerColor;
        towers = new ArrayList<>();
        for(int i = 0; i < this.numberOfTowers; i++) {
            towers.add(new Tower(schoolBoardTowerColor));
        }
    }

    public void addStudentOnTable(int index) {
        tables[entrance[index].getColor().getIndex()].addStudent(entrance[index]);
        entrance[index] = null;
    }

    public void addStudentOnTable(Student s) {
        this.tables[s.getColor().getIndex()].addStudent(s);
    }

    public void addStudentsOnEntrance(List<Student> newStudents) {
        // potremmo creare una eccezione che notifica che il numero di studenti passato sia sbagliato
        int freePositions = 0;
        for(Student student : entrance) {
            if(student == null)
                freePositions++;
        }
        // if(freePositions != newStudents.size()) throw new SomeTypeOfException("Too many students to add on entrance");
        for(int entranceIndex = 0; entranceIndex < entrance.length; entranceIndex++){
            if(entrance[entranceIndex] == null){
                entrance[entranceIndex] = newStudents.remove(0);
            }
        }
    }

    public List<PawnColor> getProfessors() {
        List<PawnColor> professors = new ArrayList<>();
        for(PawnColor color : PawnColor.values()) {
            if(tables[color.getIndex()].hasProfessor()) {
                professors.add(color);
            }
        }
        return professors;
    }

    public void setProfessor(PawnColor color, boolean value) {
        tables[color.getIndex()].setProfessor(value);
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Tower> removeTowers(int numberOfTowers) throws InvalidIndexException, NoMoreTowersException {
        if(numberOfTowers < 0) throw new InvalidIndexException("Numero non valido");
        if(numberOfTowers >= towers.size()) throw new NoMoreTowersException(towersColor); // Vedremo se mandare le torri tramite exception
        List<Tower> removedTowers = new ArrayList<>();
        for(int i = 0; i < numberOfTowers; i++)
            removedTowers.add(towers.remove(0));
        return removedTowers;
    }

    public void addTowers(List<Tower> towerToAdd) {
        if (towers.size() < 8 && (towerToAdd.size() + towers.size() <= 8))  towers.addAll(towerToAdd);
    }

    public Student removeStudentFromEntrance(int index) throws InvalidIndexException{
        if (entrance[index] == null) throw new InvalidIndexException("No student on entrance position");
        if(index < 0 || index >= entrance.length) throw new InvalidIndexException("There isn't a student on this position");
        else{
            Student studentToBeRemoved = entrance[index];
            entrance[index] = null;
            return studentToBeRemoved;
        }
    }

    public int getNumberOfStudentsOnTable(PawnColor tableColor) {
        return tables[tableColor.getIndex()].getNumberOfStudents();
    }

    public TowerColor getTowersColor() {
        return towersColor;
    }

    public Student[] getStudentsFromEntrance(){
        Student[] returnEntrance = new Student[numberOfStudentsOnEntrance];
        System.arraycopy(entrance, 0, returnEntrance, 0, numberOfStudentsOnEntrance);
        return returnEntrance;
    }

}
