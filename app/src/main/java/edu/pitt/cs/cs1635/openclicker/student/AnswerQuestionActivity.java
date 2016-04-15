package edu.pitt.cs.cs1635.openclicker.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

public class AnswerQuestionActivity extends AppCompatActivity {

    private boolean first_run = true;
    private Timer timer;
    private Activity activity;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_answer_question);

        question = Globals.getActiveQuestion();

        final Button[] answers = new Button[5];
        answers[0] = (Button) findViewById(R.id.answer_a);
        answers[1] = (Button) findViewById(R.id.answer_b);
        answers[2] = (Button) findViewById(R.id.answer_c);
        answers[3] = (Button) findViewById(R.id.answer_d);
        answers[4] = (Button) findViewById(R.id.answer_e);

        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(question.text);

        String[] questionAnswers = question.getAnswers();

        for (int i = 0; i < answers.length; i++) {
            final Button ans_button = answers[i];
            ans_button.setText(questionAnswers[i]);
            ans_button.setFocusableInTouchMode(true);
            ans_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Button b : answers) {
                        b.setBackgroundResource(R.drawable.button);
                    }
                    ans_button.requestFocus();
                    
                }
            });
        }

        final TextView timeRemainingTextView = (TextView)findViewById(R.id.answer_time_remaining);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Question question = Globals.getActiveQuestion();
                        int timeRemaining = question.getTimeRemaining();
                        if (timeRemaining >= 0) {
                            timeRemainingTextView.setText("Time Remaining: 00:" + String.format("%02d", timeRemaining));
                        } else {
                            Toast.makeText(activity, "You exited the application. You are not permitted to change your answer.",
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AnswerQuestionActivity.this, WaitForQuestionActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
                , 0 // delay
                , 100 // milis per update
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        if(first_run) {
            first_run = false;
        } else {
            findViewById(R.id.answer_a).setEnabled(false);
            findViewById(R.id.answer_b).setEnabled(false);
            findViewById(R.id.answer_c).setEnabled(false);
            findViewById(R.id.answer_d).setEnabled(false);
            findViewById(R.id.answer_e).setEnabled(false);

            Toast.makeText(this,"You exited the application. You are not permitted to change your answer.",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }

    // disable back button
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Please wait until the question is finished.",
                Toast.LENGTH_LONG).show();
    }
}
