package edu.pitt.cs.cs1635.openclicker;

import java.util.Date;

public class Question {
    public String text;
    public String[] answers;
    public int correct;
    public int seconds;
    private long startTime;

    public Question(String text, String[] answers, int correct, int seconds) {
        this.text = text;
        this.answers = answers;
        this.correct = correct;
        this.seconds = seconds;
    }

    public Question(String text, String ans1, String ans2, String ans3, String ans4, String ans5, int correct, int seconds) {
        this(text, new String[]{ans1, ans2, ans3, ans4, ans5}, correct, seconds);
    }

    public void start() {
        startTime = new Date().getTime();
    }

    public int getTimeRemaining() {
        long elapsed = new Date().getTime() - startTime;
        return (int)(seconds - elapsed / 1000);
    }
}
