package edu.pitt.cs.cs1635.openclicker.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.Student;

public class StudentClassListActivity extends AppCompatActivity {

    private String student = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_list);

        student = Globals.getActiveStudent();

        Button addClass = (Button)findViewById(R.id.student_addClass);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentClassListActivity.this, EnrollActivity.class);
                startActivity(intent);
            }
        });

        StudentClassesAdapter adapter = new StudentClassesAdapter(Globals.getStudent(student).getClassList(), this);
        ListView class_list = (ListView)findViewById(R.id.student_class_list);
        class_list.setAdapter(adapter);
    }
}
