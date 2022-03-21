package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

// Non ci sono le relazioni con  GameTable e Player

public class SchoolBoard {
    private int numberOfStudentsOnEntrance;
    private Student entrance[];
    private final int NUMBEROFTABLES = 5;
    private Table tables[];
    private int numberOfTowers;
    private List<Tower> towers;

    public SchoolBoard(int numberOfStudentsOnEntrance, TowerColor shoolBoardTowerColor, int numberOfTowers) {
        this.numberOfStudentsOnEntrance = numberOfStudentsOnEntrance;
        entrance = new Student[this.numberOfStudentsOnEntrance];
        for(int i = 0; i < this.numberOfStudentsOnEntrance; i++) { // ho collegato schoolboard con Bag
            entrance[i] = Bag.getBag().drowStudent();
        }

        tables = new Table[NUMBEROFTABLES];
        for(PawnColor tableColor : PawnColor.values())
            tables[tableColor.getIndex()] = new Table(tableColor);

        this.numberOfTowers = numberOfTowers;
        towers = new ArrayList<>();
        for(int i = 0; i < this.numberOfTowers; i++) {
            towers.add(new Tower(shoolBoardTowerColor));
        }
    }

    public void addStudentOnTable(int index) {
        // if(index >= 0 && index < numberOfStudentsOnEntrance)
        tables[entrance[index].getColor().getIndex()].addStudent(entrance[index]);
        entrance[index] = null; // valutere se l'utilizzo del null va bene
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

    public Tower removeTower() {
        return towers.remove(towers.size() - 1);
    }

    // Ho chiamato giveStudent removeStudentFromEntrance perchè mi sembrava più esplicativo
    public Student removeStudentFromEntrance(int index) {
        // eventuale controllo if(entrance[index] == null) throw new SomeTypeOfException("You're removing a null pointer and not a real student");
        Student studentToBeRemoved = entrance[index];
        entrance[index] = null;
        return studentToBeRemoved;
    }

    public int getNumberOfStudentsOnTable(PawnColor tableColor) {
        return tables[tableColor.getIndex()].getNumberOfStudents();
    }
}
