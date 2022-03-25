package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class SchoolBoard {
    private final int numberOfStudentsOnEntrance;
    private final Student[] entrance;
    private final int numberOfTables = 5;
    private final Table[] tables;
    private final int numberOfTowers;
    private final TowerColor towersColor;
    private final List<Tower> towers;

    public SchoolBoard(int numberOfStudentsOnEntrance, TowerColor schoolBoardTowerColor, int numberOfTowers) {
        this.numberOfStudentsOnEntrance = numberOfStudentsOnEntrance;
        entrance = new Student[this.numberOfStudentsOnEntrance];
        List<Student> drawnStudents = Bag.getBag().drawStudents(numberOfStudentsOnEntrance);
        for(int i = 0; i < this.numberOfStudentsOnEntrance; i++) { // ho collegato school board con Bag
            entrance[i] = drawnStudents.get(i);
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
        // if(index >= 0 && index < numberOfStudentsOnEntrance)
        tables[entrance[index].getColor().getIndex()].addStudent(entrance[index]);
        entrance[index] = null; // valutare se l'utilizzo del null va bene
    }

    public void addStudentsOnEntrance(List<Student> newStudents) {
        // potremmo creare una eccezione che notifica che il numero di studenti passato sia sbagliato
        int freePositions = 0;
        for(Student student : entrance) {
            if(student == null)
                freePositions++;
        }
        // if(freePositions != newStudents.size()) throw new SomeTypeOfException("Too many students to add on entrance");
        int entranceIndex = 0, newStudentsIndex = 0;
        while(entranceIndex < numberOfStudentsOnEntrance) {
            if(entrance[entranceIndex] == null) {
                entrance[entranceIndex] = newStudents.get(newStudentsIndex);
                newStudentsIndex++;
            }
            entranceIndex++;
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

    public List<Tower> removeTowers(int numberOfTowers) throws InvalidNumberException, NoMoreTowersException{
        if(numberOfTowers < 0) throw new InvalidNumberException("Numero non valido");
        if(numberOfTowers > towers.size()) throw new NoMoreTowersException(towersColor);
        List<Tower> removedTowers = new ArrayList<>();
        for(int i = 0; i < numberOfTowers; i++)
            removedTowers.add(towers.get(0));
            towers.remove(towers.get(0));
        return removedTowers;
    }

    public void addTowers(List<Tower> towerToAdd) {
        towers.addAll(towerToAdd);
    }

    // Ho chiamato giveStudent removeStudentFromEntrance perché mi sembrava più esplicativo
    public Student removeStudentFromEntrance(int index) {
        // eventuale controllo if(entrance[index] == null) throw new SomeTypeOfException("You're removing a null pointer and not a real student");
        if(index >= 0 && index < entrance.length) {
            Student studentToBeRemoved = entrance[index];
            entrance[index] = null;
            return studentToBeRemoved;
        }else{
            return null;
        }
    }

    public int getNumberOfStudentsOnTable(PawnColor tableColor) {
        return tables[tableColor.getIndex()].getNumberOfStudents();
    }
}
