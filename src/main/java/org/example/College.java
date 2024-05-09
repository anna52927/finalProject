package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class College {
    public String name;
    public int capacity;
    public ArrayList<Student> attendingStudents;
    public ArrayList<Student> alumni;
    public AdmissionsOffice admissions;
    private Wealth wealth;
    public int EDAdmitCapacity;


    public College(String name, int capacity, int tuition, int pubIm, double initialAcceptanceRate, int majorCutoff, int diversityCutoff, double EDAdmitPercent, boolean isUserCollege){
        this.capacity = capacity;
        this.name = name;
        attendingStudents = new ArrayList<Student>();
        alumni = new ArrayList<Student>();
        EDAdmitCapacity = (int)EDAdmitPercent * capacity;
        wealth = new Wealth(0,tuition,pubIm);
        if (!isUserCollege){
            System.out.println("ran");
            admissions = new AdmissionsOffice(this,initialAcceptanceRate,majorCutoff,diversityCutoff,EDAdmitCapacity, false);
            System.out.println(admissions.getAcceptanceRate());
        }
        else{
            admissions = new UserAdmissionsOffice((UserCollege) this,initialAcceptanceRate,majorCutoff,diversityCutoff,EDAdmitCapacity, true);
        }
    }

    public void enroll(Student student){
        attendingStudents.add(student);
    }

    //graduate or leave in general
    public void graduate(Student student){
        attendingStudents.remove(student);
        alumni.add(student);
    }

    public ArrayList<Student> getAttendingStudents(){
        return attendingStudents;
    }

    public Wealth getWealth(){
        return wealth;
    }

    public ArrayList<Student> getAlumni(){
        return alumni;
    }
}

