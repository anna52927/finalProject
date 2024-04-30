
package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserAdmissionsOffice extends AdmissionsOffice {
    private UserCollege self;  //idk what to name this
    private Map<String,Integer> importance;  //ranked table of importance
    private HashMap<Integer,Double> acceptanceRate;
    private ArrayList<Student> admittedStudents;
    private Map<String,Object> majorDistributions;
    private Map<Integer,Integer> diversityDistributions;
    private int majorCutoff; //max # of students a major can go over, sacrifices diversity
    private int diversityCutoff; //opposite of majorCutoff
    private int EDApplied; //stored datum from ED rounds so accurate acceptance rate can be calculated
    private double EDAdmitCapacity;  //percentage of total capacity to be filled by ED


    public UserAdmissionsOffice(UserCollege college,double initialAcceptanceRate,int majorCutoff,int diversityCutoff,double EDAdmitCapacity){
        super(college,initialAcceptanceRate,majorCutoff,diversityCutoff,EDAdmitCapacity);
        acceptanceRate = new HashMap<>();
        acceptanceRate.put(0,initialAcceptanceRate);
        importance = college.userCollegeInfo;

        //i dont know if we can make this a subclass now because the constructor in admissions
        //office takes in a college object but user college is its own thing separate from the
        //college class. Could change what the constructor takes in

    }

    public void chooseAdmissionsOfficeInfo(){
        Scanner scanner = new Scanner(System.in);
        //ADD IN A BUCNH OF STATEMENTS ASKING ABOUT MAJOR DISTRIBUTIONS, DIVERSITY DISTRIBUTIONS
        //major cutoff, diversity cutoff, ed admit capacity
    }



}

