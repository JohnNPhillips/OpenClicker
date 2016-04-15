package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.Teacher;

public class CreateQuestionActivity extends AppCompatActivity {

    private RadioButton[] radios = new RadioButton[5];
    private SeekBar timeBar;
    private TextView timeLabel;
    private int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        Button askQuestion = (Button) findViewById(R.id.teacher_start_question);
        askQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = saveQuestion();
                Globals.setActiveQuestion(question);

                Intent intent = new Intent(CreateQuestionActivity.this, AskQuestionActivity.class);
                startActivity(intent);
            }
        });

        timeBar = (SeekBar) findViewById(R.id.timeSelection);
        timeLabel = (TextView) findViewById(R.id.secondsLabel);

        timeLabel.setText("20 seconds");
        seconds = 20;

        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 1)
                {
                    timeLabel.setText("1 second");
                    seconds = 1;
                }
                else
                {
                    timeLabel.setText(Integer.toString(progress) + " seconds");
                    seconds = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radios[0] = (RadioButton) findViewById(R.id.question_a_correct);
        radios[1] = (RadioButton) findViewById(R.id.question_b_correct);
        radios[2] = (RadioButton) findViewById(R.id.question_c_correct);
        radios[3] = (RadioButton) findViewById(R.id.question_d_correct);
        radios[4] = (RadioButton) findViewById(R.id.question_e_correct);

        View.OnClickListener radioClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only select correct radio button
                for (RadioButton radio : radios) {
                    radio.setChecked(false);
                }
                ((RadioButton) v).setChecked(true);
            }
        };

        for (int i = 0; i < radios.length; i++) {
            radios[i].setOnClickListener(radioClick);
        }

        // Load question information if we are editing one
        if (Globals.getActiveQuestion() != null) {
            Question editQ = Globals.getActiveQuestion();

            EditText questionTitle = (EditText) findViewById(R.id.teacher_create_question_title);
            questionTitle.setText(editQ.text);

            ((EditText) findViewById(R.id.question_a_text)).setText(editQ.answers[0]);
            ((EditText) findViewById(R.id.question_b_text)).setText(editQ.answers[1]);
            ((EditText) findViewById(R.id.question_c_text)).setText(editQ.answers[2]);
            ((EditText) findViewById(R.id.question_d_text)).setText(editQ.answers[3]);
            ((EditText) findViewById(R.id.question_e_text)).setText(editQ.answers[4]);

            radios[editQ.correct].callOnClick();

            timeBar.setProgress(editQ.seconds);
        }

        // Save question functionality
        final Button saveQuestion = (Button) findViewById(R.id.teacher_save_question);
        saveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestion();

                Intent intent = new Intent(CreateQuestionActivity.this, ClassInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private Question saveQuestion() {
        String questionTitle = ((EditText) findViewById(R.id.teacher_create_question_title)).getText().toString();
        String ansA = ((EditText) findViewById(R.id.question_a_text)).getText().toString();
        String ansB = ((EditText) findViewById(R.id.question_b_text)).getText().toString();
        String ansC = ((EditText) findViewById(R.id.question_c_text)).getText().toString();
        String ansD = ((EditText) findViewById(R.id.question_d_text)).getText().toString();
        String ansE = ((EditText) findViewById(R.id.question_e_text)).getText().toString();

        int correct_ans = 0;
        for (int i = 0; i < radios.length; i++) {
            if (radios[i].isChecked()) {
                correct_ans = i;
            }
        }

        if (Globals.getActiveQuestion() != null) {
            // Edit existing question
            Question editQ = Globals.getActiveQuestion();
            editQ.text = questionTitle;
            editQ.answers = new String[] { ansA, ansB, ansC, ansD, ansE };
            editQ.correct = correct_ans;
            editQ.seconds = seconds;

            return editQ;
        }
        else {
            // Create new question
            Question newQ = new Question(questionTitle, ansA, ansB, ansC, ansD, ansE, correct_ans, seconds);
            Globals.getActiveClass().addQuestion(newQ);

            return newQ;
        }
    }
}
