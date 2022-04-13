package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.*;

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

    /**
     * Creates a school board
     * @param numberOfStudentsOnEntrance maximum number of student the entrance of the school board cn contain
     * @param schoolBoardTowerColor color of the school board. Every tower of the school board will have this color
     * @param numberOfTowers number of towers on the initial school board configuration
     */
    public SchoolBoard(int numberOfStudentsOnEntrance, TowerColor schoolBoardTowerColor, int numberOfTowers) {
        this.numberOfStudentsOnEntrance = numberOfStudentsOnEntrance;
        entrance = new Student[this.numberOfStudentsOnEntrance];

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

    /**
     * Adds a student on the table of his color
     * @param index index of entrance where is placed the student which has to be moved
     * @throws FullTableException if the table of the student's color has no empty seats
     */
    public void addStudentOnTable(int index) throws FullTableException {
        if(index >= 0 && index < numberOfStudentsOnEntrance) {}//throw new InvalidIndexException("");
        tables[entrance[index].getColor().getIndex()].addStudent(entrance[index]);
        entrance[index] = null;
    }

    /**
     * Adds a student on the table of his color
     * @param student student which has to be put on the table
     * @throws FullTableException if the table of the student's color has no empty seats
     */
    public void addStudentOnTable(Student student) throws FullTableException {
        this.tables[student.getColor().getIndex()].addStudent(student);
    }

    /**
     * Removes a student from a table
     * @param color color of the student to remove
     * @return removed student
     * @throws EmptyTableException if there are no more student on the table, the table is empty
     */
    public Student removeStudentFromTable(PawnColor color) throws EmptyTableException {
        if(tables[color.getIndex()].getNumberOfStudents() <= 0) throw new EmptyTableException();
        return tables[color.getIndex()].removeStudentFromTable();
    }

    /**
     * Removes a student from a table
     * @param tableIndex index of the table from which it has to be removed a student
     * @return removed student
     * @throws EmptyTableException if there are no more student on the table, the table is empty
     */
    public Student removeStudentFromTable(int tableIndex) throws EmptyTableException {
        if(tables[tableIndex].getNumberOfStudents() <= 0) throw new EmptyTableException();
        return tables[tableIndex].removeStudentFromTable();
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

    public int getNumberOfStudentsOnEntrance(){
        return numberOfStudentsOnEntrance;
    }

}
