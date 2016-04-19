package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import edu.pitt.cs.cs1635.openclicker.ClassObject;
import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

public class WaitForQuestionActivity extends AppCompatActivity {

    private Timer updateTimer = new Timer();
    private ClassObject currentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_question);

        currentClass = Globals.getActiveClass();

        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(Question q : currentClass.getQuestions())
                {
                    if(q.getTimeRemaining() > 0)
                    {
                        Intent intent = new Intent(WaitForQuestionActivity.this, AnswerQuestionActivity.class);
                        Globals.setActiveQuestion(q);
                        startActivity(intent);
                    }
                }
            }
        }, 0, 100);

        StudentQuestionsAdapter adapter = new StudentQuestionsAdapter(currentClass.getQuestions(), this);
        ListView questionList = (ListView)findViewById(R.id.listViewQuestions);
        questionList.setAdapter(adapter);
        setTitle(Globals.getActiveClass().getClassName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateTimer.cancel();
        updateTimer.purge();
    }
}
