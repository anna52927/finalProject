package org.example;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class AdmissionsOffice {
    public College self;  //idk what to name this
    public Map<String,Object> importance;  //ranked table of importance
    public HashMap<Integer,Double> acceptanceRate;
    public ArrayList<Student> admittedStudents;
    public HashMap<String,Object> majorDistributions;
    public Map<Integer,Integer> diversityDistributions;
    public int majorCutoff; //max # of students a major can go over, sacrifices diversity
    public int diversityCutoff; //opposite of majorCutoff
    public int EDApplied; //stored datum from ED rounds so accurate acceptance rate can be calculated
    public double EDAdmitCapacity;  //percentage of total capacity to be filled by ED
    public double yieldRate;  //The most recent years yield rate
    public int admitCapacity; //The amount of students to admit (based off of yield)


    public AdmissionsOffice(College college, double initialAcceptanceRate,int majorCutoff,int diversityCutoff,double EDAdmitCapacity, boolean isUserAd,double yieldRate){

        this.self = college; //wow, this line looks cursed
        this.majorCutoff = majorCutoff;
        this.diversityCutoff = diversityCutoff;
        this.EDAdmitCapacity = EDAdmitCapacity;
        this.yieldRate = yieldRate;
        acceptanceRate = new HashMap<>();
        admittedStudents = new ArrayList<>();
        acceptanceRate.put(0,initialAcceptanceRate);
        majorDistributions = new HashMap<String, Object>();

        /*
        WICHTIGE HINWEIS FUER FELIX (deswegen es ist auf Deutsch geschrieben)
        Die Kapazitaetsvariable muss in 2 Variablen geteilt wegen die Neuberechnung von die Kapazitaet
        majorDistributions muss auch jede Runde neu berechnet -- am besten durch eine calculateMajorDistributions Methode
         */

        if (!isUserAd){
            importance = JSONData.JSONImport(college.name + "ImportantMetrics.json");
            Map<String,Object> rawMajorDistributions = JSONData.JSONImport(college.name + "MajorDistribution.json");
            // Chat GPTified code
            for (Map.Entry<String, Object> entry : rawMajorDistributions.entrySet()) {
                Map<String, Object> majorMap = (Map<String, Object>) entry.getValue();
                Map<String, Object> bachelorMap = (Map<String, Object>) majorMap.get("Bachelor\u2019s");
                Object value = bachelorMap.get("value");
                if (value.equals("") || value.equals("<1%")) {
                    value = 0.0;
                }
                double calculatedValue = (double) value * college.capacity;
                int intValue = (int) calculatedValue;
                majorDistributions.put(entry.getKey(), intValue);
            }
        }
    }

    public void calculateCapacity(){
        admitCapacity = (int)(self.capacity / yieldRate);
    }

    public void calculateYieldRate(){
        //kinda janky, would have made more sense if we stored admitted students as an array of class arrays
        //solche Sachen sind was schwierig wenn Mann den UML nicht veraendern kann und wenn da kein zentrale Struktur gibt (schwierig mit drei verschiedene Personen)
        //hauptsache besser als nix
        int year = admittedStudents.get(0).getHashMap().get("Application Cycle");
        int count = 0;
        for (Student student : self.attendingStudents) {
            if(student.getHashMap().get("Application Cycle") == year){
                count++;
            }
        }
        yieldRate = (double)count/admittedStudents.size();
    }

    public ArrayList<Student> considerApplicants(ArrayList<Student> applicants,String round){
        //System.out.println("this many apply to " + self.name + " "+round + " : "+ applicants.size());
        int capacity;
        calculateYieldRate();
        calculateCapacity();

        if(round.equals("ED")){
            capacity = (int) (admitCapacity * EDAdmitCapacity);
        } else {
            capacity = admitCapacity;
        }

        for (Student applicant:
                applicants) {
            applicant.setScore(evaluateApplicant(applicant));
        }
        int n = applicants.size();
        Student swap;

        while (n > 1){
            for (int i = 0; i < n - 1; i++) {
                if (applicants.get(i).getScore() < applicants.get(i+1).getScore()){
                    swap = applicants.get(i+1);
                    applicants.set(i+1,applicants.get(i));
                    applicants.set(i,swap);
                }
            }
            n--;
        }

        int i = 0;
        while(admittedStudents.size() < self.capacity && i < applicants.size()){
            Student student = applicants.get(i);
            String major = student.getMajor();
            int diversity = student.getDiversity();
            Map<String,Object> majorMap = (Map<String,Object>)majorDistributions.get(major);
            Map<String,Object> bachelorMap = (Map<String,Object>)majorMap.get("Bachelor\u2019s");
            //&& diversityDistributions.get(diversity) > majorCutoff
            if((int)bachelorMap.get("value") > diversityCutoff) {
                admittedStudents.add(student);
                bachelorMap.put("value", (int)bachelorMap.get("value") - 1);
                //diversityDistributions.put(diversity, diversityDistributions.get(diversity) - 1);
            }
            i++;
        }

        if(round.equals("ED")){
            EDApplied = applicants.size();
        } else {

            //changed application year to application cycle
            acceptanceRate.put(applicants.get(0).getHashMap().get("Application Cycle"), (double) admittedStudents.size() / (applicants.size()+EDApplied));

        }
        return admittedStudents;
    }

    public double evaluateApplicant(Student applicant) {
        int score = 0;
        for (Map.Entry<String, Object> entry : importance.entrySet()) {
            String key = entry.getKey();
            Map<String,Integer> collegeVal = (Map<String,Integer>)entry.getValue();
            score += applicant.studentInfo.get(key) * collegeVal.get("value"); //need corresponding student hashMap
        }
        return score;
    }

    public void resetRound(){
        admittedStudents.clear();
        EDApplied = 0;
    }

    public HashMap<Integer,Double> getAcceptanceRate(){
        return acceptanceRate;
    }
}