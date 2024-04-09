package org.example;
/*4/8 notes:
* - need to talk through with felix about how to store students as
* parameters in the college class
*  - need to verify with everyone college / wealth class interactions*/

import java.util.ArrayList;

public class Wealth {
    private int money;
    private final int TUITION;
    private int PubIm; //public image, on a scale (1-10?, 1-100?)

    public Wealth(int money, int tuition, int PubIm){
        this.money = money;
        TUITION = tuition;
        this.PubIm = PubIm;
    }

    public void payTuition(int numStu){
        money = money + (numStu * TUITION);
    }
    public void receiveDonations(ArrayList<Student> alumni){
        //go through new students and calculate pubim
        //go through alumni donations by major expected salary (ability to give)
        //go through almni donations by pubim (willingness to give)
    }


}
