package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import edu.pitt.cs.cs1635.openclicker.R;

public class CreateQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        Button askQuestion = (Button) findViewById(R.id.teacher_start_question);
        askQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateQuestionActivity.this, AskQuestionActivity.class);
                startActivity(intent);
            }
        });

        final RadioButton[] radios = new RadioButton[5];
        radios[0] = (RadioButton)findViewById(R.id.question_a_correct);
        radios[1] = (RadioButton)findViewById(R.id.question_b_correct);
        radios[2] = (RadioButton)findViewById(R.id.question_c_correct);
        radios[3] = (RadioButton)findViewById(R.id.question_d_correct);
        radios[4] = (RadioButton)findViewById(R.id.question_e_correct);

        View.OnClickListener radioClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only select correct radio button
                for (RadioButton radio : radios) {
                    radio.setChecked(false);
                }
                ((RadioButton)v).setChecked(true);
            }
        };

        for (int i = 0; i < radios.length; i++) {
            radios[i].setOnClickListener(radioClick);
        }
    }
}
