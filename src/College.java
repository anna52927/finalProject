package org.example;

import java.util.ArrayList;

public class College {
    private int grades;
    private int testScores;
    private int coCurricular;

    public College(int grades, int testScores, int coCurricular){
        this.grades = grades;
        this.testScores = testScores;
        this.coCurricular = coCurricular;
    }
    private boolean makeDecision(ArrayList<Student> applicants){
        for (Student applicant :
                applicants) {
            evaluateApplicant(applicant);
        }
    }
}
