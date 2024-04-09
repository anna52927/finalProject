package org.example;
import java.util.ArrayList;
import java.util.Random;

//field for what cycle they are admitted in

public class Student {
    //Right now everything int because student will have a 0-3 in this area
    protected int SATScore;
    protected int GPA;
    protected int major;
    protected int extraCurriculars;
    protected int essay;
    protected int firstGen;

    //references the College objects when its made will be a list maybe for now i'll make it a string to be able to see how it works
    protected ArrayList<College> preference;

    public Student(int SATScore, int GPA, int major, int extraCurriculars, int essay, int firstGen, ArrayList<College> collegeRank) {
        this.SATScore = SATScore;
        this.GPA = GPA;
        this.major = major;
        this.extraCurriculars = extraCurriculars;
        this.essay = essay;
        this.firstGen = firstGen;
        preference = collegePreferenceGen(collegeRank);

    }

    //would take in the list of colleges ranked TAKES IN STRING TO TEST
    //WILL HAVE TO CHANGE
    //also doing all 8 colleges
    public ArrayList<College> collegePreferenceGen(ArrayList<College> collegeRank) {
        Random random = new Random();
        int prob = random.nextInt(1, 100);
        ArrayList<College> list = new ArrayList<>();

        //pick first college
        if (prob >= 1 && prob <= 40) {
            list.add(collegeRank.get(0));
        } else if (prob >= 41 && prob <= 57) {
            list.add(collegeRank.get(1));
        } else if (prob >= 58 && prob <= 70) {
            list.add(collegeRank.get(2));
        } else if (prob >= 71 && prob <= 80) {
            list.add(collegeRank.get(3));
        } else if (prob >= 81 && prob <= 85) {
            list.add(collegeRank.get(4));
        } else if (prob >= 86 && prob <= 90) {
            list.add(collegeRank.get(5));
        } else if (prob >= 91 && prob <= 95) {
            list.add(collegeRank.get(6));
        } else if (prob >= 96 && prob <= 100) {
            list.add(collegeRank.get(7));
        }
        collegeRank.remove(list.get(0));

        //second college
        prob = random.nextInt(1, 100);
        if (prob >= 1 && prob <= 50) {
            list.add(collegeRank.get(0));
        } else if (prob >= 51 && prob <= 70) {
            list.add(collegeRank.get(1));
        } else if (prob >= 71 && prob <= 80) {
            list.add(collegeRank.get(2));
        } else if (prob >= 81 && prob <= 85) {
            list.add(collegeRank.get(3));
        } else if (prob >= 86 && prob <= 90) {
            list.add(collegeRank.get(4));
        } else if (prob >= 91 && prob <= 95) {
            list.add(collegeRank.get(5));
        } else if (prob >= 96 && prob <= 100) {
            list.add(collegeRank.get(6));
        }
        collegeRank.remove(list.get(1));

        //third college
        prob = random.nextInt(1, 100);
        if (prob >= 1 && prob <= 50) {
            list.add(collegeRank.get(0));
        } else if (prob >= 51 && prob <= 70) {
            list.add(collegeRank.get(1));
        } else if (prob >= 71 && prob <= 85) {
            list.add(collegeRank.get(2));
        } else if (prob >= 86 && prob <= 90) {
            list.add(collegeRank.get(3));
        } else if (prob >= 91 && prob <= 95) {
            list.add(collegeRank.get(4));
        } else if (prob >= 96 && prob <= 100) {
            list.add(collegeRank.get(5));
        }
        collegeRank.remove(list.get(2));

        //fourth college
        prob = random.nextInt(1, 100);
        if (prob >= 1 && prob <= 50) {
            list.add(collegeRank.get(0));
        } else if (prob >= 51 && prob <= 75) {
            list.add(collegeRank.get(1));
        } else if (prob >= 76 && prob <= 90) {
            list.add(collegeRank.get(2));
        } else if (prob >= 91 && prob <= 95) {
            list.add(collegeRank.get(3));
        } else if (prob >= 96 && prob <= 100) {
            list.add(collegeRank.get(4));
        }
        collegeRank.remove(list.get(3));

        //fifth college
        prob = random.nextInt(1, 100);
        if (prob >= 1 && prob <= 50) {
            list.add(collegeRank.get(0));
        } else if (prob >= 51 && prob <= 80) {
            list.add(collegeRank.get(1));
        } else if (prob >= 81 && prob <= 90) {
            list.add(collegeRank.get(2));
        } else if (prob >= 91 && prob <= 100) {
            list.add(collegeRank.get(3));
        }
        collegeRank.remove(list.get(4));

        //sixth college
        prob = random.nextInt(1, 100);
        if (prob >= 1 && prob <= 50) {
            list.add(collegeRank.get(0));
        } else if (prob >= 51 && prob <= 75) {
            list.add(collegeRank.get(1));
        } else if (prob >= 76 && prob <= 100) {
            list.add(collegeRank.get(2));
        }
        collegeRank.remove(list.get(5));

        //seventh college
        prob = random.nextInt(1, 100);
        if (prob >= 1 && prob <= 50) {
            list.add(collegeRank.get(0));
        } else if (prob >= 51 && prob <= 100) {
            list.add(collegeRank.get(1));
        }
        collegeRank.remove(list.get(6));

        //eighth college
        list.add(collegeRank.get(0));

        System.out.println(list);
        return list;


    }
    public ArrayList<College> collegePref2(ArrayList<College> collegeRank){
        ArrayList<College> list = new ArrayList<>();
        ArrayList<Integer> collegePercents = new ArrayList<>();
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
            int prob = random.nextInt(1, 100);

            for(int j = 0; j < collegePercents.size(); j++){
                System.out.println(collegePercents.get(j));
                System.out.println(prob);
                System.out.println(probAdd);
                if (j == 0 && prob >= collegePercents.get(0)){
                    System.out.println(prob);
                    list.add(collegeRank.get(0));
                }

                //probAdd + 1 represents lower limit and other one is upper
                else if (prob <= (probAdd + 1) && prob <= collegePercents.get(j)) {
                    list.add(collegeRank.get(j));
                    collegeIndexRemove = j;
                }
                probAdd = probAdd + collegePercents.get(j);
            }
            probAdd = probAdd - collegePercents.get(collegeIndexRemove);
            collegeRank.remove(collegeIndexRemove);
            collegePercents.remove(collegeIndexRemove);
            System.out.println(probAdd);
            System.out.println("2");

            for(int k = 0; k < collegePercents.size(); k++){
                float newProb = (float) collegePercents.get(k) /probAdd * 100;
                collegePercents.set(k, (int) newProb);
            }
        }
        System.out.println(list);
        return list;
    }
    public College decide(ArrayList<College> collegeAct) {
        return collegeAct.get(0);
    }

    public ArrayList<College> getList(){
        return preference;
    }
}