package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;



public class Student {
    protected String major;
    protected String diversity;
    protected ArrayList<College> preference;
    //Hash Map
    HashMap<String, Integer> studentInfo = new HashMap<>();
    private double score;

    public Student(int rigor, int classRank, int GPA, int SAT, int essay, int recommendations, int interview, int extraCurriculars, int talent, int character, int firstGen, int alumniRelation, int geoRes, int stateRes, int religion, int raceEthnicStatus, int volunteerWork, int workExp, int levelInt, int cycleNumber, int year, ArrayList<College> collegeRank, String major, String diversity) {
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
        preference = collegePrefGen(collegeRank);
        this.major = major;
        this.diversity = diversity;


    }



    public ArrayList<College> collegePrefGen(ArrayList<College> collegeRank){
        ArrayList<College> list = new ArrayList<>();
        ArrayList<Integer> collegePercents = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
        ArrayList<Integer> copyCollegeRank = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6));


        //have to change to seven so edit everything to fit
        for(int k = 0; k < 7; k++){
            double pubImTotal = 0; //the denominator for the fraction
            int hundredCheck = 0; //makes sure it adds up to 100 random number is in range

            //adding up all public images
            for (Integer integer : copyCollegeRank) {
                pubImTotal = pubImTotal + collegeRank.get(integer).getWealth().pubIm;
            }

            //sets the new percentages with their public image / pubImTotal
            for(int j = 0; j < copyCollegeRank.size(); j++ ){
                collegePercents.set(j, (int) Math.round(((collegeRank.get(copyCollegeRank.get(j)).getWealth().pubIm) / pubImTotal) * 100.0));
                hundredCheck = hundredCheck + collegePercents.get(j);
            }

            //checks if hundred, if not adds or subtracts from top school
            if (hundredCheck != 100){
                if(hundredCheck > 100){
                    collegePercents.set(0, (collegePercents.get(0) - (hundredCheck - 100)));
                }
                else{
                    collegePercents.set(0, (collegePercents.get(0) + (100 - hundredCheck)));
                }
            }


            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            double probAdd = 0; //the bottom range
            int collegeIndexRemove = 0; // tells which index to remove from the copy and percent lists

            for(int l = 0; l < collegePercents.size(); l++){
                if (l == 0 && randomNumber <= collegePercents.get(0)){
                    //System.out.println("college percent = " + collegePercents);
                    //System.out.println("college rank = " + collegeRank );
                    list.add(collegeRank.get(copyCollegeRank.get(0)));
                }
                else if (randomNumber >= (probAdd + 0.01) && randomNumber <= (collegePercents.get(l) + probAdd)) {
                    list.add(collegeRank.get(copyCollegeRank.get(l)));
                    collegeIndexRemove = l;
                }
                probAdd = probAdd + collegePercents.get(l);

            }
            copyCollegeRank.remove(collegeIndexRemove);
            collegePercents.remove(collegeIndexRemove);
        }

        //System.out.println("End List: " + list);
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
    public String getDiversity(){return diversity;}

    //Is major a String or Int for now?
    public String getMajor(){return major;}

    public void setScore(double score){
        this.score = score;
    }

    public double getScore(){
        return score;
    }
}