package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a college with various attributes including name, capacity, attending students,
 * alumni, admissions office, and financial wealth.
 */
public class College {
    public String name;
    public int capacity;
    public ArrayList<Student> attendingStudents;
    public ArrayList<Student> alumni;
    public AdmissionsOffice admissions;
    private Wealth wealth;
    public int EDAdmitCapacity;

    /**
     * Constructs a new College with the specified parameters.
     *
     * @param name                   The name of the college.
     * @param capacity               The total student capacity of the college.
     * @param tuition                The tuition fee per student.
     * @param pubIm                  The public image score of the college.
     * @param initialAcceptanceRate  The initial acceptance rate of the college.
     * @param majorCutoff            The cutoff for major selection.
     * @param diversityCutoff        The cutoff for diversity considerations.
     * @param EDAdmitPercent         The percentage of students admitted through Early Decision.
     * @param initialYieldRate       The initial yield rate of the college.
     */
    public College(String name, int capacity, int tuition, int pubIm, double initialAcceptanceRate, int majorCutoff, int diversityCutoff, double EDAdmitPercent,double initialYieldRate){
        this.capacity = capacity / 4;     //adjusts to be capacity for freshmen only
        this.name = name;
        attendingStudents = new ArrayList<Student>();
        alumni = new ArrayList<Student>();
        EDAdmitCapacity = (int)EDAdmitPercent * capacity;

        wealth = new Wealth(0,tuition,pubIm);
        admissions = new AdmissionsOffice(this,initialAcceptanceRate,majorCutoff,diversityCutoff,EDAdmitCapacity,initialYieldRate);
        System.out.println(admissions.getAcceptanceRate());


    }
    /**
     * Enrolls a student into the college by adding them to the list of attending students.
     *
     * @param student The student to be enrolled.
     */
    public void enroll(Student student){
        attendingStudents.add(student);
    }

    /**
     * Graduates or removes a student from the college and adds them to the list of alumni.
     *
     * @param student The student to be graduated.
     */
    public void graduate(Student student){
        attendingStudents.remove(student);
        alumni.add(student);
    }

    /**
     * Retrieves the list of currently attending students.
     *
     * @return An ArrayList of students currently attending the college.
     */
    public ArrayList<Student> getAttendingStudents() {
        return attendingStudents;
    }

    /**
     * Retrieves the wealth information of the college.
     *
     * @return A Wealth object representing the college's financial status.
     */
    public Wealth getWealth() {
        return wealth;
    }

    /**
     * Retrieves the list of alumni who have graduated from the college.
     *
     * @return An ArrayList of students who are alumni of the college.
     */
    public ArrayList<Student> getAlumni() {
        return alumni;
    }
}

