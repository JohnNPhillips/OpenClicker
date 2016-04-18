package edu.pitt.cs.cs1635.openclicker.teacher;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.student.AnswerQuestionActivity;

public class QuestionsAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Question> list = new ArrayList<Question>();
    private Context context;

    public QuestionsAdapter(ArrayList<Question> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_teacher_questions, null);
        }

        // Set question title
        TextView listItemText = (TextView) view.findViewById(R.id.list_question_title);
        listItemText.setText(list.get(position).text);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.setActiveQuestion(list.get(position));

                Intent intent = new Intent(context, CreateQuestionActivity.class);
                context.startActivity(intent);
            }
        });

        Button askQ = (Button) view.findViewById(R.id.teacher_ask_question);
        askQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = list.get(position);
                Globals.setActiveQuestion(question);

                // Send a notification to the "student" (DEMO ONLY)
                Globals.setActiveStudent(Globals.getStudent("100"));
                question.notifyCurrentStudent(context);

                // Teacher views results
                Intent intent = new Intent(context, AskQuestionActivity.class);
                context.startActivity(intent);
            }
        });

        return view;
    }
}