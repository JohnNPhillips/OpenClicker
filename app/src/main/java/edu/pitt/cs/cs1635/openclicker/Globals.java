package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Globals {
    public static ArrayList<String> studentClassList = new ArrayList<>();
    //public static ArrayList<String> teacherClassList = new ArrayList<>();

    public static HashMap<String, List<Question>> classQuestions = new HashMap<>();

    public static String currentTeacherClass = null;

    public static Teacher teacherTest = new Teacher(100);
    public static ClassObject classExample = new ClassObject("HIST 1234");
    public static ClassObject classExample2 = new ClassObject("HIST 2300");

    static {
        studentClassList.add("CS 1632");
        studentClassList.add("CS 1550");

        //teacherClassList.add("HIST 1234");
        //teacherClassList.add("HIST 2300");

        ArrayList<Question> hist1234_questions = new ArrayList<>();
        Question q1 = new Question("What year did the civil war start?", "", "", "", "", "", 0, 8);
        Question q2 = new Question("Who was president in 1860?", "", "", "", "", "", 0, 8);
        Question q3 = new Question("Where was the last battle in the civil war?", "", "", "", "", "", 0, 8);
        hist1234_questions.add(q1);
        hist1234_questions.add(q2);
        hist1234_questions.add(q3);
        classExample.addQuestion(q1);
        classExample.addQuestion(q2);
        classExample.addQuestion(q3);
        teacherTest.addClass(classExample);
        //classQuestions.put("HIST 1234", hist1234_questions);

        Question statesQ = new Question("How many states are there in the US?", "40", "45", "48", "50", "52", 3, 8);
        List<Question> hist101qs = new ArrayList<>();
        hist101qs.add(statesQ);
        classQuestions.put("CS 4321", hist101qs);
    }

    public static String getClassCode(String className) {
        return ((className.hashCode() % 900000) + 100000) + "";
    }

    public static String getClassNameFromCode(String classCode) {
        for (String className : teacherTest.getClassList()) {
            if (getClassCode(className).equals(classCode)) {
                return className;
            }
        }

        return null;
    }

    public static void addQuestionToCurrentClass(Question q) {
        List<Question> classQs = classQuestions.get(currentTeacherClass);
        if (classQs == null) {
            classQs = new ArrayList<>();
        }

        classQs.add(q);
    }

    public static List<Question> getQuestionListForTeacher() {
        List<Question> classQs = classQuestions.get(currentTeacherClass);
        if (classQs == null) {
            classQs = new ArrayList<>();
            classQuestions.put(currentTeacherClass, classQs);
        }

        return classQs;
    }

    public static ArrayList<Question> getQuestionList(String c)
    {
        return teacherTest.getClass(c).getQuestions();
    }

    public static void init() {
        //created so that Globals code can run -- Don't touch ask Luke Kljucaric
    }
}
