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
        // mix();
    }

    // Eventuale metodo per mescolare la lista - anche se comunque la pescata è casuale
    private void mix() {}

    public Student drowStudent() {
        int index = new Random().nextInt(students.size());
        Student drawnStudent = students.remove(index);
        return drawnStudent;
    }

    public static Bag getBag() {
        if(bag == null) bag = new Bag();
        return bag;
    }
}