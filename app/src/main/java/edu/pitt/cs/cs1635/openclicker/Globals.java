package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;
import java.util.Hashtable;

public class Globals {

    /**
     * IMPORTANT USAGE NOTES
     *
     *      The variables activeStudent, activeTeacher, activeClass, and activeQuestion
     *      must be maintained accurately. These globals prevent us from having to pass
     *      lots of strings whenever we switch intents (and then look-up the objects based
     *      on the strings).
     *
     *      Student and teacher objects can be accessed by id through this Globals class,
     *      but if you want the currently active student or teacher, use the variables mentioned
     *      in the previous note.
     *
     *      All ClassObject objects can be accessed through the teacher or student objects.
     *
     *      All Question objects can be accessed through the appropriate ClassObject
     */

    /**
     * Miscellaneous Methods
     *   getClassFromCode(code)
     *   init()
     */

    /**
     * Maps from ids to student/teacher objects
     *   This way we don't have to loop through every teacher/student when retrieving
     *
     *   teachers
     *      addTeacher(Teacher)
     *      getTeacher(id)
     *   students
     *      addStudent(Student)
     *      getStudent(id)
     */
    private static Hashtable<String,Teacher> teachers = new Hashtable<>();
    private static Hashtable<String,Student> students = new Hashtable<>();

    /**
     * State that keeps track of what is currently going on in the app
     *      activeTeacher
     *          setActiveTeacher(Teacher)
     *          getActiveTeacher()
     *      activeStudent
     *          setActiveStudent(Student)
     *          getActiveStudent()
     *      activeClass
     *          setActiveClass(ClassObj)
     *          getActiveClass()
     *      activeQuestion
     *          setActiveQuestion(Question)
     *          getActiveQuestion()
     */
    private static Teacher activeTeacher = null;
    private static Student activeStudent = null;
    private static ClassObject activeClass = null;
    private static Question activeQuestion = null;
    protected static boolean onTeacherSide = false; // set from start-up screen, altered by "question asked" notification

    /**
     * Hard-coded teacher and student with example classes and questions (DEMO ONLY)
     */
    static {
        Teacher teacherTest = new Teacher("100");
        ClassObject classExample = new ClassObject("HIST 1234", teacherTest);
        ClassObject classExample2 = new ClassObject("HIST 2300", teacherTest);

        ArrayList<Question> hist1234_questions = new ArrayList<>();
        Question q1 = new Question("What year did the civil war start?", "A", "B", "C", "D", "E", 1, 8);
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
        teachers.put(teacherTest.getId(), teacherTest);

        Student studentTest = new Student("100");
        studentTest.addClass(classExample);
        students.put(studentTest.getId(), studentTest);
    }

    /******************************
     * Method Implementations
     ******************************/

    /******************************
     * Miscellaneous
     ******************************/

    public static ClassObject getClassFromCode(String classCode) {
        // class does not exist unless a teacher has it
        //  this is our most expensive operation
        for (Teacher t: teachers.values()) {
            for (ClassObject classObj : t.getClassList()) {
                if (classObj.getClassCode().equals(classCode)) {
                    return classObj;
                }
            }
        }
        return null;
    }

    public static void init() {
        //created so that Globals code can run -- Don't touch ask Luke Kljucaric
    }

    /******************************
     * Teacher and Student Map Manipulators
     ******************************/

    public static Student getStudent(String id)
    {
        if(students.containsKey(id)) return students.get(id);
        else return null;
    }

    public static void addStudent(Student s)
    {
        students.put(s.getId(), s);
    }

    public static Teacher getTeacher(String id)
    {
        if(teachers.containsKey(id)) return teachers.get(id);
        else return null;
    }

    public static void addTeacher(Teacher t)
    {
        teachers.put(t.getId(), t);
    }

    /******************************
     * State Manipulators
     ******************************/

    public static Student getActiveStudent()
    {
        return activeStudent;
    }

    public static void setActiveStudent(Student s)
    {
        activeStudent = s;
    }

    public static Teacher getActiveTeacher()
    {
        return activeTeacher;
    }

    public static void setActiveTeacher(Teacher t)
    {
        activeTeacher = t;
    }

    public static ClassObject getActiveClass()
    {
        return activeClass;
    }

    public static void setActiveClass(ClassObject c)
    {
        activeClass = c;
    }

    public static Question getActiveQuestion()
    {
        return activeQuestion;
    }

    public static void setActiveQuestion(Question q)
    {
        activeQuestion = q;
    }
}
