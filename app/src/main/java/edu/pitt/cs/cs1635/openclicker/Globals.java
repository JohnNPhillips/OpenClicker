package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Globals {
    public static ArrayList<String> studentClassList = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private static Hashtable<String, Teacher> rLookUpTeacher = new Hashtable<String, Teacher>(); //use this for looking up who the teacher is when student adds class
    //public static ArrayList<String> teacherClassList = new ArrayList<>();

    private static HashMap<String, List<Question>> classQuestions = new HashMap<>();

    private static String currentTeacherClass = null, activeTeacher = null, tempClass = null;

    private static Teacher teacherTest = new Teacher("100");
    private static ClassObject classExample = new ClassObject("HIST 1234");
    private static ClassObject classExample2 = new ClassObject("HIST 2300");

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
        teacherTest.addClass(classExample2);
        rLookUpTeacher.put(classExample.getClassName(), teacherTest);
        rLookUpTeacher.put(classExample2.getClassName(), teacherTest);
        teachers.add(teacherTest);
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

    public static ArrayList<Question> getQuestionList(Teacher t, String c)
    {
        return t.getClass(c).getQuestions();
    }

    public static Teacher getTeacher(String id)
    {
        for (Teacher t: teachers)
        {
            if(t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public static void addTeacher(String id)
    {
        Teacher t = new Teacher(id);
        teachers.add(t);
    }

    public static String getActiveTeacher()
    {
        return activeTeacher;
    }

    public static void setActiveTeacher(String id)
    {
        activeTeacher = id;
    }

    public static String getTempClass()
    {
        return tempClass;
    }

    public static void setTempClass(String c)
    {
        tempClass = c;
    }

    public static Teacher getTeacherFromClass(String name)
    {
        if(rLookUpTeacher.containsKey(name))
        {
            return rLookUpTeacher.get(name);
        }

        return null;
    }

    public static void addRLookup(String c, Teacher t)
    {
        rLookUpTeacher.put(c, t);
    }

    public static void init() {
        //created so that Globals code can run -- Don't touch ask Luke Kljucaric
    }
}
