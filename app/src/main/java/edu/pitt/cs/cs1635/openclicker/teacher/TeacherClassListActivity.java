package edu.pitt.cs.cs1635.openclicker.teacher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.Teacher;

public class TeacherClassListActivity extends AppCompatActivity {

    public static final String NEW_CLASS_CODE = "NEW_CLASS_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_list);

        // Pop up class code for newly created class
        if (getIntent().hasExtra(NEW_CLASS_CODE)) {
            String code = getIntent().getStringExtra(NEW_CLASS_CODE);
            new AlertDialog.Builder(this)
                    .setTitle("Class Code")
                    .setMessage("Class successfully created. The class code (" + code + ") should  be distributed to your students so they can enroll in the class.")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create().show();
        }

        Button createClass = (Button) findViewById(R.id.teacher_createClass);
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherClassListActivity.this, CreateClassActivity.class);
                startActivity(intent);
            }
        });

        Teacher teacher = Globals.getActiveTeacher();
        TeacherClassesAdapter adapter = new TeacherClassesAdapter(teacher.getClassList(), this);
        ListView class_list = (ListView) findViewById(R.id.teacher_class_list);
        class_list.setAdapter(adapter);

    }
}
