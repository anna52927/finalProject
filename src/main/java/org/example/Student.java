package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//field for what cycle they are admitted in

public class Student {

    //references the College objects when its made will be a list maybe for now i'll make it a string to be able to see how it works
    protected String major;
    protected int diversity;

    protected ArrayList<College> preference;
    //Hash Map
    HashMap<String, Integer> studentInfo = new HashMap<>();

    public Student(int rigor, int classRank, int GPA, int SAT, int essay, int recommendations, int interview, int extraCurriculars, int talent, int character, int firstGen, int alumniRelation, int geoRes, int stateRes, int religion, int raceEthnicStatus, int volunteerWork, int workExp, int levelInt, int cycleNumber, ArrayList<College> collegeRank) {
        studentInfo.put("Rigor of secondary school record", rigor);
        studentInfo.put("Class Rank", classRank);
        studentInfo.put("Academic GPA", GPA);
        studentInfo.put("Standardized test scores", SAT);
        studentInfo.put("Application Essay", essay);
        studentInfo.put("Recommendation(s)", recommendations);
        studentInfo.put("Interview",interview);
        studentInfo.put("Extracurricular activities",extraCurriculars);
        studentInfo.put("Talent/Ability",talent);
        studentInfo.put("Character/personal qualities",character);
        studentInfo.put("First Generation",firstGen);
        studentInfo.put("Alumni/ae relation",alumniRelation);
        studentInfo.put("Geographical residence",geoRes);
        studentInfo.put("State residency",stateRes);
        studentInfo.put("Religious affiliation/commitment",religion);
        studentInfo.put("Racial/ethnic status",raceEthnicStatus);
        studentInfo.put("Volunteer work",volunteerWork);
        studentInfo.put("Work experience",workExp);
        studentInfo.put("Level of applicant's interest",levelInt);
        studentInfo.put("Application Cycle",cycleNumber);
        preference = collegePref2(collegeRank);

    }

    public ArrayList<College> collegePref2(ArrayList<College> collegeRank){
        ArrayList<College> list = new ArrayList<>();
        ArrayList<Integer> collegePercents = new ArrayList<>();
        //can change these if we want
        collegePercents.add(40);
        collegePercents.add(17);
        collegePercents.add(13);
        collegePercents.add(10);
        collegePercents.add(5);
        collegePercents.add(5);
        collegePercents.add(5);
        collegePercents.add(5);

        for(int i = 0; i < 8; i++ ){
            int probAdd = 0;
            int collegeIndexRemove = 0;

            Random random = new Random();
            int prob = random.nextInt(100)+1;
            for(int j = 0; j < collegePercents.size(); j++){
                if (j == 0 && prob <= collegePercents.get(0)){
                    list.add(collegeRank.get(0));
                }
                //probAdd + 1 represents lower limit and other one is upper
                else if (prob >= (probAdd + 1) && prob <= (collegePercents.get(j) + probAdd)) {
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

    //will this list already be ranked probably not  figure out
    public College decide(ArrayList<College> collegeAct) {
        return collegeAct.get(0);
    }

    public ArrayList<College> getList(){
        return preference;
    }

    //make a get hashmap
    //make it so only 6 schools because columbia and upenn suck
}