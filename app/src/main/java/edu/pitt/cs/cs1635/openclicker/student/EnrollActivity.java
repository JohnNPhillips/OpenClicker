package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.pitt.cs.cs1635.openclicker.ClassObject;
import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.Student;

public class EnrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        Button enrollClass = (Button) findViewById(R.id.student_enrollClass);
        enrollClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enroll();
            }
        });

        EditText edit = (EditText) findViewById(R.id.student_classCode);
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    enroll();
                }
                return false;
            }
        });
    }

    private void enroll() {
        final EditText classCode = (EditText) findViewById(R.id.student_classCode);
        String code = classCode.getText().toString().trim();
        String className = Globals.getClassNameFromCode(code);
        if (className == null) {
            Toast.makeText(EnrollActivity.this, "Sorry, no class with that code could be found", Toast.LENGTH_LONG).show();
            return;
        }
        ClassObject enrolledClass = Globals.getTeacherFromClass(className).getClass(className);
        Student toEnroll = Globals.getStudent(Globals.getActiveStudent());

        toEnroll.addClass(enrolledClass);
        enrolledClass.addStudent(toEnroll.getId());

        Intent intent = new Intent(EnrollActivity.this, StudentClassListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
