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
* 4/13 notes:
*  - don't have any sort of consideration of financial aid in calculating weath
*  - thinking about calculating money. the calculateWealth method only calculates the expected
* wealth gained in one year. i think it might be useful to have a method which calulates the
* whole expected wealth gain for one specific class of students, as students and then as
* alums, so the impact of each accepted class can be seen more easily.
*  - just a note about running it, each time you want a new admissionsCycle, so each year,
* you have to initialize a new instance of AdmissionsCycle, you can't just use the same one
* year to year, because the year field needs to be updated.
*  - for the wealth class, using the acceptance rate to calculate public image, is based
* off of how much the current acceptance rate differs from the college's initial acceptance rate.
* if it is lower, they get more public image, if it is higher, less public image. in order to do
* this, the program needs to know the initial acceptance rate, which would just be the real world
* acceptance rate. to get this info into the program, store the college's real world acceptance
* rate as year 0 in the acceptanceRate hashmap?
* */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class AdmissionsCycle {
    private ArrayList<Student> students;
    private ArrayList<College> colleges;
    private String round;
    private int year;



    public AdmissionsCycle(ArrayList<Student> students, ArrayList<College> colleges, int year){
        this.students = students;
        this.colleges = colleges;
        round = "ED";
        this.year = year;

    }



    public void earlyRound2() {
        round = "ED";
        HashMap<String, ArrayList<Student>> applicantsMap = new HashMap<>();

        // Initialize the applicants lists for each college
        for (char c = 'A'; c <= 'F'; c++) {
            applicantsMap.put(String.valueOf(c), new ArrayList<>());
        }

        // Group applicants based on their first college choice
        for (Student student : students) {
            if (!student.getList().isEmpty()) {
                College college = student.getList().get(0);
                String collegeName = college.name.toUpperCase();
                ArrayList<Student> collegeApplicants = applicantsMap.get(collegeName);
                collegeApplicants.add(student);
            }
        }

        // Consider applicants for each college and remove attending students from the main list
        for (int i = 0; i < colleges.size(); i++) {
            String collegeName = String.valueOf((char) ('A' + i));
            ArrayList<Student> attendingStudents = colleges.get(i).admissions.considerApplicants(applicantsMap.get(collegeName), round);
            students.removeAll(attendingStudents);
        }

        // Update college for each attending student (if needed)
    }

    public void regularRound() {
        round = "RA";
        HashMap<String, ArrayList<Student>> applicantsMap = new HashMap<>();

        for (char c = 'A'; c <= 'F'; c++) {
            applicantsMap.put(String.valueOf(c), new ArrayList<>());
        }

        for (Student student : students) {
            for (int j = 1; j < 2 && j < student.getList().size(); j++) { //how many colleges to apply to in ED round?
                College college = student.getList().get(j);
                String collegeName = college.name.toUpperCase();
                ArrayList<Student> collegeApplicants = applicantsMap.get(collegeName);
                if (collegeApplicants != null) {
                    collegeApplicants.add(student);
                }
            }
        }

        ArrayList<ArrayList<Student>> acceptedLists = new ArrayList<>();
        for (int i = 0; i < colleges.size(); i++) {
            String collegeName = String.valueOf((char) ('A' + i));
            ArrayList<Student> accepted = colleges.get(i).admissions.considerApplicants(applicantsMap.get(collegeName), round);
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
    public void calculateWealthPerYear(){
        for (College college : colleges){

            college.getWealth().payTuition(college.getAttendingStudents().size());
            college.getWealth().updatePubIm(college.getAttendingStudents(), year, college.admissions.getAcceptanceRate());
            college.getWealth().receiveDonations(college.getAlumni());
        }

    }
    public void calculateWealthPerClass(int classYear){
        //HashMap<String, ArrayList<Student>> classOf_ = new HashMap<>();
        for (College college : colleges){//makes a list of all the students of one singular class year
            ArrayList<Student> sameYearStudents = new ArrayList<>();
            for(Student student : college.getAttendingStudents()){
                if (student.getHashMap().get("Application Year") == classYear){
                    sameYearStudents.add(student);
                }
            }
            //classOf_.put(college.name, sameYearStudents);

            for(int i=0;i<4;i++){//pays tuition for all four years of that class
                college.getWealth().payTuition(sameYearStudents.size());
            }
            college.getWealth().updatePubIm(college.getAttendingStudents(), classYear, college.admissions.getAcceptanceRate());
            college.getWealth().receiveClassCumulativeDonations(sameYearStudents);
        }
    }



}

