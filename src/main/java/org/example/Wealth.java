package org.example;
/*4/8 notes:
* - need to talk through with felix about how to store students as
* parameters in the college class
*  - need to verify with everyone college / wealth class interactions*/

import java.util.ArrayList;
import java.util.HashMap;

public class Wealth {
    private int money;
    private final int TUITION;
    private int pubIm; //public image, on a scale (1-10?, 1-100?)

    public Wealth(int money, int tuition, int PubIm){
        this.money = money;
        TUITION = tuition;
        this.PubIm = PubIm;
    }

    public void payTuition(int numStu){
        money = money + (numStu * TUITION);
    }
    public void updatePubIm(ArrayList<Student> students, int year){
        final double MAJORDISREQ0 = .25; //what distribution of majors the public finds okay
        final double MAJORDISREQ1 = .25;
        final double MAJORDISREQ2 = .25;
        final double MAJORDISREQ3 = .25;
        HashMap<Integer, Double> majorDisReq = new HashMap<>();
        majorDisReq.put(0, MAJORDISREQ0);
        majorDisReq.put(1, MAJORDISREQ1);
        majorDisReq.put(2, MAJORDISREQ2);
        majorDisReq.put(3, MAJORDISREQ3);


        final double DIVDISREQ0 = .25;
        final double DIVDISREQ1 = .25;
        final double DIVDISREQ2 = .25;
        final double DIVDISREQ3 = .25;
        HashMap<Integer, Double> divDisReq = new HashMap<>();
        majorDisReq.put(0, DIVDISREQ0);
        majorDisReq.put(1, DIVDISREQ1);
        majorDisReq.put(2, DIVDISREQ2);
        majorDisReq.put(3, DIVDISREQ3);


        ArrayList<Student> firstYears = new ArrayList<>();
        int pubImChange = 0;

        for (Student student : students){
            if (student.getYear() = year){
                firstYears.add(student);
            }
        }

        int[] majorCounts = new int[4]; // 0, 1, 2, 3
        for (Student student : firstYears) {
            int major = student.getMajor();
            majorCounts[major]++;
        }
        for (int i=0; i<majorCounts.length; i++){
            int major = majorCounts[i];
            double classPercent = ((double) major) / firstYears.size();
            double percentOff = (classPercent - majorDisReq.get(i)); //not sure about this bit
            //this is all subjective
            if (percentOff < .1 && 0 < percentOff){
                pubImChange = pubImChange + 1;
            } else if (percentOff < .2 && .1 < percentOff) {
                pubImChange = pubImChange - 1;
            } else if (percentOff < .4 && .2 < percentOff) {
                pubImChange = pubImChange - 2;
            } else if (percentOff < .6 && .4 < percentOff) {
                pubImChange = pubImChange - 5;
            } else if (percentOff <= 1 && .6 < percentOff) {
                pubImChange = pubImChange - 10;
            }

        }

        int[] diversityCounts = new int[4]; // 0, 1, 2, 3
        for (Student student : firstYears) {
            int firstGen = student.getFirstGen();
            diversityCounts[firstGen]++;//assuming firstGen opperates on 0-3 scale of diversity
        }
        for (int i=0; i<majorCounts.length; i++){
            int div = diversityCounts[i];
            double classPercent = ((double) div) / firstYears.size();
            double percentOff = (classPercent - divDisReq.get(i)); //not sure about this bit
            //this is all subjective
            if (percentOff < .1 && 0 < percentOff){
                pubImChange = pubImChange + 1;
            } else if (percentOff < .2 && .1 < percentOff) {
                pubImChange = pubImChange - 1;
            } else if (percentOff < .4 && .2 < percentOff) {
                pubImChange = pubImChange - 2;
            } else if (percentOff < .6 && .4 < percentOff) {
                pubImChange = pubImChange - 5;
            } else if (percentOff <= 1 && .6 < percentOff) {
                pubImChange = pubImChange - 10;
            }

        }

    pubIm = pubIm +pubImChange;




    }
    public void receiveDonations(ArrayList<Student> alumni, ArrayList<Student> students){

        //go through alumni donations by major expected salary (ability to give)
        //go through almni donations by pubim (willingness to give)
    }


}
