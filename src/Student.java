import java.util.ArrayList;

public class Student {
    //Right now everything int because student will have a 0-3 in this area
    protected int SATScore;
    protected int GPA;
    protected int major;
    protected int extraCurriculars;
    protected int essay;
    protected int firstGen;

    //references the College object when its made will be a list maybe for now i'll make it a string
    protected ArrayList<String> preference;
    public Student(int SATScore, int GPA, int major, int extraCurriculars, int essay, int firstGen){
        this.SATScore = SATScore;
        this.GPA = GPA;
        this.major = major;
        this.extraCurriculars = extraCurriculars;
        this.essay = essay;
        this.firstGen = firstGen;

    }
    public static ArrayList<String> collegePreferenceGen(){
    }
}
