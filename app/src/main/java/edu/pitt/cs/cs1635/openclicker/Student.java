package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;

/**
 * Created by lukekljucaric on 3/30/16.
 */
public class Student {
    private String Id;
    private ArrayList<ClassObject> classes;

    public Student(String n)
    {
        Id = n;
        classes = new ArrayList<ClassObject>();
    }

    public void addClass(ClassObject c)
    {
        classes.add(c);
    }

    public boolean removeClass(ClassObject c)
    {
        return classes.remove(c);
    }

    public ClassObject getClass(String cName)
    {
        for (ClassObject c: classes)
        {
            if(c.getClassName().equals(cName))
            {
                return c;
            }
        }
        return null;
    }

    public ArrayList<ClassObject> getClassList()
    {
        return classes;
    }

    public String getId()
    {
        return Id;
    }

}

