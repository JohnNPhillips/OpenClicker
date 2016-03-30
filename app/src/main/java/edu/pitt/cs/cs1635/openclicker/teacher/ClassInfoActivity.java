package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;

public class ClassInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        Button createQuestion = (Button) findViewById(R.id.teacher_create_question);
        createQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassInfoActivity.this, CreateQuestionActivity.class);
                startActivity(intent);
            }
        });

        String className = getIntent().getStringExtra("Class");

        QuestionsAdapter adapter = new QuestionsAdapter(Globals.getQuestionList(className), this);
        ListView question_list = (ListView) findViewById(R.id.teacher_questions_list);
        question_list.setAdapter(adapter);

        TextView classCode = (TextView) findViewById(R.id.teacher_class_code);
        classCode.setText("Class Code: " + Globals.getClassCode(Globals.currentTeacherClass));
    }
}
