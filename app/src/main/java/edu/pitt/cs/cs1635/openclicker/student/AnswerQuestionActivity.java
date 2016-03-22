package edu.pitt.cs.cs1635.openclicker.student;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.pitt.cs.cs1635.openclicker.R;

public class AnswerQuestionActivity extends AppCompatActivity {

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
            ans_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Button b : answers) {
                        //Zb.setEnabled(true);
                        b.setBackgroundResource(android.R.drawable.btn_default);
                    }

                    //ans_button.setEnabled(false);
                    ans_button.setBackgroundColor(Color.argb(0xFF, 0x92,0xd2, 0x95)); // pale greenish
                }
            });
        }
    }
}
