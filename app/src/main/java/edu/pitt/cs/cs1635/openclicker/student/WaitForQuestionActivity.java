package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import edu.pitt.cs.cs1635.openclicker.R;

public class WaitForQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_question);

        ProgressBar waitingCircle = (ProgressBar)findViewById(R.id.progressBar);
        waitingCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitForQuestionActivity.this, AnswerQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}
