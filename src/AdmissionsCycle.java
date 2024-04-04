/*things this class needs from other classes to run the way it is now:
* - Student should have a method getList to get each student's preference list
* - College should have name field
* - College should have method considerApplicants which takes in a list of applicants
* and the round (ED vs RA), and returns a list of accepted students (for ED, method should
* also enroll students)
* - Student should have a method decide which takes in a list of colleges that accepted them
* and returns the college they choose
* - College should have a method enroll which enrolls a student, void
* */

import java.util.ArrayList;
public class AdmissionsCycle {
    private ArrayList<Student> students;
    private ArrayList<College> colleges;
    private String round;



    public AdmissionsCycle(ArrayList<Student> students, ArrayList<College> colleges){
        this.students = students;
        this.colleges = colleges;
        round = "ED";

    }
    /*
    public void earlyRound(){
       for(int i = 0; i<students.size(); i++){
           College college = students.get(i).getList.get(0);//the first college on a student's list
           Boolean decision = college.considerApplicant(students.get(i), round); // considerApplicant change to return boolean
           if (decision){
               college.getAttendingStudents.add(students.get(i));
               college.enroll(students.get(i)); //two ways to do this, or have it handled in college
               students.remove(students.get(i));
           }
       }
    }

     */
    public void earlyRound2(){ //all applicants passed into consideration
        ArrayList<Student> Aapplicants = new ArrayList<Student>();
        ArrayList<Student> Bapplicants = new ArrayList<Student>();
        ArrayList<Student> Capplicants = new ArrayList<Student>();

        for(int i = 0; i<students.size(); i++){
            College college = students.get(i).getList.get(0);//the first college on a student's list
            //adds each student to the applying list for that college
            switch(college.name){
                case "A":
                    Aapplicants.add(students.get(i));
                    break;
                case "B":
                    Bapplicants.add(students.get(i));
                    break;
                case "C":
                    Capplicants.add(students.get(i));
                    break;
            }
        }
        ArrayList<Student> Aattending = colleges.get(0).considerApplicants(Aapplicants, round); //method could return list of accepted students
        ArrayList<Student> Battending = colleges.get(1).considerApplicants(Bapplicants, round); //so that they can be taken out of consideration for other colleges
        ArrayList<Student> Cattending = colleges.get(2).considerApplicants(Capplicants, round);
        //handle enrolling withing considerApplicants (ED is binding)

        students.removeAll(Aattending);
        students.removeAll(Battending);
        students.removeAll(Cattending);

        //update college for student (what college student is going to from student perspective)

    }
    public void regularRound(){
        round = "RA";
        ArrayList<Student> Aapplicants = new ArrayList<Student>();
        ArrayList<Student> Bapplicants = new ArrayList<Student>();
        ArrayList<Student> Capplicants = new ArrayList<Student>();
        for(int i = 0; i<students.size(); i++) {
            for (int j = 1; j < 2; j++) { //<2 takes B, <3 takes B + C
                College college = students.get(i).getList.get(j);//the next college on a student's list
                //adds each student to the applying list for that college
                switch (college.name) {
                    case "A":
                        Aapplicants.add(students.get(i));
                        break;
                    case "B":
                        Bapplicants.add(students.get(i));
                        break;
                    case "C":
                        Capplicants.add(students.get(i));
                        break;
                }
            }
        }
        //returns list of accepted students
        ArrayList<Student> Aaccepted = colleges.get(0).considerApplicants(Aapplicants, round); //method could return list of accepted students
        ArrayList<Student> Baccepted = colleges.get(1).considerApplicants(Bapplicants, round); //so that they can be taken out of consideration for other colleges
        ArrayList<Student> Caccepted = colleges.get(2).considerApplicants(Capplicants, round);

        for(Student student: students){//for each student, creates list of colleges they got into
            ArrayList<College> acceptedColleges = new ArrayList<College>();
            if (Aaccepted.contains(student)){
                acceptedColleges.add(colleges.get(0));
            }
            if (Baccepted.contains(student)){
                acceptedColleges.add(colleges.get(1));
            }
            if (Caccepted.contains(student)){
                acceptedColleges.add(colleges.get(2));
            }
            College attendingCollege = student.decide(acceptedColleges);
            //passes list of accepted colleges, returns their choice
            attendingCollege.enroll(student);
            //enrolls that student at that college
            students.remove(student);
        }



    }



}
