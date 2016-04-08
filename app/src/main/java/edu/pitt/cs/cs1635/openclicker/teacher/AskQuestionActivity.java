package edu.pitt.cs.cs1635.openclicker.teacher;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

public class AskQuestionActivity extends AppCompatActivity {

    CountDownTimer timer;
    ImageView image;
    TextView questionTextView;
    int currentSlide, seconds;
    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        image = (ImageView) findViewById(R.id.imageView1);
        questionTextView = (TextView) findViewById(R.id.questionTLabel);

        Question question = Globals.getActiveQuestion();
        questionTextView.setText(question.text);
        seconds = question.seconds;

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

        final TextView timeRemaining = (TextView)findViewById(R.id.time_remaining);
        timer = new CountDownTimer(seconds * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished < 1000f * (seconds - currentSlide)) image.setImageResource(images[currentSlide++]);
                timeRemaining.setText("Time Remaining: 00:" + String.format("%02d", millisUntilFinished / 1000));
            }

            public void onFinish() {
                timeRemaining.setText("Final results");
            }
        }.start();
    }

    @Override
    public void onStop()
    {
        super.onStop();

        timer.cancel();
    }
}
