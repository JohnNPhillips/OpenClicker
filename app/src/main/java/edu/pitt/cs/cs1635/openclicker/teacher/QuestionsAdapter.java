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

                // Set activeStudent to the demo student
                Globals.setActiveStudent(Globals.getStudent("100"));

                // Build notification
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setSmallIcon(R.drawable.button);
                mBuilder.setContentTitle("OpenClicker");
                mBuilder.setContentText(question.text);
                mBuilder.setAutoCancel(true);

                // Add action to be performed when notification is clicked
                Intent resultIntent = new Intent(context, AnswerQuestionActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(AnswerQuestionActivity.class);

                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);

                // Issue notification (this only sends a notification to the demo student;
                //   in reality we would send it to all the students in the class)
                int notificationId = 100; // this could be the same as the student id
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(notificationId, mBuilder.build());

                // Teacher views results
                Intent intent = new Intent(context, AskQuestionActivity.class);
                context.startActivity(intent);
            }
        });

        return view;
    }
}