package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.Student;

/**
 * Created by lukekljucaric on 4/16/16.
 */
public class StudentAnswerAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Question question;
    private Context context;
    private Set<Map.Entry<String, Integer>> entries;

    public StudentAnswerAdapter(Question q, Context c)
    {
        question = q;
        context = c;

        if(question.getStudentsAnswers() != null) {
            entries = question.getStudentsAnswers();
            for (Map.Entry<String, Integer> entry : entries) {
                list.add(entry.getKey());
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

        //if (q.getStudentAnswer(Globals.getActiveStudent().getId()) != null) {
        TextView listItemText = (TextView) view.findViewById(R.id.list_question_student);
        listItemText.setText(list.get(position));
        TextView listItemCorrect = (TextView) view.findViewById(R.id.correctLabel);

        if (question.getStudentAnswer(list.get(position)) == question.correct) {
            listItemCorrect.setText("Correct");
        }
        else
        {
            listItemCorrect.setText("Incorrect");
        }
                //}

        // Clicking on row opens class activity

        return view;
    }
}


