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



    public void earlyRound() {
        round = "ED";
        HashMap<String, ArrayList<Student>> applicantsMap = new HashMap<>();

        // Initialize the applicants lists for each college
        for (College college: colleges){
            applicantsMap.put(college.name, new ArrayList<>());
        }


        // Group applicants based on their first college choice
        for (Student student : students) {
            if (!student.getList().isEmpty()) {
                College college = student.getList().get(0);
                String collegeName = college.name;
                ArrayList<Student> collegeApplicants = applicantsMap.get(collegeName);
                collegeApplicants.add(student);
            }
        }

        // Consider applicants for each college and remove attending students from the main list
        for (College college: colleges) {
            String collegeName = college.name;
            ArrayList<Student> attendingStudents = college.admissions.considerApplicants(applicantsMap.get(collegeName), round);
            for (Student student: attendingStudents){
                college.enroll(student);
            }
            students.removeAll(attendingStudents);
        }
    }

    public void regularRound() {
        round = "RA";
        HashMap<String, ArrayList<Student>> applicantsMap = new HashMap<>();

        // Initialize the applicants lists for each college
        for (College college: colleges){
            applicantsMap.put(college.name, new ArrayList<>());
        }



        //add students applying to each school to a list of applicants
        for (Student student : students) {
            for (int j = 1; j < 7 && j < student.getList().size(); j++) { //how many colleges to apply to in ED round?
                College college = student.getList().get(j);
                String collegeName = college.name;
                ArrayList<Student> collegeApplicants = applicantsMap.get(collegeName);
                if (collegeApplicants != null) {
                    collegeApplicants.add(student);
                }
            }
        }

        //collects a list of each list of accepted students
        ArrayList<ArrayList<Student>> acceptedLists = new ArrayList<>();
        for (College college : colleges) {
            String collegeName = college.name;
            ArrayList<Student> accepted = college.admissions.considerApplicants(applicantsMap.get(collegeName), round);
            acceptedLists.add(accepted);
            //System.out.println("this many got accepted: " + accepted.size());
        }

        //each student receives a list of their accepted colleges and decides which to attened
        //they are enrolled and removed from the applicant pool
        ArrayList<Student> acceptedStudents = new ArrayList<>();
        for (Student student : students) {
            ArrayList<College> acceptedColleges = new ArrayList<>();
            boolean studentAcceptance = false;

            for (int i = 0; i < acceptedLists.size(); i++) {
                if (acceptedLists.get(i).contains(student)) {
                    acceptedColleges.add(colleges.get(i));
                    studentAcceptance = true;
                }
            }
            if (studentAcceptance){
                College attendingCollege = student.decide(acceptedColleges);
                attendingCollege.enroll(student);
                acceptedStudents.add(student);
            }
        }
        students.removeAll(acceptedStudents);
    }
    public void calculateWealthPerYear(){
        //add graduation thingy
        for (College college : colleges){

            college.getWealth().payTuition(college.getAttendingStudents().size());
            college.getWealth().updatePubIm(college.getAttendingStudents(), year, college.admissions.getAcceptanceRate());
            college.getWealth().receiveDonations(college.getAlumni());
            System.out.println("the wealth for " + college.name + " is " + college.getWealth().money);
        }

    }

    public void calculateWealthPerClass(int classYear){
        //HashMap<String, ArrayList<Student>> classOf_ = new HashMap<>();
        for (College college : colleges){//makes a list of all the students of one singular class year
            //System.out.println("this many attend: " + college.attendingStudents.size());
            //System.out.println("number of attending students: " + college.getAttendingStudents().size());
            ArrayList<Student> sameYearStudents = new ArrayList<>();
            for(Student student : college.getAttendingStudents()){
                if (student.getHashMap().get("Application Cycle") == classYear){
                    sameYearStudents.add(student);
                }
            }
            //classOf_.put(college.name, sameYearStudents);

            for(int i=0;i<4;i++){//pays tuition for all four years of that class
                college.getWealth().payTuition(sameYearStudents.size());
            }
            college.getWealth().updatePubIm(college.getAttendingStudents(), classYear, college.admissions.getAcceptanceRate());
            college.getWealth().receiveClassCumulativeDonations(sameYearStudents);
            System.out.println("the wealth for " + college.name + " is " + college.getWealth().money);
        }
    }
    //CHATGPT HELL YEAH BABYYYYYY RAHHHHHHHHH
    public ArrayList<College> orderCollegesByPubIm(){
        ArrayList<College> collegesRanked = new ArrayList<>(colleges); // Assume 'colleges' is a class member containing the initial list
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < collegesRanked.size() - 1; i++) {
                College current = collegesRanked.get(i);
                College next = collegesRanked.get(i + 1);
                if (current.getWealth().pubIm < next.getWealth().pubIm) {
                    // Swap current and next
                    collegesRanked.set(i, next);
                    collegesRanked.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return collegesRanked;
    }
    public ArrayList<College> orderCollegesByMoney(){
        ArrayList<College> collegesRanked = new ArrayList<>(colleges); // Assume 'colleges' is a class member containing the initial list
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < collegesRanked.size() - 1; i++) {
                College current = collegesRanked.get(i);
                College next = collegesRanked.get(i + 1);
                if (current.getWealth().money < next.getWealth().money) {
                    // Swap current and next
                    collegesRanked.set(i, next);
                    collegesRanked.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return collegesRanked;
    }

}

