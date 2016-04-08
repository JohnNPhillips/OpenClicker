package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.pitt.cs.cs1635.openclicker.ClassObject;
import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;

public class CreateClassActivity extends AppCompatActivity {

    private String teacher = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        if(getIntent().hasExtra("Id")) {
            teacher = getIntent().getStringExtra("Id");
        }
        else
        {
            teacher = Globals.getActiveTeacher();
        }

        Button createClass = (Button) findViewById(R.id.teacher_createClass);
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClass();
            }
        });

        EditText edit = (EditText) findViewById(R.id.teacher_className);
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    createClass();
                }
                return false;
            }
        });
    }

    private void createClass() {
        final EditText className = (EditText) findViewById(R.id.teacher_className);
        String name = className.getText().toString().trim();
        if (!name.isEmpty()) {
            ClassObject c = new ClassObject(name);
            Globals.getTeacher(teacher).addClass(c);
            Globals.addRLookup(c.getClassName(), Globals.getTeacher(teacher));
        }

        Intent intent = new Intent(CreateClassActivity.this, TeacherClassListActivity.class);
        intent.putExtra(TeacherClassListActivity.NEW_CLASS_CODE, Globals.getClassCode(name));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
