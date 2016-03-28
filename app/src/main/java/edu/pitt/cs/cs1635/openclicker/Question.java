package edu.pitt.cs.cs1635.openclicker;

public class Question {
    public String text;
    public String[] answers;
    public int correct;

    public Question(String text, String[] answers, int correct) {
        this.text = text;
        this.answers = answers;
        this.correct = correct;
    }

    public Question(String text, String ans1, String ans2, String ans3, String ans4, String ans5, int correct) {
        this(text, new String[]{ans1, ans2, ans3, ans4, ans5}, correct);
    }
}
