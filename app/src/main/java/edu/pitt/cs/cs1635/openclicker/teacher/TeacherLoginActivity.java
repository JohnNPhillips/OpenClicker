package edu.pitt.cs.cs1635.openclicker.teacher;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.Teacher;

public class TeacherLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        Button login = (Button) findViewById(R.id.teacher_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        EditText edit = (EditText) findViewById(R.id.idInput);
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    login();
                }
                return false;
            }
        });
    }

    private void login() {
        String id = ((EditText) findViewById(R.id.idInput)).getText().toString();
        if (Globals.getTeacher(id) == null) {
            new AlertDialog.Builder(this)
                    .setTitle("New Teacher")
                    .setMessage("Is the ID: " + id + " correct? This will create a new teacher.")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String id = ((EditText) findViewById(R.id.idInput)).getText().toString();
                            Teacher teacher = new Teacher(id);
                            Globals.addTeacher(teacher);
                            Globals.setActiveTeacher(teacher);
                            Intent intent = new Intent(TeacherLoginActivity.this, TeacherClassListActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .create().show();
            return;
        } else {
            Teacher t = Globals.getTeacher(id);
            Globals.setActiveTeacher(t);
            Intent intent = new Intent(TeacherLoginActivity.this, TeacherClassListActivity.class);
            startActivity(intent);
        }
    }
}
