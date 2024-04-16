package org.example;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class AdmissionsOffice {
    private College self;  //idk what to name this
    private Map<String,Object> importance;  //ranked table of importance
    private Map<Integer,Double> acceptanceRate;

    public AdmissionsOffice(College college, double initialAcceptanceRate){
        this.self = college; //wow, this line looks cursed
        acceptanceRate = new HashMap<>();
        acceptanceRate.put(0,initialAcceptanceRate);
        importance = JSONData.JSONImport(college.name + "ImportantMetrics.json");
    }

    public ArrayList<Student> considerApplicants(ArrayList<Student> applicants, String round){
        for (Student applicant:
                applicants) {
            applicant.setScore(evaluateApplicant(applicant)); //needs setScore method
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

        ArrayList<Student> admittedStudents = new ArrayList<>();
        //list slicing (python superiority moment)
        for (int i = 0; i < self.capacity; i++) {
            admittedStudents.add(applicants.get(i));
        }

        //check diversity
        HashMap<Integer,Integer> schoolDiversity = new HashMap<>();
        for (Student applicant :
                applicants) {
            schoolDiversity.put(applicant.diversity,schoolDiversity.getOrDefault(applicant.diversity,0)+1);
        }
        acceptanceRate.put(applicants.get(0).getHashMap().get("Application Year"),(double) admittedStudents.size()/applicants.size());

        return admittedStudents;
    }

    public double evaluateApplicant(Student applicant) {
        int score = 0;
        for (Map.Entry<String, Object> entry : importance.entrySet()) {
            String key = entry.getKey();
            int collegeVal = (int) entry.getValue();
            score += applicant.studentInfo.get(key) * collegeVal; //need corresponding student hashMap
        }
        return score;
    }

    public Map<Integer,Double> getAcceptanceRate(){
        return acceptanceRate;
    }
}