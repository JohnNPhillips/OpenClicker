package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;

/**
 * Created by lukekljucaric on 3/30/16.
 */
public class ClassObject {
    private String name;
    private Teacher teacher;
    private ArrayList<Student> students;
    private ArrayList<Question> questions;
    private String code;

    public ClassObject(String n, Teacher t)
    {
        this(n, t, null);
    }

    public ClassObject(String n, Teacher t, ArrayList<Question> q)
    {
        name = n;
        teacher = t;
        code = (Math.abs(name.hashCode() % 900000) + 100000) + "";
        students = new ArrayList<>();
        if(q == null) questions = new ArrayList<>();
        else questions = q;
    }

    public void addStudent(Student s)
    {
        students.add(s);
    }

    public Question getQuestion(String text) {
        for (Question q: questions) {
            if (q.text.equals(text)) {
                return q;
            }
        }
        return null;
    }

    public void addQuestion(Question q)
    {
        questions.add(q);
    }

    public boolean removeQuestion(Question q)
    {
        return questions.remove(q);
    }

    public String getClassName()
    {
        return name;
    }

    public String getClassCode()
    {
        return code;
    }

    public ArrayList<Question> getQuestions()
    {
        return questions;
    }

    public void delete() {
        for (Student s: students) {
            s.removeClass(this);
        }
        teacher.removeClass(this);
    }
}
