package org.example;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//
public class Main {
    public static void main(String[] args) {
            
        ArrayList<College> colleges = new ArrayList<>();
        String[] subjects = {"Agriculture", "Natural resources and conservation", "Architecture", "Area, ethnic, and gender studies", "Communication/journalism", "Computer and information sciences", "Education", "Engineering", "Foreign languages, literatures, and linguistics","Family and consumer sciences","English","Liberal arts/general studies","Biological/life sciences","Mathematics and statistics","Interdisciplinary studies","Philosophy and religious studies","Physical sciences","Psychology","Public administration and social services","Social sciences","Visual and performing arts","Health professions and related programs","Business/marketing","History"};
        String[] diversity = {"Nonresidents","Hispanic/Latino","Black or African American, non-Hispanic","White, non-Hispanic","American Indian or Alaska Native, non-Hispanic","Asian, non-Hispanic","Native Hawaiian or other Pacific Islander, non-Hispanic","Two or more races, non-Hispanic","Race and/or ethnicity unknown"};
        double[] diversityNums = {0.07,0.15,0.1,0.38,0.02,0.20,0.01,0.06,0.06};
        colleges.add(new College("yale",6645,62250,77,0.045,-4,0,0.33,0.7));
        colleges.add(new College("brown",7639,65146,58,0.051,-1,-4,0.12,0.63));
        colleges.add(new College("harvard",7240,57261,99,.0341,0,0,0.21,0.85));
        colleges.add(new College("dartmouth",4458,62658,58,0.0734,-2,-1,0.23,0.67));
        colleges.add(new College("princeton",5604,57410,63,0.0623,-5,-3,0.12,0.77));
        colleges.add(new College("cornell",15735,57410,56,0.057,-5,-3,0.12,0.66));
        colleges.add(new UserCollege("userCollege", 0,0,0,0,0,0,0,0));

        ((UserCollege)colleges.get(6)).chooseCollegeInfo();




        ArrayList<Student> students = new ArrayList<>();
        int numStudents = 30000;

        Random random = new Random();

        for (int i = 0; i < numStudents; i++) {
            int[] randomValues = new int[20];
            for (int j = 0; j < 20; j++) {
                randomValues[j] = random.nextInt(4); // Generates random values between 0 and 3
            }

            //simulate a semi-acurate diversity of an application pool
            String studentDiversity =  "Error";
            double count = 0;
            double rand = random.nextDouble();
            for (int j = 0; j < diversity.length; j++) {
                count += diversityNums[j];
                if(count > rand){
                    studentDiversity = diversity[j];
                }
            }

            // Add a student with random values and other specified parameters
            students.add(new Student(randomValues[0], randomValues[1], randomValues[2], randomValues[3],
                    randomValues[4], randomValues[5], randomValues[6], randomValues[7], randomValues[8],
                    randomValues[9], randomValues[10], randomValues[11], randomValues[12], randomValues[13],
                    randomValues[14], randomValues[15], randomValues[16], randomValues[17], randomValues[18],
                    1, 2024, colleges, subjects[random.nextInt(subjects.length)],studentDiversity));
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