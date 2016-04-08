package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;

/**
 * Created by lukekljucaric on 3/30/16.
 */
public class ClassObject {
    private String name;
    private ArrayList<String> students;
    private ArrayList<Question> questions;

    public ClassObject(String n)
    {
        name = n;
        questions = new ArrayList<Question>();
        students = new ArrayList<String>();
    }

    public ClassObject(String n, ArrayList<Question> q)
    {
        name = n;
        questions = q;
        students = new ArrayList<String>();
    }

    public void addStudent(String s)
    {
        students.add(s);
    }

    public void addQuestion(Question q)
    {
        questions.add(q);
    }

    public boolean removeQuestion(Question q)
    {
        if(questions.contains(q))
        {
            questions.remove(q);
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getClassName()
    {
        return name;
    }

    public ArrayList<Question> getQuestions()
    {
        return questions;
    }
}
