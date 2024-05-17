package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


/**
 * Represents a student participating in the admissions cycle.
 * This class stores various attributes of a student, including academic and extracurricular information,
 * and generates preferences for colleges based on certain criteria.
 */
public class Student {
    protected String major;
    protected String diversity;
    protected ArrayList<College> preference;
    //Hash Map
    HashMap<String, Integer> studentInfo = new HashMap<>();
    private double score;

    //doesn't pass in colleges ranked
    /**
     * Constructs a new Student with the specified attributes and calculates initial preferences.
     *
     * @param rigor               The rigor of the student's secondary school record
     * @param classRank           The student's class rank
     * @param GPA                 The student's GPA
     * @param SAT                 The student's SAT score
     * @param essay               The quality of the student's application essay
     * @param recommendations     The quality of recommendations for the student
     * @param interview           The quality of the student's interview
     * @param extraCurriculars    The student's extracurricular talent
     * @param talent              The student's talent and ability
     * @param character           The student's character and personal qualities
     * @param firstGen            Whether the student is a first-generation college student
     * @param alumniRelation      Whether the student has alumni relations
     * @param geoRes              The student's geographical residence
     * @param stateRes            The student's state residency
     * @param religion            The student's religious affiliation or commitment
     * @param raceEthnicStatus    The student's racial or ethnic status
     * @param volunteerWork       The student's volunteer work
     * @param workExp             The student's work experience
     * @param levelInt            The student's level of interest
     * @param cycleNumber         The application cycle number
     * @param year                The application year
     * @param collegeRank         The list of colleges ranked by the student
     * @param major               The student's chosen major
     * @param diversity           The student's diversity status
     */
    public Student(int rigor, int classRank, int GPA, int SAT, int essay, int recommendations, int interview, int extraCurriculars, int talent, int character, int firstGen, int alumniRelation, int geoRes, int stateRes, int religion, int raceEthnicStatus, int volunteerWork, int workExp, int levelInt, int cycleNumber, int year, ArrayList<College> collegeRank, String major, String diversity) {
        score = 0;
        studentInfo.put("Rigor of secondary school record", rigor);
        studentInfo.put("Class rank", classRank);
        studentInfo.put("Academic GPA", GPA);
        studentInfo.put("Standardized test scores", SAT);
        studentInfo.put("Application Essay", essay);
        studentInfo.put("Recommendation(s)", recommendations);
        studentInfo.put("Interview",interview);
        studentInfo.put("Extracurricular activities",extraCurriculars);
        studentInfo.put("Talent/ability",talent);
        studentInfo.put("Character/personal qualities",character);
        studentInfo.put("First generation",firstGen);
        studentInfo.put("Alumni/ae relation",alumniRelation);
        studentInfo.put("Geographical residence",geoRes);
        studentInfo.put("State residency",stateRes);
        studentInfo.put("Religious affiliation/commitment",religion);
        studentInfo.put("Racial/ethnic status",raceEthnicStatus);
        studentInfo.put("Volunteer work",volunteerWork);
        studentInfo.put("Work experience",workExp);
        studentInfo.put("Level of applicant\u2019s interest",levelInt);
        studentInfo.put("Application Cycle",cycleNumber);
        studentInfo.put("Application Year", year);
        preference = collegePrefGen(collegeRank);
        this.major = major;
        this.diversity = diversity;


    }


    /**
     * Generates a list of college preferences based on the public image of the colleges.
     *
     * @param collegeRank The list of colleges ranked by public image.
     * @return The list of colleges ordered by preference.
     */
    public ArrayList<College> collegePrefGen(ArrayList<College> collegeRank){
        ArrayList<College> list = new ArrayList<>();
        ArrayList<Integer> collegePercents = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
        ArrayList<Integer> copyCollegeRank = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6));


        //have to change to seven so edit everything to fit
        for(int k = 0; k < 7; k++){
            double pubImTotal = 0; //the denominator for the fraction
            int hundredCheck = 0; //makes sure it adds up to 100 random number is in range

            //adding up all public images
            for (Integer integer : copyCollegeRank) {
                pubImTotal = pubImTotal + collegeRank.get(integer).getWealth().pubIm;
            }

            //sets the new percentages with their public image / pubImTotal
            for(int j = 0; j < copyCollegeRank.size(); j++ ){
                collegePercents.set(j, (int) Math.round(((collegeRank.get(copyCollegeRank.get(j)).getWealth().pubIm) / pubImTotal) * 100.0));
                hundredCheck = hundredCheck + collegePercents.get(j);
            }

            //checks if hundred, if not adds or subtracts from top school
            if (hundredCheck != 100){
                if(hundredCheck > 100){
                    collegePercents.set(0, (collegePercents.get(0) - (hundredCheck - 100)));
                }
                else{
                    collegePercents.set(0, (collegePercents.get(0) + (100 - hundredCheck)));
                }
            }


            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            double probAdd = 0; //the bottom range
            int collegeIndexRemove = 0; // tells which index to remove from the copy and percent lists

            for(int l = 0; l < collegePercents.size(); l++){
                if (l == 0 && randomNumber <= collegePercents.get(0)){
                    //System.out.println("college percent = " + collegePercents);
                    //System.out.println("college rank = " + collegeRank );
                    list.add(collegeRank.get(copyCollegeRank.get(0)));
                }
                else if (randomNumber >= (probAdd + 0.01) && randomNumber <= (collegePercents.get(l) + probAdd)) {
                    list.add(collegeRank.get(copyCollegeRank.get(l)));
                    collegeIndexRemove = l;
                }
                probAdd = probAdd + collegePercents.get(l);

            }
            copyCollegeRank.remove(collegeIndexRemove);
            collegePercents.remove(collegeIndexRemove);
        }

        return list;
    }

    /**
     * Decides which college the student prefers the most from the given list of accepted colleges.
     *
     * @param collegeAct The list of colleges that accepted the student.
     * @return The college that the student prefers the most.
     */
    public College decide(ArrayList<College> collegeAct) {
        int min = 5;
        for (College college : collegeAct) {
            for (int j = 0; j < preference.size(); j++) {
                if ((college == preference.get(j) && j < min)) {
                    min = j;
                }
            }
        }
        return preference.get(min);
    }


    /**
     * Gets the student's information stored in the HashMap.
     *
     * @return The HashMap containing the student's information.
     */
    public HashMap<String, Integer> getHashMap() {
        return studentInfo;
    }

    /**
     * Sets the student's college preferences.
     *
     * @param colleges The list of colleges to set as preferences.
     */
    public void setList(ArrayList<College> colleges) {
        this.preference = colleges;
    }

    /**
     * Gets the student's college preferences.
     *
     * @return The list of colleges preferred by the student.
     */
    public ArrayList<College> getList() {
        return preference;
    }

    /**
     * Gets the student's diversity status.
     *
     * @return The student's diversity status.
     */
    public String getDiversity() {
        return diversity;
    }

    /**
     * Sets the student's diversity status.
     *
     * @param diversity The diversity status to set.
     */
    public void setDiversity(String diversity) {
        this.diversity = diversity;
    }

    /**
     * Gets the student's major.
     *
     * @return The student's major.
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the student's major.
     *
     * @param major The major to set.
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Gets the student's application score.
     *
     * @return The student's application score.
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets the student's application score.
     *
     * @param score The score to set.
     */
    public void setScore(double score) {
        this.score = score;
    }
}