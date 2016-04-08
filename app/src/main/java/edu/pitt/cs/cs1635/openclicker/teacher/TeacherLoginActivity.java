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
                    .setTitle("Teacher ID")
                    .setMessage("There is currently no teacher account associated with this id. Would you like to create one?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String id = ((EditText) findViewById(R.id.idInput)).getText().toString();
                            Globals.addTeacher(id);
                            Globals.setActiveTeacher(id);
                            Intent intent = new Intent(TeacherLoginActivity.this, TeacherClassListActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
            return;
        } else {
            Globals.setActiveTeacher(id);
            Intent intent = new Intent(TeacherLoginActivity.this, TeacherClassListActivity.class);
            startActivity(intent);
        }
    }
}
