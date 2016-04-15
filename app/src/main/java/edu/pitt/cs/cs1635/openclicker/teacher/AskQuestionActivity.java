package edu.pitt.cs.cs1635.openclicker.teacher;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

public class AskQuestionActivity extends AppCompatActivity {

    Timer timer;
    ImageView image;
    TextView questionTextView;
    int currentSlide;
    int[] images;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        activity = this;
        image = (ImageView) findViewById(R.id.imageView1);
        questionTextView = (TextView) findViewById(R.id.questionTLabel);

        Question question = Globals.getActiveQuestion();
        questionTextView.setText(question.text);

        images = new int[8];
        images[0] = R.drawable.graph0;
        images[1] = R.drawable.graph1;
        images[2] = R.drawable.graph2;
        images[3] = R.drawable.graph3;
        images[4] = R.drawable.graph4;
        images[5] = R.drawable.graph5;
        images[6] = R.drawable.graph6;
        images[7] = R.drawable.graph7;

        currentSlide = 1;
        image.setImageResource(images[0]);

        final TextView timeRemainingTextView = (TextView)findViewById(R.id.time_remaining);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Question question = Globals.getActiveQuestion();
                        int timeRemaining = question.getTimeRemaining();
                        if (timeRemaining > 0) {
                            timeRemainingTextView.setText("Time Remaining: 00:" + String.format("%02d", timeRemaining));
                            currentSlide++;
                            image.setImageResource(images[currentSlide < 8 ? currentSlide : 7]);// respect array bounds
                        } else {
                            final TextView timeRemainingTextView = (TextView)findViewById(R.id.time_remaining);
                            timeRemainingTextView.setText("Final results");
                        }
                    }
                });
            }
        }
            , 0 // delay
            , 900 // milis per update
        );

        question.start();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        timer.cancel();
    }
}
