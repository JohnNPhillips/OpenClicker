package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;

public class Globals {
    public static ArrayList<String> studentClassList = new ArrayList<>();
    public static ArrayList<String> teacherClassList = new ArrayList<>();

    static
    {
        studentClassList.add("CS 1234");
        studentClassList.add("CS 4567");

        teacherClassList.add("CS 4321");
        teacherClassList.add("CS 8765");
    }
}
