package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Cloud {
    private final int numberOfStudents;
    private final List<Student> students;

    /**
     * Creates a cloud that can contain up to the number of students passed as parameter
     * @param numberOfStudents maximum amount of student the cloud can contain
     */
    public Cloud(int numberOfStudents) {
        this.students = new ArrayList<>();
        this.numberOfStudents = numberOfStudents;
    }

    /**
     * Returns the maximum number of students the cloud can contain
     * @return maximum amount of student the cloud can contain
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    /**
     * Returns the list of students which is on the cloud
     * @return students on the cloud
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Return a value which is true if there are no students on the cloud and false if there are some students on it
     * @return true to notify the cloud is empty
     */
    public boolean isEmpty() {
        return students.size() <= 0;
    }

    /**
     * Removes all the students on the cloud
     * @return list of students on the cloud
     */
    public List<Student> removeStudents() {
        List<Student> removedStudents = new ArrayList<>(students);
        students.clear();
        return removedStudents;
    }

    /**
     * Adds the students in the list passed as parameter on the cloud
     * @param newStudents students to put on the cloud
     */
    public void addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
    }
}
