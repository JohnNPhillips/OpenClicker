package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.pitt.cs.cs1635.openclicker.ClassObject;
import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

/**
 * Created by lukekljucaric on 4/16/16.
 */
public class StudentQuestionsAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<Question> questionList;
    private Context context;

    public StudentQuestionsAdapter(ArrayList<Question> qList, Context c)
    {
        questionList = qList;
        context = c;
        for (Question q : questionList)
        {
            if (q.hasBeenAsked())
            {
                list.add(q.text);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_student_answers, null);
        }

        for(Question q : questionList)
        {
            if(list.get(position).equals(q.text)) {
                //if (q.getStudentAnswer(Globals.getActiveStudent().getId()) != null) {
                TextView listItemText = (TextView) view.findViewById(R.id.list_question_student);
                listItemText.setText(list.get(position));
                TextView listItemCorrect = (TextView) view.findViewById(R.id.correctLabel);

                if (q.getStudentAnswer(Globals.getActiveStudent().getId()) == q.correct) {
                    listItemCorrect.setText("Correct");
                }
                else
                {
                    listItemCorrect.setText("Incorrect");
                }
                //}
            }
        }

        // Clicking on row opens class activity

        return view;
    }
}
