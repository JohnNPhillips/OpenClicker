package edu.pitt.cs.cs1635.openclicker;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map;

import edu.pitt.cs.cs1635.openclicker.student.AnswerQuestionActivity;

public class Question {
    public String text;
    public String[] answers;
    public int correct;
    public int seconds;
    private long startTime;
    private Hashtable<String, Integer> studentAnswers = new Hashtable<String, Integer>();

    public Question(String text, String[] answers, int correct, int seconds) {
        this.text = text;
        this.answers = answers;
        this.correct = correct;
        this.seconds = seconds;
    }

    public Question(String text, String ans1, String ans2, String ans3, String ans4, String ans5, int correct, int seconds) {
        this(text, new String[]{ans1, ans2, ans3, ans4, ans5}, correct, seconds);
    }

    public void start() {
        startTime = new Date().getTime();
    }

    public String[] getAnswers()
    {
        return answers;
    }

    public boolean hasBeenAsked() {
        return startTime != 0;
    }

    public int getTimeRemaining() {
        long elapsed = new Date().getTime() - startTime;
        return (int) (seconds - elapsed / 1000);
    }

    public void setStudentAnswer(String s, Integer answer)
    {
        studentAnswers.put(s, answer);
    }

    public int getStudentAnswer(String s)
    {
        if(studentAnswers.containsKey(s))
        {
            return studentAnswers.get(s);
        }

        return -1;
    }

    public Set<Map.Entry<String, Integer>> getStudentsAnswers()
    {
        return studentAnswers.entrySet();
    }

    public void notifyCurrentStudent(Context context) {
        // Build notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.button);
        mBuilder.setContentTitle("OpenClicker");
        mBuilder.setContentText(text);
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
    }
}
