package org.example;

import java.util.ArrayList;
import java.util.Map;

public class AdmissionsOffice {
    private College self;  //idk what to name this
    private Map<String,Object> importance;  //ranked table of importance

    public AdmissionsOffice(Map<String,Object> importance, College college){
        this.self = college; //wow, this line looks cursed
        this.importance = importance;
    }

    public ArrayList<Student> considerApplicants(ArrayList<Student> applicants){
        for (Student applicant:
                applicants) {
            applicant.setScore(evaluateApplicant(applicant)); //needs setScore method
        }
        //sort algorithm (steal from test)
        return applicants[:capacity-1] //however you do this in java (python superiority moment)
    }

    public double evaluateApplicant(Student applicant){
        //Kein bock drauf
        //wirklich keine laune
        return 4.0;
    }
}
