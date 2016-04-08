package edu.pitt.cs.cs1635.openclicker;

import java.util.ArrayList;

/**
 * Created by lukekljucaric on 3/30/16.
 */
public class Teacher {
    private String id;
    private ArrayList<ClassObject> classes;

    public Teacher(String n)
    {
        id = n;
        classes = new ArrayList<ClassObject>();
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

    public String getId()
    {
        return id;
    }

    public void addClass(ClassObject c)
    {
        classes.add(c);
    }

    public boolean removeClass(ClassObject c)
    {
        return classes.remove(c);
    }

    public ArrayList<ClassObject> getClassList()
    {
        return classes;
    }
}
