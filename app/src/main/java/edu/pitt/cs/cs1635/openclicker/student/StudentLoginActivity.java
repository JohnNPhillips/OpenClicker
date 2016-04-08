package edu.pitt.cs.cs1635.openclicker.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;

public class StudentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        Button login = (Button)findViewById(R.id.student_login);
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

    private void login(){
        String id = ((EditText) findViewById(R.id.idInput)).getText().toString();
        if(Globals.getStudent(id) == null) {
            new AlertDialog.Builder(this)
                    .setTitle("New Student")
                    .setMessage("Is the ID: " + id + " correct? This will create a new student.")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String student = ((EditText) findViewById(R.id.idInput)).getText().toString();
                            Globals.addStudent(student);
                            Globals.setActiveStudent(student);
                            Intent intent = new Intent(StudentLoginActivity.this, StudentClassListActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    })
                    .create().show();
        }
        else
        {
            Globals.setActiveStudent(id);
            Intent intent = new Intent(StudentLoginActivity.this, StudentClassListActivity.class);
            startActivity(intent);
        }
    }
}
