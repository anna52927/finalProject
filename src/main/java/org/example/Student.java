package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;



public class Student {
    protected int major;
    protected int diversity;
    protected ArrayList<College> preference;
    //Hash Map
    HashMap<String, Integer> studentInfo = new HashMap<>();
    private double score;

    public Student(int rigor, int classRank, int GPA, int SAT, int essay, int recommendations, int interview, int extraCurriculars, int talent, int character, int firstGen, int alumniRelation, int geoRes, int stateRes, int religion, int raceEthnicStatus, int volunteerWork, int workExp, int levelInt, int cycleNumber, int year, ArrayList<College> collegeRank) {
        score = 0;
        studentInfo.put("Rigor of secondary school record", rigor);
        studentInfo.put("Class rank", classRank);
        studentInfo.put("Academic GPA", GPA);
        studentInfo.put("Standardized test scores", SAT);
        studentInfo.put("Application Essay", essay);
        studentInfo.put("Recommendation(s)", recommendations);
        studentInfo.put("Interview",interview);
        studentInfo.put("Extracurricular activities",extraCurriculars);
        studentInfo.put("Talent/ability",talent);
        studentInfo.put("Character/personal qualities",character);
        studentInfo.put("First generation",firstGen);
        studentInfo.put("Alumni/ae relation",alumniRelation);
        studentInfo.put("Geographical residence",geoRes);
        studentInfo.put("State residency",stateRes);
        studentInfo.put("Religious affiliation/commitment",religion);
        studentInfo.put("Racial/ethnic status",raceEthnicStatus);
        studentInfo.put("Volunteer work",volunteerWork);
        studentInfo.put("Work experience",workExp);
        studentInfo.put("Level of applicant\u2019s interest",levelInt);
        studentInfo.put("Application Cycle",cycleNumber);
        studentInfo.put("Application Year", year);
        preference = collegePrefGen2(collegeRank);

    }


    public ArrayList<College> collegePrefGen2(ArrayList<College> collegeRank){
        return collegeRank;
    }

    public ArrayList<College> collegePrefGen(ArrayList<College> collegeRank){
        ArrayList<College> list = new ArrayList<>();
        ArrayList<Integer> collegePercents = new ArrayList<>();
        //can change these if we want
        collegePercents.add(40);
        collegePercents.add(20);
        collegePercents.add(17);
        collegePercents.add(13);
        collegePercents.add(5);
        collegePercents.add(5);

        for(int i = 0; i < 6; i++ ){
            int probAdd = 0;
            int collegeIndexRemove = 0;

            Random random = new Random();
            int prob = random.nextInt(100)+1;
            for(int j = 0; j < collegePercents.size(); j++){
                if (j == 0 && prob <= collegePercents.get(0)){
                    System.out.println(collegeRank);
                    list.add(collegeRank.get(0));
                }
                //probAdd + 1 represents lower limit and other one is upper
                else if (prob >= (probAdd + 1) && prob <= (collegePercents.get(j) + probAdd) && (collegeRank.size() != 0)) {
                    System.out.println("college percent = " + collegePercents);
                    System.out.println("college rank = " + collegeRank );
                    list.add(collegeRank.get(j));
                    collegeIndexRemove = j;
                }
                probAdd = probAdd + collegePercents.get(j);
            }
            probAdd = probAdd - collegePercents.get(collegeIndexRemove);
            collegeRank.remove(collegeIndexRemove);
            collegePercents.remove(collegeIndexRemove);

            //sometimes the percentage doesn't add up to 100 (fixes it)
            int hundredCheck = 0;
            for(int k = 0; k < collegePercents.size(); k++){
                float newProb = (float) collegePercents.get(k) /probAdd * 100;
                collegePercents.set(k, (int) newProb);
                hundredCheck = hundredCheck + collegePercents.get(k);
            }
            if (hundredCheck < 100 && hundredCheck != 0){
                collegePercents.set(0, (collegePercents.get(0) + (100 - hundredCheck)));
            }
        }
        System.out.println(list);
        return list;
    }

    public College decide(ArrayList<College> collegeAct) {
        int min = 5;
        for (College college : collegeAct) {
            for (int j = 0; j < preference.size(); j++) {
                if ((college == preference.get(j) && j < min)) {
                    min = j;
                }
            }
        }
        return preference.get(min);
    }


    public HashMap<String, Integer> getHashMap(){
        return studentInfo;
    }
    public ArrayList<College> getList(){
        return preference;
    }
    public int getDiversity(){return diversity;}

    //Is major a String or Int for now?
    public int getMajor(){return major;}

    public void setScore(double score){
        this.score = score;
    }

    public double getScore(){
        return score;
    }
}