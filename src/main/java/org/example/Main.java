package org.example;
import java.util.ArrayList;
import java.util.HashMap;

//
public class Main {
    public static void main(String[] args) {
            
        ArrayList<College> colleges = new ArrayList<>();
        colleges.add(new College("yale",400,2000000,30,0.04,-4,0,0.33));
        colleges.add(new College("brown",500,1000000,25,0.04,-1,-4,0.12));
        colleges.add(new College("harvard",600,100000,24,25,0,0,0.21));
        colleges.add(new College("dartmouth",9,2000000,20,0.04,-2,-1,0.23));
        colleges.add(new College("princeton",4000,2000000,19,0.04,-5,-3,0.12));
        colleges.add(new College("cornell",2000,2000000,18,0.04,-6,0,0.98));

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(1,2,3,1,2,1,3,0,1,2,3,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));
        students.add(new Student(2,3,1,2,0,2,3,1,2,1,0,1,2,0,0,1,2,3,2,1,2024,colleges,"English"));


        AdmissionsCycle cycle1 = new AdmissionsCycle(students, colleges, 1);
        cycle1.earlyRound();
        cycle1.regularRound();
        cycle1.calculateWealthPerClass(1);



    }
}