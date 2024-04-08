package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class College {
    private int grades;
    private int testScores;
    private int coCurricular;
    private int capacity;
    private Map<String,Object> importance;  //ranked table of importance

    public College(int grades, int testScores, int coCurricular, int capacity, Map<String,Object> importance){
        this.grades = grades;
        this.testScores = testScores;
        this.coCurricular = coCurricular;
        this.capacity = capacity;
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
        return 4.0;
    }
}

