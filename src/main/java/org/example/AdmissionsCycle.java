package org.example;
/*things this class needs from other classes to run the way it is now:
 * - Student should have a method getList to get each student's preference list
 * - College should have name field
 * - College should have method considerApplicants which takes in a list of applicants
 * and the round (ED vs RA), and returns a list of accepted students (for ED, method should
 * also enroll students)
 * - Student should have a method decide which takes in a list of colleges that accepted them
 * and returns the college they choose
 * - College should have a method enroll which enrolls a student, void
 * */
/*4/8 notes:
* - College has wealth as parameter
*  - College has methods getWealth and getEnrolledStudents
*  */

import java.util.ArrayList;
import java.util.HashMap;

public class AdmissionsCycle {
    private ArrayList<Student> students;
    private ArrayList<College> colleges;
    private String round;



    public AdmissionsCycle(ArrayList<Student> students, ArrayList<College> colleges){
        this.students = students;
        this.colleges = colleges;
        round = "ED";

    }
    /*
    public void earlyRound(){
       for(int i = 0; i<students.size(); i++){
           College college = students.get(i).getList.get(0);//the first college on a student's list
           Boolean decision = college.considerApplicant(students.get(i), round); // considerApplicant change to return boolean
           if (decision){
               college.getAttendingStudents.add(students.get(i));
               college.enroll(students.get(i)); //two ways to do this, or have it handled in college
               students.remove(students.get(i));
           }
       }
    }

     *//*
    public void earlyRound2(){ //all applicants passed into consideration
        ArrayList<Student> Aapplicants = new ArrayList<Student>();
        ArrayList<Student> Bapplicants = new ArrayList<Student>();
        ArrayList<Student> Capplicants = new ArrayList<Student>();
        ArrayList<Student> Dapplicants = new ArrayList<Student>();
        ArrayList<Student> Eapplicants = new ArrayList<Student>();
        ArrayList<Student> Fapplicants = new ArrayList<Student>();
        ArrayList<Student> Gapplicants = new ArrayList<Student>();
        ArrayList<Student> Happlicants = new ArrayList<Student>();

        for(int i = 0; i<students.size(); i++){
            College college = students.get(i).getList.get(0);//the first college on a student's list
            //adds each student to the applying list for that college
            switch(college.name.toUpperCase()){
                case "A":
                    Aapplicants.add(students.get(i));
                    break;
                case "B":
                    Bapplicants.add(students.get(i));
                    break;
                case "C":
                    Capplicants.add(students.get(i));
                    break;
                case "D":
                    Dapplicants.add(students.get(i));
                    break;
                case "E":
                    Eapplicants.add(students.get(i));
                    break;
                case "F":
                    Fapplicants.add(students.get(i));
                    break;
                case "G":
                    Gapplicants.add(students.get(i));
                    break;
                case "H":
                    Happlicants.add(students.get(i));
                    break;
            }
        }
        ArrayList<Student> Aattending = colleges.get(0).considerApplicants(Aapplicants, round); //method could return list of accepted students
        ArrayList<Student> Battending = colleges.get(1).considerApplicants(Bapplicants, round); //so that they can be taken out of consideration for other colleges
        ArrayList<Student> Cattending = colleges.get(2).considerApplicants(Capplicants, round);
        ArrayList<Student> Dattending = colleges.get(3).considerApplicants(Dapplicants, round);
        ArrayList<Student> Eattending = colleges.get(4).considerApplicants(Eapplicants, round);
        ArrayList<Student> Fattending = colleges.get(5).considerApplicants(Fapplicants, round);
        ArrayList<Student> Gattending = colleges.get(6).considerApplicants(Gapplicants, round);
        ArrayList<Student> Hattending = colleges.get(7).considerApplicants(Happlicants, round);
        //handle enrolling withing considerApplicants (ED is binding)

        students.removeAll(Aattending);
        students.removeAll(Battending);
        students.removeAll(Cattending);
        students.removeAll(Dattending);
        students.removeAll(Eattending);
        students.removeAll(Fattending);
        students.removeAll(Gattending);
        students.removeAll(Hattending);

        //update college for student (what college student is going to from student perspective)

    }*/
    public void earlyRound2() {
        HashMap<String, ArrayList<Student>> applicantsMap = new HashMap<>();

        // Initialize the applicants lists for each college
        for (char c = 'A'; c <= 'H'; c++) {
            applicantsMap.put(String.valueOf(c), new ArrayList<>());
        }

        // Group applicants based on their first college choice
        for (Student student : students) {
            if (!student.getList().isEmpty()) {
                College college = student.getList().get(0);
                String collegeName = college.getName().toUpperCase();
                ArrayList<Student> collegeApplicants = applicantsMap.get(collegeName);
                collegeApplicants.add(student);
            }
        }

        // Consider applicants for each college and remove attending students from the main list
        for (int i = 0; i < colleges.size(); i++) {
            String collegeName = String.valueOf((char) ('A' + i));
            ArrayList<Student> attendingStudents = colleges.get(i).considerApplicants(applicantsMap.get(collegeName), round);
            students.removeAll(attendingStudents);
        }

        // Update college for each attending student (if needed)
    }/*
    public void regularRound(){
        round = "RA";
        ArrayList<Student> Aapplicants = new ArrayList<Student>();
        ArrayList<Student> Bapplicants = new ArrayList<Student>();
        ArrayList<Student> Capplicants = new ArrayList<Student>();
        ArrayList<Student> Dapplicants = new ArrayList<Student>();
        ArrayList<Student> Eapplicants = new ArrayList<Student>();
        ArrayList<Student> Fapplicants = new ArrayList<Student>();
        ArrayList<Student> Gapplicants = new ArrayList<Student>();
        ArrayList<Student> Happlicants = new ArrayList<Student>();
        for(int i = 0; i<students.size(); i++) {
            for (int j = 1; j < 2; j++) { //<2 takes B, <3 takes B + C, number-1 = number of colleges
                College college = students.get(i).getList.get(j);//the next college on a student's list
                //adds each student to the applying list for that college
                switch (college.name.toUpperCase()) {
                    case "A":
                        Aapplicants.add(students.get(i));
                        break;
                    case "B":
                        Bapplicants.add(students.get(i));
                        break;
                    case "C":
                        Capplicants.add(students.get(i));
                        break;
                    case "D":
                        Dapplicants.add(students.get(i));
                        break;
                    case "E":
                        Eapplicants.add(students.get(i));
                        break;
                    case "F":
                        Fapplicants.add(students.get(i));
                        break;
                    case "G":
                        Gapplicants.add(students.get(i));
                        break;
                    case "H":
                        Happlicants.add(students.get(i));
                        break;
                }
            }
        }
        //returns list of accepted students
        ArrayList<Student> Aaccepted = colleges.get(0).considerApplicants(Aapplicants, round); //method could return list of accepted students
        ArrayList<Student> Baccepted = colleges.get(1).considerApplicants(Bapplicants, round); //so that they can be taken out of consideration for other colleges
        ArrayList<Student> Caccepted = colleges.get(2).considerApplicants(Capplicants, round);
        ArrayList<Student> Daccepted = colleges.get(2).considerApplicants(Capplicants, round);
        ArrayList<Student> Eaccepted = colleges.get(2).considerApplicants(Capplicants, round);
        ArrayList<Student> Faccepted = colleges.get(2).considerApplicants(Capplicants, round);
        ArrayList<Student> Gaccepted = colleges.get(2).considerApplicants(Capplicants, round);
        ArrayList<Student> Haccepted = colleges.get(2).considerApplicants(Capplicants, round);

        for(Student student: students){//for each student, creates list of colleges they got into
            ArrayList<College> acceptedColleges = new ArrayList<College>();
            if (Aaccepted.contains(student)){
                acceptedColleges.add(colleges.get(0));
            }
            if (Baccepted.contains(student)){
                acceptedColleges.add(colleges.get(1));
            }
            if (Caccepted.contains(student)){
                acceptedColleges.add(colleges.get(2));
            }
            if (Daccepted.contains(student)){
                acceptedColleges.add(colleges.get(3));
            }
            if (Eaccepted.contains(student)){
                acceptedColleges.add(colleges.get(4));
            }
            if (Faccepted.contains(student)){
                acceptedColleges.add(colleges.get(5));
            }
            if (Gaccepted.contains(student)){
                acceptedColleges.add(colleges.get(6));
            }
            if (Haccepted.contains(student)){
                acceptedColleges.add(colleges.get(7));
            }
            College attendingCollege = student.decide(acceptedColleges);
            //passes list of accepted colleges, returns their choice
            attendingCollege.enroll(student);
            //enrolls that student at that college
            students.remove(student);
        }



    }*/
    public void regularRound() {
        round = "RA";
        HashMap<String, ArrayList<Student>> applicantsMap = new HashMap<>();

        for (char c = 'A'; c <= 'H'; c++) {
            applicantsMap.put(String.valueOf(c), new ArrayList<>());
        }

        for (Student student : students) {
            for (int j = 1; j < 2 && j < student.getList().size(); j++) {
                College college = student.getList().get(j);
                String collegeName = college.getName().toUpperCase();
                ArrayList<Student> collegeApplicants = applicantsMap.get(collegeName);
                if (collegeApplicants != null) {
                    collegeApplicants.add(student);
                }
            }
        }

        ArrayList<ArrayList<Student>> acceptedLists = new ArrayList<>();
        for (int i = 0; i < colleges.size(); i++) {
            String collegeName = String.valueOf((char) ('A' + i));
            ArrayList<Student> accepted = colleges.get(i).considerApplicants(applicantsMap.get(collegeName), round);
            acceptedLists.add(accepted);
        }

        for (Student student : students) {
            ArrayList<College> acceptedColleges = new ArrayList<>();
            for (int i = 0; i < acceptedLists.size(); i++) {
                if (acceptedLists.get(i).contains(student)) {
                    acceptedColleges.add(colleges.get(i));
                }
            }
            College attendingCollege = student.decide(acceptedColleges);
            attendingCollege.enroll(student);
            students.remove(student);
        }
    }
    public void calculateWealth(){
        for (College college : colleges){
            college.getWealth.payTuition(college.getEnrolledStudents.size());
        }

    }



}

