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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

public class AnswerQuestionActivity extends AppCompatActivity {

    private Timer timer;
    private Activity activity;
    private Question question;

    private Set<Question> viewedQuestions = new HashSet<>();

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
            //ans_button.setFocusableInTouchMode(true);
            ans_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Button b : answers) {
                        b.setBackgroundResource(R.drawable.button_enabled);
                    }
                    //ans_button.requestFocus();
                    for (int j = 0; j < 5; j++) {
                        if (ans_button.getText().equals(answers[j].getText())) {
                            question.setStudentAnswer(Globals.getActiveStudent().getId(), j);
                        }
                    }
                    ans_button.setBackgroundResource(R.drawable.button_pressed);
                }
            });
        }

        setTitle(Globals.getActiveClass().getClassName());
        createTimer();
    }

    private void createTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
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
                            Integer answer = question.getStudentAnswer(Globals.getActiveStudent().getId());
                            if (answer == -1)
                            {
                                Toast.makeText(activity, "You did not select an answer",
                                        Toast.LENGTH_LONG).show();
                            }
                            else if (question.correct == answer)
                            {
                                Toast.makeText(activity, "Your answer was correct",
                                        Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(activity, "Your answer was incorrect",
                                        Toast.LENGTH_LONG).show();
                            }
                            disableButtons();
                            timeRemainingTextView.setText("Question is over");
                            timer.cancel();
                            timer.purge();
                        }
                    }
                });
            }
        }
                , 0 // delay
                , 100 // milis per update
        );
    }
    private void disableButtons() {
        findViewById(R.id.answer_a).setEnabled(false);
        findViewById(R.id.answer_b).setEnabled(false);
        findViewById(R.id.answer_c).setEnabled(false);
        findViewById(R.id.answer_d).setEnabled(false);
        findViewById(R.id.answer_e).setEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (viewedQuestions.contains(question) && question.getTimeRemaining() > 0) {
                disableButtons();
                Toast.makeText(this, "You exited the application. You are not permitted to change your answer.",
                        Toast.LENGTH_LONG).show();

                createTimer();
        }

        viewedQuestions.add(question);
    }

    @Override
    public void onStop() {
        super.onStop();

        timer.cancel();
        timer.purge();
    }
}
