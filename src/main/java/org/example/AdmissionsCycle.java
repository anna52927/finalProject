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

// The AdmissionsCycle class handles the logic for a college admission cycle,
// which includes managing early decision and regular admission rounds.
public class AdmissionsCycle {
    private ArrayList<Student> students; // List of students participating in this admissions cycle
    private ArrayList<College> colleges; // List of colleges participating in this admissions cycle
    private String round; // Current admission round (ED or RA)
    private int year; // Academic year for the admissions cycle


    /**
     * Constructor for AdmissionsCycle.
     * Initializes a new admissions cycle with specified students, colleges, and year.
     *
     * @param students List of students participating in this admissions cycle.
     * @param colleges List of colleges participating in this admissions cycle.
     * @param year The current year of the admissions cycle.
     */
    public AdmissionsCycle(ArrayList<Student> students, ArrayList<College> colleges, int year){
        this.students = students;
        this.colleges = colleges;
        round = "ED";
        this.year = year;

    }


    /**
     * Conducts the early decision (ED) round of college admissions.
     * Students apply to their first-choice college and may be accepted and enrolled.
     */
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
            //System.out.println(college.admissions);
            ArrayList<Student> attendingStudents = college.admissions.considerApplicants(applicantsMap.get(collegeName), round);
            for (Student student: attendingStudents){
                college.enroll(student);
            }
            students.removeAll(attendingStudents);
        }
    }
    /**
     * Conducts the regular admission (RA) round of college admissions.
     * Students apply to their next choices, and decisions are made accordingly.
     */
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
    /**
     * Calculates the wealth generated per year by the current attending students and alums in each college.
     */
    public void calculateWealthPerYear(){
        //add graduation thingy
        for (College college : colleges){
            for(Student student:students){
                if (student.getHashMap().get("Application Cycle")==year-4){
                    college.graduate(student);
                }
            }

            college.getWealth().payTuition(college.getAttendingStudents().size());
            college.getWealth().updatePubIm(college.getAttendingStudents(), year, college.admissions.getAcceptanceRate(), college);
            college.getWealth().receiveDonations(college.getAlumni());
            System.out.println("the wealth for " + college.name + " is " + college.getWealth().money);
        }

    }
    /**
     * Calculates the total expected wealth for one specific class from their time as students to their time as alumni.
     *
     * @param classYear The year of the class for which the wealth is being calculated.
     */
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
            college.getWealth().updatePubIm(college.getAttendingStudents(), classYear, college.admissions.getAcceptanceRate(), college);
            college.getWealth().receiveClassCumulativeDonations(sameYearStudents);
            System.out.println("the wealth for " + college.name + " is " + college.getWealth().money);
        }
    }
    //CHATGPT HELL YEAH BABYYYYYY RAHHHHHHHHH
    /**
     * Ranks colleges based on their public image score.
     *
     * @return A list of colleges sorted by public image.
     */
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
    /**
     * Ranks colleges based on their monetary wealth.
     *
     * @return A list of colleges sorted by their financial status.
     */
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
    /**
     * Gets the list of students participating in this admissions cycle.
     * @return A list of students.
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students participating in this admissions cycle.
     * @param students The list of students to set.
     */
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Gets the list of colleges participating in this admissions cycle.
     * @return A list of colleges.
     */
    public ArrayList<College> getColleges() {
        return colleges;
    }

    /**
     * Sets the list of colleges participating in this admissions cycle.
     * @param colleges The list of colleges to set.
     */
    public void setColleges(ArrayList<College> colleges) {
        this.colleges = colleges;
    }

    /**
     * Gets the current admission round.
     * @return The current round (ED or RA).
     */
    public String getRound() {
        return round;
    }

    /**
     * Sets the current admission round.
     * @param round The admission round to set (ED or RA).
     */
    public void setRound(String round) {
        this.round = round;
    }

    /**
     * Gets the academic year for the admissions cycle.
     * @return The academic year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the academic year for the admissions cycle.
     * @param year The academic year to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

}

