package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.student.WaitForQuestionActivity;

public class AskQuestionActivity extends AppCompatActivity {

    ImageView image;
    int currentSlide;
    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        image = (ImageView) findViewById(R.id.imageView1);

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
        new CountDownTimer(8000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished < 1000f * (8 - currentSlide)) image.setImageResource(images[currentSlide++]);
                timeRemaining.setText("Time Remaining: 00:" + String.format("%02d", millisUntilFinished / 1000));
            }

            public void onFinish() {
                timeRemaining.setText("Final results");
            }
        }.start();
    }
}
