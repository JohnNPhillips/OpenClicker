package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.pitt.cs.cs1635.openclicker.R;

public class AnswerQuestionActivity extends AppCompatActivity {

    private boolean first_run = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        final Button[] answers = new Button[5];
        answers[0] = (Button) findViewById(R.id.answer_a);
        answers[1] = (Button) findViewById(R.id.answer_b);
        answers[2] = (Button) findViewById(R.id.answer_c);
        answers[3] = (Button) findViewById(R.id.answer_d);
        answers[4] = (Button) findViewById(R.id.answer_e);

        for (int i = 0; i < answers.length; i++) {
            final Button ans_button = answers[i];
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

        // Countdown  timer
        final TextView timeRemaining = (TextView)findViewById(R.id.answer_time_remaining);
        new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeRemaining.setText("Time Remaining: 00:" + String.format("%02d", millisUntilFinished / 1000));
            }

            public void onFinish() {
                Intent intent = new Intent(AnswerQuestionActivity.this, WaitForQuestionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }.start();
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

    // disable back button
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Please wait until the question is finished.",
                Toast.LENGTH_LONG).show();
    }
}
