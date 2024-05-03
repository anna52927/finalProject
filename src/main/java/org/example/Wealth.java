package org.example;
/*4/8 notes:
* - need to talk through with felix about how to store students as
* parameters in the college class
*  - need to verify with everyone college / wealth class interactions*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Wealth {
    public int money;
    private final int TUITION;
    public int pubIm; //public image, on a scale (1-10?, 1-100?)

    public Wealth(int money, int tuition, int pubIm){
        this.money = money;
        TUITION = tuition;
        this.pubIm = pubIm;
    }

    public void payTuition(int numStu){
        money = money + (numStu * TUITION);
    }
    public void updatePubIm(ArrayList<Student> students, int year, HashMap<Integer, Double> acceptanceRate){
        int pubImChange = 0;

        double goalAcceptanceRate = acceptanceRate.get(0);
        double currentAcceptanceRate = acceptanceRate.get(year);
        double rateChange = goalAcceptanceRate - currentAcceptanceRate;
        if (rateChange > 0){ //current accpectance rate smaller than goal, so positive public image change
            pubImChange = pubImChange + ((int)((rateChange/goalAcceptanceRate)*20));//mess with weight there
        }
        else if (rateChange < 0){ //currept acceptance rate is bigger than goal, so negative affect on pubim
            pubImChange = pubImChange + ((int)((rateChange/goalAcceptanceRate)*20));//mess with weight there
        }


        final double MAJORDISREQ0 = .25; //what distribution of majors the public finds okay
        final double MAJORDISREQ1 = .25;
        final double MAJORDISREQ2 = .25;
        final double MAJORDISREQ3 = .25;
        HashMap<String, Double> majorDisReq = new HashMap<>();
        majorDisReq.put("Humanities", MAJORDISREQ0);
        majorDisReq.put("Art", MAJORDISREQ1);
        majorDisReq.put("Math", MAJORDISREQ2);
        majorDisReq.put("Science", MAJORDISREQ3);


        final double DIVDISREQ0 = .25;
        final double DIVDISREQ1 = .25;
        final double DIVDISREQ2 = .25;
        final double DIVDISREQ3 = .25;
        HashMap<Integer, Double> divDisReq = new HashMap<>();
        divDisReq.put(0, DIVDISREQ0);
        divDisReq.put(1, DIVDISREQ1);
        divDisReq.put(2, DIVDISREQ2);
        divDisReq.put(3, DIVDISREQ3);


        ArrayList<Student> firstYears = new ArrayList<>();


        for (Student student : students){
            if (student.getHashMap().get("Application Year") == year){
                firstYears.add(student);
            }
        }

        HashMap<String, Integer> majorCounts = new HashMap<String, Integer>(); // 0, 1, 2, 3
        for (Student student : firstYears) {
            String major = student.getMajor();
            if (!majorCounts.containsKey(major)){
                majorCounts.put(major, 0);
            }

            majorCounts.put(major, majorCounts.get(major)+1);

        }
        for (HashMap.Entry<String, Integer> entry : majorCounts.entrySet()) {
            String major = entry.getKey();
            int numberOfPeopleInMajor = entry.getValue();
            double classPercent = ((double) numberOfPeopleInMajor) / firstYears.size();
            double percentOff = (classPercent - majorDisReq.get(major)); //not sure about this bit
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

            int firstGen = student.getHashMap().get("First generation");
            diversityCounts[firstGen]++;//assuming firstGen opperates on 0-3 scale of diversity
            int diversity = student.getDiversity();
            diversityCounts[diversity]++;//assuming firstGen opperates on 0-3 scale of diversity
        }
        for (int i=0; i<diversityCounts.length; i++){
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
    public void receiveDonations(ArrayList<Student> alumni){
        Random random = new Random();
        int totalDonation = 0;


        final double MAJORSAL0 = 100000; // Major average salary
        final double MAJORSAL1 = 80000;
        final double MAJORSAL2 = 60000;
        final double MAJORSAL3 = 50000;
        final double donationRate = .2;

        final double pubImWeight = .01;
        final double salaryPercent = .01;

        HashMap<String, Double> majorSal = new HashMap<>();
        majorSal.put("Humanities", MAJORSAL0);
        majorSal.put("Art", MAJORSAL1);
        majorSal.put("Math", MAJORSAL2);
        majorSal.put("Science", MAJORSAL3);

        for (Student alum : alumni) {
            double checkRate= random.nextDouble();
            if (checkRate<=donationRate) {
                double pay = majorSal.get(alum.getMajor()); // Get alum's salary based on major
                double pubImRate = (((double) pubIm) / 100) * pubImWeight;
                double donation = pay * pubImRate * salaryPercent;
                totalDonation += donation;
            }
        }
        money = money + totalDonation;
        System.out.println(totalDonation);

    }

    public void receiveClassCumulativeDonations(ArrayList<Student> alumni){
        //System.out.println(alumni.size());
        Random random = new Random();
        int totalDonation = 0;

        final double MAJORSAL0 = 100000; // Major average salary
        final double MAJORSAL1 = 80000;
        final double MAJORSAL2 = 60000;
        final double MAJORSAL3 = 50000;
        final double raiseRate = 1.04;
        final double donationRate = .2;

        final double pubImWeight = .01;
        final double salaryPercent = .01;

        HashMap<String, Double> majorSal = new HashMap<>();
        majorSal.put("English", MAJORSAL0);
        majorSal.put("Art", MAJORSAL1);
        majorSal.put("Math", MAJORSAL2);
        majorSal.put("Science", MAJORSAL3);

        for(int i = 0; i<50; i++){
            for (Student alum : alumni) {
                double checkRate = random.nextDouble();
                if (checkRate <= donationRate) {
                    double pay = majorSal.get(alum.getMajor()) * (Math.pow(raiseRate,i));
                    ; // Get alum's salary based on major
                    double pubImRate = (((double) pubIm) / 100) * pubImWeight;
                    double donation = pay * pubImRate * salaryPercent;
                    totalDonation += donation;
                }
            }
        }
        System.out.println(totalDonation);
        money = money + totalDonation;

    }

}
