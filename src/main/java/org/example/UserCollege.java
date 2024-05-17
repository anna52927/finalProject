package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Represents a user-defined college participating in the admissions cycle.
 * This class extends the College class and allows customization of various parameters
 * such as capacity, tuition, public image, and admissions criteria.
 */
public class UserCollege extends College {
    private HashMap<String, Integer> userCollegeInfo = new HashMap<>();
    public int capacity;
    private ArrayList<Student> attendingStudents;
    private ArrayList<Student> alumni;
    private Wealth wealth;
    public UserAdmissionsOffice admissions;

    /**
     * Constructs a new UserCollege with the specified attributes.
     *
     * @param name                   The name of the college.
     * @param capacity               The capacity of the college.
     * @param tuition                The tuition fee for the college.
     * @param pubIm                  The public image score of the college.
     * @param initialAcceptanceRate  The initial acceptance rate of the college.
     * @param majorCutoff            The cutoff for major selection.
     * @param diversityCutoff        The cutoff for diversity considerations.
     * @param EDAdmitPercent         The percentage of ED admits.
     * @param initialYieldRate       The initial yield rate of the college.
     */
    public UserCollege(String name, int capacity, int tuition, int pubIm, double initialAcceptanceRate, int majorCutoff, int diversityCutoff, double EDAdmitPercent, double initialYieldRate) {
        super(name, capacity, tuition, pubIm, initialAcceptanceRate, majorCutoff, diversityCutoff, EDAdmitPercent, initialYieldRate);
        this.capacity = capacity;
        this.name = name;
        admissions = new UserAdmissionsOffice(this, initialAcceptanceRate, majorCutoff, diversityCutoff, EDAdmitPercent, initialYieldRate);
        attendingStudents = new ArrayList<>();
        alumni = new ArrayList<>();
        wealth = new Wealth(0, tuition, pubIm);
    }

    /**
     * Sets the default user college information with specified criteria.
     *
     * @param rigor            The rigor of the secondary school record.
     * @param classRank        The class rank.
     * @param GPA              The academic GPA.
     * @param SAT              The standardized test scores.
     * @param essay            The application essay.
     * @param recommendations  The recommendations.
     * @param interview        The interview performance.
     * @param extraCurriculars The extracurricular activities.
     * @param talent           The talent and ability.
     * @param character        The character and personal qualities.
     * @param firstGen         Whether the student is a first-generation college student.
     * @param alumniRelation   The alumni relations.
     * @param geoRes           The geographical residence.
     * @param stateRes         The state residency.
     * @param religion         The religious affiliation or commitment.
     * @param raceEthnicStatus The racial or ethnic status.
     * @param volunteerWork    The volunteer work.
     * @param workExp          The work experience.
     * @param levelInt         The level of the applicant's interest.
     */
    public void setDefaultUserCollege(int rigor, int classRank, int GPA, int SAT, int essay, int recommendations, int interview, int extraCurriculars, int talent, int character, int firstGen, int alumniRelation, int geoRes, int stateRes, int religion, int raceEthnicStatus, int volunteerWork, int workExp, int levelInt) {
        userCollegeInfo.put("Rigor of secondary school record", rigor);
        userCollegeInfo.put("Class Rank", classRank);
        userCollegeInfo.put("Academic GPA", GPA);
        userCollegeInfo.put("Standardized test scores", SAT);
        userCollegeInfo.put("Application Essay", essay);
        userCollegeInfo.put("Recommendation(s)", recommendations);
        userCollegeInfo.put("Interview", interview);
        userCollegeInfo.put("Extracurricular activities", extraCurriculars);
        userCollegeInfo.put("Talent/ability", talent);
        userCollegeInfo.put("Character/personal qualities", character);
        userCollegeInfo.put("First generation", firstGen);
        userCollegeInfo.put("Alumni/ae relation", alumniRelation);
        userCollegeInfo.put("Geographical residence", geoRes);
        userCollegeInfo.put("State residency", stateRes);
        userCollegeInfo.put("Religious affiliation/commitment", religion);
        userCollegeInfo.put("Racial/ethnic status", raceEthnicStatus);
        userCollegeInfo.put("Volunteer work", volunteerWork);
        userCollegeInfo.put("Work experience", workExp);
        userCollegeInfo.put("Level of applicant's interest", levelInt);
    }

    /**
     * Allows the user to input and set college information interactively.
     */
    public void chooseCollegeInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your college's capacity: ");
        super.capacity = scanner.nextInt();
        System.out.println("Enter your college's tuition: ");
        super.getWealth().TUITION = scanner.nextInt();
        System.out.println("Enter your college's public image score: ");
        super.getWealth().pubIm = scanner.nextInt();

        admissions.chooseAdmissionsOfficeInfo();

        scanner.close();
    }


}
