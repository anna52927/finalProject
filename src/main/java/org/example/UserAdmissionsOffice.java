
package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserAdmissionsOffice extends AdmissionsOffice {
    private Map<String,Integer> importance;  //ranked table of importance



    public UserAdmissionsOffice(UserCollege college,double initialAcceptanceRate,int majorCutoff,int diversityCutoff,double EDAdmitCapacity,double initialYieldRate){
        super(college,initialAcceptanceRate,majorCutoff,diversityCutoff,EDAdmitCapacity,initialYieldRate);
        //acceptanceRate = new HashMap<>();
        //acceptanceRate.put(0,initialAcceptanceRate);
        //importance = college.userCollegeInfo;
        //diversityDistributions = new HashMap<String,Integer>();

        //i dont know if we can make this a subclass now because the constructor in admissions
        //office takes in a college object but user college is its own thing separate from the
        //college class. Could change what the constructor takes in

    }

    public void chooseAdmissionsOfficeInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the initial acceptance rate of your college: ");
        super.acceptanceRate.put(0, scanner.nextDouble());
        System.out.println("What is the major cutoff number: ");
        super.majorCutoff = scanner.nextInt();
        System.out.println("What is the diversity cutoff number: ");
        super.diversityCutoff = scanner.nextInt();
        System.out.println("Enter the percentage of applicants that will be let in ED: ");
        super.EDAdmitCapacity = scanner.nextDouble();
        System.out.println("Enter the initial yield rate of your college: ");
        super.yieldRate = scanner.nextDouble();
        scanner.close();

        //major distributions fill hash map
        /*
        System.out.println("Enter the percentage of students that can major in Agriculture: ");
        majorDistributions.put("Agriculture",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Natural resources and conservation: ");
        majorDistributions.put("Natural resources and conservation",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Architecture: ");
        majorDistributions.put("Architecture",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Area, ethnic, and gender studies: ");
        majorDistributions.put("Area, ethnic, and gender studies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Communication/journalism: ");
        majorDistributions.put("Communication/journalism",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Communication technologies: ");
        majorDistributions.put("Communication technologies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Computer and information sciences: ");
        majorDistributions.put("Computer and information sciences",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Personal and culinary services: ");
        majorDistributions.put("Personal and culinary services",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Education: ");
        majorDistributions.put("Education",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Engineering: ");
        majorDistributions.put("Engineering",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Engineering technologies: ");
        majorDistributions.put("Engineering technologies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Foreign languages, literatures, and linguistics: ");
        majorDistributions.put("Foreign languages, literatures, and linguistics",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Family and consumer sciences: ");
        majorDistributions.put("Family and consumer sciences",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Law/legal studies: ");
        majorDistributions.put("Law/legal studies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in English: ");
        majorDistributions.put("English",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Liberal arts/general studies: ");
        majorDistributions.put("Liberal arts/general studies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Library science: ");
        majorDistributions.put("Library science",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Biological/life sciences: ");
        majorDistributions.put("Biological/life sciences",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Mathematics and statistics: ");
        majorDistributions.put("Mathematics and statistics",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Military science and military technologies: ");
        majorDistributions.put("Military science and military technologies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Interdisciplinary studies: ");
        majorDistributions.put("Interdisciplinary studies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Parks and recreation: ");
        majorDistributions.put("Parks and recreation",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Philosophy and religious studies: ");
        majorDistributions.put("Philosophy and religious studies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Theology and religious vocations: ");
        majorDistributions.put("Theology and religious vocations",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Physical sciences: ");
        majorDistributions.put("Physical sciences",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Science technologies: ");
        majorDistributions.put("Science technologies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Psychology: ");
        majorDistributions.put("Psychology",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Homeland Security, law enforcement, firefighting, and protective services: ");
        majorDistributions.put("Homeland Security, law enforcement, firefighting, and protective services",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Public administration and social services: ");
        majorDistributions.put("Public administration and social services",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Social sciences: ");
        majorDistributions.put("Social sciences",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Construction trades: ");
        majorDistributions.put("Construction trades",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Mechanic and repair technologies: ");
        majorDistributions.put("Mechanic and repair technologies",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Precision production: ");
        majorDistributions.put("Precision production",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Transportation and materials moving: ");
        majorDistributions.put("Transportation and materials moving",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Visual and performing arts: ");
        majorDistributions.put("Visual and performing arts",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Health professions and related programs: ");
        majorDistributions.put("Health professions and related programs",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in Business/marketing: ");
        majorDistributions.put("Business/marketing",scanner.nextDouble());
        System.out.println("Enter the percentage of students that can major in History: ");
        majorDistributions.put("History",scanner.nextDouble());
         */

        scanner.close();
        //What's going on with diversity distributions
    }



}

