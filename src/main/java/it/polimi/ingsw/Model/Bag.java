package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random; // Controlla quale è la libreria standard

public class Bag {
    private static Bag bag;
    private List<Student> students;

    private Bag() {
        students = new ArrayList<Student>();
        for(int i = 0; i < 26 ; i++) {
            students.add(new Student(PawnColor.YELLOW));
            students.add(new Student(PawnColor.BLUE));
            students.add(new Student(PawnColor.GREEN));
            students.add(new Student(PawnColor.RED));
            students.add(new Student(PawnColor.PINK));
        }
    }

    public List<Student> drawStudents(int numberOfStudents) {
        List<Student> drawnStudents = new ArrayList<>();
        for(int i = 0; i < numberOfStudents; i++) {
            int index = new Random().nextInt(students.size());
            drawnStudents.add(students.remove(index));
        }
        return drawnStudents;
    }

    public static Bag getBag() {
        if(bag == null) bag = new Bag();
        return bag;
    }
}