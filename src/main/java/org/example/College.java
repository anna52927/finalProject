package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class College {
    public String name;
    public int capacity;
    private ArrayList<Student> attendingStudents;
    private ArrayList<Student> alumni;
    public AdmissionsOffice admissions;
    private Wealth wealth;

    public College(String name, int capacity, int tuition, int pubIm, double initialAcceptanceRate, int majorCutoff, int diversityCutoff, int EDAdmitCapacity, HashMap<String,Integer> majorDistributions,HashMap<Integer,Integer> diversityDistributions){
        this.capacity = capacity;
        this.name = name;
        attendingStudents = new ArrayList<Student>();
        alumni = new ArrayList<Student>();
        admissions = new AdmissionsOffice(this,initialAcceptanceRate,majorCutoff,diversityCutoff,EDAdmitCapacity,majorDistributions,diversityDistributions);
        wealth = new Wealth(0,tuition,pubIm);
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

