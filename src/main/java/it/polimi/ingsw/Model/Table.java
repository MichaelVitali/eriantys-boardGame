package it.polimi.ingsw.Model;

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
}
