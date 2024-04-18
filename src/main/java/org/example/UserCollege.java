package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserCollege {
    HashMap<String, Integer> userCollegeInfo = new HashMap<>();
    public int capacity;
    private ArrayList<Student> attendingStudents;
    private ArrayList<Student> alumni;
    private Wealth wealth;
    public int fAidBudget;
    //need to make subclass of admissions office that does everything for user college
    //make a financial aid budget
    public UserCollege(int rigor, int classRank, int GPA, int SAT, int essay, int recommendations, int interview, int extraCurriculars, int talent, int character, int firstGen, int alumniRelation, int geoRes, int stateRes, int religion, int raceEthnicStatus, int volunteerWork, int workExp, int levelInt, int capacity,int tuition,int pubIm) {
        userCollegeInfo.put("Rigor of secondary school record", rigor);
        userCollegeInfo.put("Class Rank", classRank);
        userCollegeInfo.put("Academic GPA", GPA);
        userCollegeInfo.put("Standardized test scores", SAT);
        userCollegeInfo.put("Application Essay", essay);
        userCollegeInfo.put("Recommendation(s)", recommendations);
        userCollegeInfo.put("Interview",interview);
        userCollegeInfo.put("Extracurricular activities",extraCurriculars);
        userCollegeInfo.put("Talent/ability",talent);
        userCollegeInfo.put("Character/personal qualities",character);
        userCollegeInfo.put("First generation",firstGen);
        userCollegeInfo.put("Alumni/ae relation",alumniRelation);
        userCollegeInfo.put("Geographical residence",geoRes);
        userCollegeInfo.put("State residency",stateRes);
        userCollegeInfo.put("Religious affiliation/commitment",religion);
        userCollegeInfo.put("Racial/ethnic status",raceEthnicStatus);
        userCollegeInfo.put("Volunteer work",volunteerWork);
        userCollegeInfo.put("Work experience",workExp);
        userCollegeInfo.put("Level of applicant's interest",levelInt);

        this.capacity = capacity;
        attendingStudents = new ArrayList<Student>();
        alumni = new ArrayList<Student>();
        wealth = new Wealth(0,tuition,pubIm);


    }

    public void chooseCollegeInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how much you consider the rigor of secondary school record: ");
        userCollegeInfo.put("Rigor of secondary school record", scanner.nextInt());
        System.out.println("Enter how much you consider class rank: ");
        userCollegeInfo.put("Class Rank", scanner.nextInt());
        System.out.println("Enter how much you consider academic GPA: ");
        userCollegeInfo.put("Academic GPA", scanner.nextInt());
        System.out.println("Enter how much you consider standardized test scores: ");
        userCollegeInfo.put("Standardized test scores", scanner.nextInt());
        System.out.println("Enter how much you consider application essays: ");
        userCollegeInfo.put("Application Essay", scanner.nextInt());
        System.out.println("Enter how much you consider recommendations: ");
        userCollegeInfo.put("Recommendation(s)", scanner.nextInt());
        System.out.println("Enter how much you consider interviews: ");
        userCollegeInfo.put("Interview", scanner.nextInt());
        System.out.println("Enter how much you consider extracurricular activities: ");
        userCollegeInfo.put("Extracurricular activities", scanner.nextInt());
        System.out.println("Enter how much you consider talent/ability: ");
        userCollegeInfo.put("Talent/ability", scanner.nextInt());
        System.out.println("Enter how much you consider character/personal qualities: ");
        userCollegeInfo.put("Character/personal qualities", scanner.nextInt());
        System.out.println("Enter how much you consider first generation: ");
        userCollegeInfo.put("First generation", scanner.nextInt());
        System.out.println("Enter how much you consider Alumni/ae relation: ");
        userCollegeInfo.put("Alumni/ae relation", scanner.nextInt());
        System.out.println("Enter how much you consider geographical residence: ");
        userCollegeInfo.put("Geographical residence", scanner.nextInt());
        System.out.println("Enter how much you consider state residency: ");
        userCollegeInfo.put("State residency", scanner.nextInt());
        System.out.println("Enter how much you consider religious affiliation/commitment: ");
        userCollegeInfo.put("Religious affiliation/commitment", scanner.nextInt());
        System.out.println("Enter how much you consider racial/ethnic status: ");
        userCollegeInfo.put("Racial/ethnic status", scanner.nextInt());
        System.out.println("Enter how much you consider volunteer work: ");
        userCollegeInfo.put("Volunteer work", scanner.nextInt());
        System.out.println("Enter how much you consider work experience: ");
        userCollegeInfo.put("Work experience", scanner.nextInt());
        System.out.println("Enter how much you consider the applicant's interest: ");
        userCollegeInfo.put("Level of applicant's interest", scanner.nextInt());

        System.out.println("Enter your college's capacity: ");
        capacity = scanner.nextInt();

        //major requirements

        scanner.close();
    }

    public void enroll(Student student){
        attendingStudents.add(student);
    }

    public void graduate(Student student){
        attendingStudents.remove(student);
        alumni.add(student);
    }

    //Questions about UserCollege
    //where is the users wealth calculated?

}
