package org.example;
import java.util.ArrayList;
import java.util.Map;

public class College {
    public String name;
    public int capacity;
    private ArrayList<Student> attendingStudents;
    private ArrayList<Student> alumni;
    public AdmissionsOffice admissions;

    public College(String name,int capacity){
        this.capacity = capacity;
        this.name = name;
        this.admissions = admissions;
        attendingStudents = new ArrayList<Student>();
        alumni = new ArrayList<Student>();
        admissions = new AdmissionsOffice(this);
        //potential spot to put the admin constuctor
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

}

