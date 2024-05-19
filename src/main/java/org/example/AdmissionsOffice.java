package org.example;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
/**
 * Represents the admissions office of a college, managing admissions criteria,
 * acceptance rates, admitted students, major and diversity distributions, and more.
 */
public class AdmissionsOffice {
    public College self;  //idk what to name this
    public Map<String,Object> importance;  //ranked table of importance
    public HashMap<Integer,Double> acceptanceRate;
    public ArrayList<Student> admittedStudents;
    public Map<String,Object> rawMajorDistributions;
    public HashMap<String,Integer> majorDistributions;
    public Map<String,Object> rawDiversityDistributions;
    public HashMap<String,Integer> diversityDistributions;
    public int majorCutoff; //max # of students a major can go over, sacrifices diversity
    public int diversityCutoff; //opposite of majorCutoff
    public int EDApplied; //stored datum from ED rounds so accurate acceptance rate can be calculated
    public double EDAdmitCapacity;  //percentage of total capacity to be filled by ED
    public double yieldRate;  //The most recent years yield rate
    public int admitCapacity; //The amount of students to admit (based off of yield)

    /**
     * Constructs a new AdmissionsOffice with the specified parameters.
     *
     * @param college                The associated college.
     * @param initialAcceptanceRate  The initial acceptance rate.
     * @param majorCutoff            The cutoff for major selection.
     * @param diversityCutoff        The cutoff for diversity considerations.
     * @param EDAdmitCapacity        The percentage of students admitted through Early Decision.
     * @param initialYieldRate       The initial yield rate.
     */
    public AdmissionsOffice(College college, double initialAcceptanceRate,int majorCutoff,int diversityCutoff,double EDAdmitCapacity,double initialYieldRate){

        this.self = college; //wow, this line looks cursed
        this.majorCutoff = majorCutoff;
        this.diversityCutoff = diversityCutoff;
        this.EDAdmitCapacity = EDAdmitCapacity;
        this.yieldRate = initialYieldRate;
        acceptanceRate = new HashMap<>();
        admittedStudents = new ArrayList<>();
        acceptanceRate.put(0,initialAcceptanceRate);
        majorDistributions = new HashMap<String,Integer>();
        diversityDistributions = new HashMap<String,Integer>();

        /*
        WICHTIGE HINWEIS FUER FELIX (deswegen es ist auf Deutsch geschrieben)
        Die Kapazitaetsvariable muss in 2 Variablen geteilt wegen die Neuberechnung von die Kapazitaet
        majorDistributions muss auch jede Runde neu berechnet -- am besten durch eine calculateMajorDistributions Methode
         */


        importance = JSONData.JSONImport(self.name + "ImportantMetrics.json");
        rawMajorDistributions = JSONData.JSONImport(self.name + "MajorDistribution.json");
        rawDiversityDistributions = JSONData.JSONImport(self.name + "DiversityDistribution.json");
        //adjust to percents
        for (Map.Entry<String, Object> entry : rawDiversityDistributions.entrySet()) {
            Map<String,Object> map = (Map<String, Object>) entry.getValue();
            Map<String,Object> diversitymap = (Map<String, Object>) map.get(Integer.toString(map.size()-1));
            double v;
            if(diversitymap.get("value").equals("")){
                v = 0.0;
            } else {
                v = (double) diversitymap.get("value");
            }
            rawDiversityDistributions.put(entry.getKey(), v / self.capacity);
        }
        calculateCapacity();
        calculateMajorDistributions();
        calculateDiversityDistributions();
    }

    /**
     * Calculates the admission capacity based on the yield rate.
     */
    public void calculateCapacity(){
        admitCapacity = (int)(self.capacity / yieldRate);
    }

    /**
     * Calculates the yield rate based on the number of admitted students and those who enrolled.
     */
    public void calculateYieldRate(){
        //kinda janky, would have made more sense if we stored admitted students as an array of class arrays
        int year = admittedStudents.get(0).getHashMap().get("Application Cycle");
        int count = 0;
        for (Student student : self.attendingStudents) {
            if(student.getHashMap().get("Application Cycle") == year){
                count++;
            }
        }
        yieldRate = (double)count/admittedStudents.size();
    }
    /**
     * Calculates the major distributions based on the raw data and admit capacity.
     */
    public void calculateMajorDistributions(){
        // Chat GPTified code (modified by ChatGPT)
        for (Map.Entry<String, Object> entry : rawMajorDistributions.entrySet()) {
            Map<String, Object> majorMap = (Map<String, Object>) entry.getValue();
            Map<String, Object> bachelorMap = (Map<String, Object>) majorMap.get("Bachelor\u2019s");
            Object value = bachelorMap.get("value");
            if (value.equals("") || value.equals("<1%")) {
                value = 0.0;
            }
            double calculatedValue = (double) value * admitCapacity;
            int intValue = (int) calculatedValue;
            majorDistributions.put(entry.getKey(), intValue);
        }
    }
    /**
     * Calculates the diversity distributions based on the raw data and admit capacity.
     */
    public void calculateDiversityDistributions(){
        for (Map.Entry<String,Object> entry : rawDiversityDistributions.entrySet()) {
            double value = (double) entry.getValue() * admitCapacity;
            diversityDistributions.put(entry.getKey(), (int) value);
        }
    }
    /**
     * Considers applicants for admission and determines which students to admit based on the current round (ED or RD).
     *
     * @param applicants The list of applicants.
     * @param round      The current admission round ("ED" or "RD").
     * @return The list of admitted students.
     */
    public ArrayList<Student> considerApplicants(ArrayList<Student> applicants,String round){
        //System.out.println("this many apply to " + self.name + " "+round + " : "+ applicants.size());
        int capacity;

        if(round.equals("ED")){
            //avoid running in the first round
            if(!self.attendingStudents.isEmpty()) {
                calculateYieldRate(); //calculating for RD while data is still stored in fields
            }
            admitCapacity = self.capacity; //yield rate is 100% in ED
            calculateMajorDistributions();
            calculateDiversityDistributions();
            capacity = (int) (admitCapacity * EDAdmitCapacity);
        } else {
            calculateCapacity();
            calculateMajorDistributions();
            calculateDiversityDistributions();
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

        while(admittedStudents.size() < capacity && i < applicants.size()){
            Student student = applicants.get(i);
            String major = student.getMajor();
            String  diversity = student.getDiversity();
            int value = majorDistributions.get(major);
            if(value > diversityCutoff && diversityDistributions.get(diversity) > majorCutoff) {
                admittedStudents.add(student);
                majorDistributions.put("value", majorDistributions.get(major) - 1);
                diversityDistributions.put(diversity, diversityDistributions.get(diversity) - 1);
            }
            i++;
        }

        if(round.equals("ED")){
            EDApplied = applicants.size();
        } else {
            //changed application year to application cycle
            acceptanceRate.put(applicants.get(0).getHashMap().get("Application Cycle"), (double) admittedStudents.size() / (applicants.size() + EDApplied));
        }

        return admittedStudents;
    }
    /**
     * Evaluates an applicant based on the importance metrics and returns their score.
     *
     * @param applicant The student applicant.
     * @return The evaluation score of the applicant.
     */
    public double evaluateApplicant(Student applicant) {
        int score = 0;
        for (Map.Entry<String, Object> entry : importance.entrySet()) {
            String key = entry.getKey();
            Map<String,Integer> collegeVal = (Map<String,Integer>)entry.getValue();
            score += applicant.studentInfo.get(key) * collegeVal.get("value"); //need corresponding student hashMap
        }
        return score;
    }

    //don't use this method
    /**
     * Resets the admission round, clearing the list of admitted students and resetting the ED applied count.
     */
    public void resetRound(){
        admittedStudents.clear();
        EDApplied = 0;
    }
    /**
     * Retrieves the acceptance rates of the college.
     *
     * @return A HashMap with the acceptance rates by year.
     */
    public HashMap<Integer,Double> getAcceptanceRate(){
        return acceptanceRate;
    }
}