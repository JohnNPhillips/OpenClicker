package edu.pitt.cs.cs1635.openclicker.teacher;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

public class AskQuestionActivity extends AppCompatActivity {

    private Timer timer;
    private TextView questionTextView;
    private Activity activity;
    private FakeData fakeData;

    private static int students_per_class = 48; // for faking results

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        activity = this;
        questionTextView = (TextView) findViewById(R.id.questionTLabel);

        Question question = Globals.getActiveQuestion();
        questionTextView.setText(question.text);

        fakeData = new FakeData();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(students_per_class * 2 / 3);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-1);
        graph.getViewport().setMaxX(5);
        graph.getGridLabelRenderer().setHighlightZeroLines(false);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"", "A", "B", "C", "D", "E", ""});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        updateGraph();

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
                            fakeData.update();
                            updateGraph();
                            timeRemainingTextView.setText("Time Remaining: 00:" + String.format("%02d", timeRemaining));
                        } else {
                            timeRemainingTextView.setText("Final results");
                        }
                    }
                });
            }
        }
            , 0 // delay
            , 990 // milis per update
        );

        question.start();
        setTitle("Question Results");
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
        timer.purge();
    }

    private void updateGraph() {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, fakeData.a),
                new DataPoint(1, fakeData.b),
                new DataPoint(2, fakeData.c),
                new DataPoint(3, fakeData.d),
                new DataPoint(4, fakeData.e)
        });
        series.setColor(0xFFD97925);
        series.setSpacing(50);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(0xFF000000);
        graph.removeAllSeries();
        graph.addSeries(series);
    }

    private class FakeData {
        int a,b,c,d,e;
        Random r;
        int students_left;

        FakeData() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            r = new Random();
            students_left = students_per_class;
        }

        void update() {
            Question question = Globals.getActiveQuestion();
            int time_remaining = question.getTimeRemaining();
            int students_to_answer = students_left / time_remaining;
            for(int i=0; i<students_to_answer; i++) {
                int correct = r.nextInt(2); // fifty percent chance of answering correctly

                int ans;
                if (correct > 0)  ans = question.correct; // answer correctly
                else ans = r.nextInt(5); // answer randomly

                if(ans == 0) a++;
                else if(ans == 1) b++;
                else if(ans == 2) c++;
                else if(ans == 3) d++;
                else e++;
            }
            students_left -= students_to_answer;
        }
    }
}
