package org.example;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class AdmissionsOffice {
    private College self;  //idk what to name this
    private Map<String,Object> importance;  //ranked table of importance
    private HashMap<Integer,Double> acceptanceRate;
    private ArrayList<Student> admittedStudents;
    private HashMap<String,Integer> majorDistributions;
    private HashMap<Integer,Integer> diversityDistributions;
    private int majorCutoff; //max # of students a major can go over, sacrifices diversity
    private int diversityCutoff; //opposite of majorCutoff
    private int EDApplied; //stored datum from ED rounds so accurate acceptance rate can be calculated
    private double EDAdmitCapacity;  //percentage of total capacity to be filled by ED

    public AdmissionsOffice(College college, double initialAcceptanceRate){
        this.self = college; //wow, this line looks cursed
        acceptanceRate = new HashMap<>();
        admittedStudents = new ArrayList<>();
        acceptanceRate.put(0,initialAcceptanceRate);
        importance = JSONData.JSONImport(college.name + "ImportantMetrics.json");
    }

    public ArrayList<Student> considerApplicants(ArrayList<Student> applicants,String round){
        int capacity;
        if(round.equals("ED")){
            capacity = (int) (self.capacity * EDAdmitCapacity);
        } else {
            capacity = self.capacity;
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

        //list slicing (python superiority moment)
        int i = 0;
        while(admittedStudents.size() < self.capacity && i < applicants.size()){
            Student student = applicants.get(i);
            String major = student.getMajor();
            int diversity = student.getDiversity();
            if(majorDistributions.get(major) > diversityCutoff && diversityDistributions.get(diversity) > majorCutoff) {
                admittedStudents.add(student);
                majorDistributions.put(major, majorDistributions.get(major) - 1);
                diversityDistributions.put(diversity, diversityDistributions.get(diversity) - 1);
            }
            i++;
        }

        if(round.equals("ED")){
            EDApplied = applicants.size();
        } else {
            acceptanceRate.put(applicants.get(0).getHashMap().get("Application Year"), (double) admittedStudents.size() / (applicants.size()+EDApplied));
        }

        return admittedStudents;
    }

    public double evaluateApplicant(Student applicant) {
        int score = 0;
        for (Map.Entry<String, Object> entry : importance.entrySet()) {
            String key = entry.getKey();
            Map<String,Integer> collegeVal = (Map<String,Integer>)entry.getValue();
            System.out.println(key);
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