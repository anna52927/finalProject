package org.example;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> testList = new ArrayList<>();
        testList.add("a");
        testList.add("b");
        testList.add("c");
        testList.add("d");
        testList.add("e");
        testList.add("f");
        testList.add("g");
        testList.add("h");

        Student testStudent = new Student(2,3,4,1,2,3,testList);

    }
}