package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//
public class Main {
    public static void main(String[] args) {
            
        ArrayList<College> colleges = new ArrayList<>();
        String[] subjects = {"Agriculture","Natural resources and conservation","Architecture","Area, ethnic, and gender studies","Communication/journalism","Communication technologies","Computer and information sciences","Personal and culinary services","Education","Engineering","Engineering technologies","Foreign languages, literatures, and linguistics"};

        colleges.add(new College("yale",100,62250,30,0.01,-4,0,0.33));
        colleges.add(new College("brown",100,65146,25,0.04,-1,-4,0.12));
        colleges.add(new College("harvard",100,57261,24,.25,0,0,0.21));
        colleges.add(new College("dartmouth",100,62658,20,0.04,-2,-1,0.23));
        colleges.add(new College("princeton",100,57410,19,0.04,-5,-3,0.12));
        colleges.add(new College("cornell",100,57410,22,0.04,-5,-3,0.12));
        colleges.add(new UserCollege("UserCollege", 0,0,0,0,0,0,0));

        ((UserCollege)colleges.get(6)).chooseCollegeInfo();



        ArrayList<Student> students = new ArrayList<>();
        int numStudents = 1000;

        Random random = new Random();

        for (int i = 0; i < numStudents; i++) {
            int[] randomValues = new int[20];
            for (int j = 0; j < 20; j++) {
                randomValues[j] = random.nextInt(4); // Generates random values between 0 and 3
            }

            // Add a student with random values and other specified parameters
            students.add(new Student(randomValues[0], randomValues[1], randomValues[2], randomValues[3],
                    randomValues[4], randomValues[5], randomValues[6], randomValues[7], randomValues[8],
                    randomValues[9], randomValues[10], randomValues[11], randomValues[12], randomValues[13],
                    randomValues[14], randomValues[15], randomValues[16], randomValues[17], randomValues[18],
                    1, 2024, colleges, subjects[random.nextInt(subjects.length)]));
        }


        AdmissionsCycle cycle1 = new AdmissionsCycle(students, colleges, 1);
        cycle1.earlyRound();
        cycle1.regularRound();
        cycle1.calculateWealthPerClass(1);
        //this does indeed calculate donations, but they are in most cases unfortunatly negative
        //cycle1.calculateWealthPerYear(); //this doesn't calculate donations, because there are no almuni yet
        ArrayList<College> rankedColleges= cycle1.orderCollegesByMoney();
        for (College college : rankedColleges){
            System.out.println(college.name);
        }



    }
}