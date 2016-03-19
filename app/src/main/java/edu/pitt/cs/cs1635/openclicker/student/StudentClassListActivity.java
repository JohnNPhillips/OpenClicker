package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.pitt.cs.cs1635.openclicker.R;

public class StudentClassListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_list);

        Button selectClass = (Button)findViewById(R.id.student_gotoClass);
        selectClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentClassListActivity.this, WaitForQuestionActivity.class);
                startActivity(intent);
            }
        });

        Button addClass = (Button)findViewById(R.id.student_addClass);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentClassListActivity.this, EnrollActivity.class);
                startActivity(intent);
            }
        });
    }
}
