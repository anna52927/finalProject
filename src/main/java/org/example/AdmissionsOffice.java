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
        //sort algorithm (steal from test)
        return applicants[:capacity-1] //however you do this in java (python superiority moment)
    }

    public double evaluateApplicant(Student applicant){
        int score = 0;
        for(Map.Entry<String,Object> entry : importance.entrySet()){
            String key = entry.getKey();
            int collegeVal = (int)entry.getValue();
            score += applicant.basicVals.get(key) * collegeVal; //need corresponding student hashMap
        }
        return score;
    }
}
