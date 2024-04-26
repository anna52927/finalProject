
package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserAdmissionsOffice extends AdmissionsOffice {
    private College self;
    private Map<String,Integer> importance;
    private HashMap<Integer,Double> acceptanceRate;
    public UserAdmissionsOffice(UserCollege college, double initialAcceptanceRate){
        super(college, initialAcceptanceRate);
        acceptanceRate = new HashMap<>();
        acceptanceRate.put(0,initialAcceptanceRate);
        importance = college.userCollegeInfo;

        //i dont know if we can make this a subclass now because the constructor in admissions
        //office takes in a college object but user college is its own thing separate from the
        //college class. Could change what the constructor takes in

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
        for (Map.Entry<String, Integer> entry : importance.entrySet()) {
            String key = entry.getKey();
            int collegeVal =  entry.getValue();
            score += applicant.studentInfo.get(key) * collegeVal; //need corresponding student hashMap
        }
        return score;
    }

}