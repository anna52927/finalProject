package org.example;

import java.util.ArrayList;
import java.util.Map;

public class AdmissionsOffice {
    private College self;  //idk what to name this
    private Map<String,Object> importance;  //ranked table of importance

    public AdmissionsOffice(College college){
        this.self = college; //wow, this line looks cursed
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
        return admittedStudents;
    }

    public double evaluateApplicant(Student applicant){
        int score = 0;
        for(Map.Entry<String,Object> entry : importance.entrySet()){
            String key = entry.getKey();
            int collegeVal = (int)entry.getValue();
            score += applicant.studentInfo.get(key) * collegeVal; //need corresponding student hashMap
        }
        return score;
    }
}
