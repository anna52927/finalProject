package org.example;
/*4/8 notes:
 * - need to talk through with felix about how to store students as
 * parameters in the college class
 *  - need to verify with everyone college / wealth class interactions*/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Wealth {
    public int money;
    public int TUITION;
    public int pubIm; //public image, on a scale (1-10?, 1-100?)
    public HashMap<String, Double[]> majorInfo;

    public Wealth(int money, int tuition, int pubIm){
        this.money = money;
        TUITION = tuition;
        this.pubIm = pubIm;
        this.majorInfo = new HashMap<>();
        majorInfo.put("Agriculture", new Double[]{55000.0, 0.02});
        majorInfo.put("Natural resources and conservation", new Double[]{56000.0, 0.01});
        majorInfo.put("Architecture", new Double[]{70000.0, 0.08});
        majorInfo.put("Area, ethnic, and gender studies", new Double[]{58000.0, 0.01});
        majorInfo.put("Communication/journalism", new Double[]{57000.0, 0.05});
        majorInfo.put("Computer and information sciences", new Double[]{90000.0, 0.1});
        majorInfo.put("Education", new Double[]{50000.0, 0.01});
        majorInfo.put("Engineering", new Double[]{97000.0, 0.1});
        majorInfo.put("Foreign languages, literatures, and linguistics", new Double[]{59000.0, 0.05});
        majorInfo.put("Family and consumer sciences", new Double[]{47000.0, 0.01});
        majorInfo.put("English", new Double[]{56000.0, 0.05});
        majorInfo.put("Liberal arts/general studies", new Double[]{54000.0, 0.05});
        majorInfo.put("Biological/life sciences", new Double[]{70000.0, 0.1});
        majorInfo.put("Mathematics and statistics", new Double[]{78000.0, 0.1});
        majorInfo.put("Interdisciplinary studies", new Double[]{55000.0, 0.02});
        majorInfo.put("Philosophy and religious studies", new Double[]{60000.0, 0.02});
        majorInfo.put("Physical sciences", new Double[]{74000.0, 0.02});
        majorInfo.put("Psychology", new Double[]{53000.0, 0.02});
        majorInfo.put("Public administration and social services", new Double[]{50000.0, 0.02});
        majorInfo.put("Social sciences", new Double[]{68000.0, 0.1});
        majorInfo.put("Visual and performing arts", new Double[]{45000.0, 0.08});
        majorInfo.put("Health professions and related programs", new Double[]{65000.0, 0.1});
        majorInfo.put("Business/marketing", new Double[]{69000.0, 0.2});
        majorInfo.put("History", new Double[]{63000.0, 0.0});

    }

    public void payTuition(int numStu){
        money = money + (numStu * TUITION);
    }
    public void updatePubIm(ArrayList<Student> students, int year, HashMap<Integer, Double> acceptanceRate, College college){
        int pubImChange = 0;


        double goalAcceptanceRate = acceptanceRate.get(0);
        double currentAcceptanceRate = acceptanceRate.get(year);
        double rateChange = goalAcceptanceRate - currentAcceptanceRate;
        //System.out.println("rate change: " +rateChange);
        if (rateChange > 0){
            //current accpectance rate smaller than goal, so positive public image change
            pubImChange = pubImChange + ((int)((rateChange)*500));//mess with weight there

        }
        else if (rateChange < 0){
            //currept acceptance rate is bigger than goal, so negative affect on pubim
            pubImChange = pubImChange + ((int)((rateChange)*500));//mess with weight there
        }
        //System.out.println("goal acceptance rate: " +goalAcceptanceRate + ", acceptance rate = "+ currentAcceptanceRate);
        //System.out.println("public image change after acceptance rate: " +pubImChange);


        //each college has majordistributions map



        HashMap<String, Object> majorDisReq = college.admissions.majorDistributions; //#@!R$@!G#T#!@?!!!!


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
            if (student.getHashMap().get("Application Cycle") == year){
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

        //System.out.println(majorCounts);
        for (HashMap.Entry<String, Integer> entry : majorCounts.entrySet()) {
            String major = entry.getKey();
            int numberOfPeopleInMajor = entry.getValue();
            double numberOff = (numberOfPeopleInMajor - (Double)majorDisReq.get(major)); //not sure about this bit
            //System.out.println(percentOff);
            //this is all subjective
            //System.out.println("pubImChange before: " + pubImChange);
            if (numberOff < .1 && 0 < numberOff){
                pubImChange = pubImChange + 1;
            } else if (numberOff < .2 && .1 < numberOff) {
                pubImChange = pubImChange - 1;
            } else if (numberOff < .4 && .2 < numberOff) {
                pubImChange = pubImChange - 2;
            } else if (numberOff < .6 && .4 < numberOff) {
                pubImChange = pubImChange - 3;
            } else if (numberOff <= 1 && .6 < numberOff) {
                pubImChange = pubImChange - 5;
            }
            //System.out.println("pubImChange after: " + pubImChange);

        }

        int[] diversityCounts = new int[4]; // 0, 1, 2, 3
        for (Student student : firstYears) {

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
        double averageAcademics = 0;
        double totalAcademics = 0;
        for (Student student: students){
            totalAcademics += ((double)(student.getHashMap().get("Rigor of secondary school record")+student.getHashMap().get("Class rank")+student.getHashMap().get("Academic GPA")+student.getHashMap().get("Standardized test scores")+student.getHashMap().get("Application Essay")+student.getHashMap().get("Talent/ability")))/6;
        }
        averageAcademics = totalAcademics/students.size();
        pubImChange += averageAcademics *2;

        pubIm +=pubImChange;

        double averageSport = 0;
        int totalSport = 0;
        for (Student student: students){
            totalSport += student.getHashMap().get("Extracurricular activities");
        }
        averageSport = totalSport/students.size();
        pubImChange += averageSport *2;

        //System.out.println("Public Image: " + pubIm);




    }
    public void receiveDonations(ArrayList<Student> alumni){
        Random random = new Random();

        int totalDonation = 0;
        final double donationRate = .2;
        final double pubImWeight = .001;
        final double salaryPercent = .05;

        for (Student alum : alumni) {
            double checkRate= random.nextDouble();
            if (checkRate<=donationRate) {

                double salary = 0;
                double specialChecker = random.nextDouble();

                double averageAcademics = ((double)(alum.getHashMap().get("Rigor of secondary school record")+alum.getHashMap().get("Class rank")+alum.getHashMap().get("Academic GPA")+alum.getHashMap().get("Standardized test scores")+alum.getHashMap().get("Application Essay"))/5);

                if (specialChecker<=majorInfo.get(alum.getMajor())[1]) {

                    salary = (majorInfo.get(alum.getMajor())[0]*10 + 20000 * random.nextGaussian()) ;
                }
                else{
                    salary = (majorInfo.get(alum.getMajor())[0] + 20000 * random.nextGaussian()) ;
                }
                salary = salary + averageAcademics*10000;



                // Get alum's salary based on major
                double pubImRate = (((double) pubIm) / 100) * pubImWeight;
                double donation = salary * pubImRate * salaryPercent;
                totalDonation += donation;
            }
        }
        money = money + totalDonation;
        //System.out.println(totalDonation);

    }

    public void receiveClassCumulativeDonations(ArrayList<Student> alumni){
        //System.out.println(alumni.size());
        Random random = new Random();
        int totalDonation = 0;


        final double raiseRate = 1.04;
        final double donationRate = .2;
        final double pubImWeight = .001;
        final double salaryPercent = .05;


        HashMap<Student, Double> studentSalary = new HashMap<>();



        for (Student alum : alumni) {

            double salary = 0;
            double specialChecker = random.nextDouble();

            double averageAcademics = ((double)(alum.getHashMap().get("Rigor of secondary school record")+alum.getHashMap().get("Class rank")+alum.getHashMap().get("Academic GPA")+alum.getHashMap().get("Standardized test scores")+alum.getHashMap().get("Application Essay"))/5);

            if (specialChecker<=majorInfo.get(alum.getMajor())[1]) {

                salary = (majorInfo.get(alum.getMajor())[0]*10 + 20000 * random.nextGaussian()) ;
            }
            else{
                salary = (majorInfo.get(alum.getMajor())[0] + 20000 * random.nextGaussian()) ;
            }
            salary = salary + averageAcademics*10000;
            studentSalary.put(alum, salary);

        }


        for(int i = 0; i<50; i++){
            for (Student alum : alumni) {
                double checkRate = random.nextDouble();
                if (checkRate <= donationRate) {
                    double pay = (studentSalary.get(alum)  * (Math.pow(raiseRate,i)));
                    ; // Get alum's salary based on major
                    double pubImRate = (((double) pubIm)  * pubImWeight);
                    double donation = pay * pubImRate * salaryPercent;
                    totalDonation += donation;
                    //System.out.println(donation);
                }
            }
        }
        //System.out.println(totalDonation);
        money = money + totalDonation;

    }

}
