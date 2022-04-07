package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exception.EmptyTableException;

public class Table {
    private final PawnColor color;
    private final int NUMBEROFSEATS = 10; /////////
    private final Student[] students;
    private boolean professor;

    public Table(PawnColor color) {
        this.color = color;
        students = new Student[NUMBEROFSEATS];
        professor = false;
    }

    public int getNumberOfStudents() {
        int i;
        for(i = 0; i < NUMBEROFSEATS ; i++)
            if(students[i] == null) break;
        return i;
    }

    public PawnColor getColor() {
        return color;
    }

    public void setProfessor(boolean value) {
        this.professor = value;
    }

    public boolean hasProfessor() {
        return professor;
    }

    public void addStudent(Student newStudent) {
        for(int i = 0; i < NUMBEROFSEATS; i++){
            if(students[i] == null) {
                students[i] = newStudent;
                break;
            }
        }
    }

    //restituisce la posizione dell'ultimo studente aggiunto per l'aggiunta del coin
    /*public int getLastStudentPosition(){
        for(int i = 0; i < students.length; i++){
            if(this.students[i] == null) return --i;
        }
        return 0;
    }*/

    public Student removeStudentFromTable() throws EmptyTableException {
        Student s = null;
        if(getNumberOfStudents() <= 0) throw new EmptyTableException();
        for(int i = 0; i < NUMBEROFSEATS; i++){
            if(this.students[i] == null){
                s = this.students[--i];
                this.students[++i] = null; // non dovrebbe esserci i ?
                break;
            }
        }
        return s;
    }

}
