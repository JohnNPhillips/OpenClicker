package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Globals {
    public static ArrayList<String> studentClassList = new ArrayList<>();
    public static ArrayList<String> teacherClassList = new ArrayList<>();

    public static HashMap<String, List<Question>> classQuestions = new HashMap<>();

    static
    {
        studentClassList.add("CS 1234");
        studentClassList.add("CS 4567");

        teacherClassList.add("CS 4321");
        teacherClassList.add("CS 8765");

        Question q = new Question("How many states are there in the US?", "40", "45", "48", "50", "52", 3);
        List<Question> cs4321_questions = new ArrayList<>();
        cs4321_questions.add(q);
        classQuestions.put("CS 4321", cs4321_questions);
    }
}
