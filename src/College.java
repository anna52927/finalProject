import java.util.ArrayList;

public class College {
    private int grades;
    private int testScores;
    private int coCurricular;
    private int capacity;

    public College(int grades, int testScores, int coCurricular, int capacity){
        this.grades = grades;
        this.testScores = testScores;
        this.coCurricular = coCurricular;
        this.capacity = capacity;
    }
    public ArrayList<Student> considerApplicants(ArrayList<Student> applicants){
        for (Student applicant:
                applicants) {
            evaluateApplicant(applicant);
        }
        //sort algorithm
        return applicants[:capacity-1] //however you do this in java
    }
}
