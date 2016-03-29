package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;

public class CreateClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        final EditText className = (EditText) findViewById(R.id.teacher_className);

        Button createClass = (Button) findViewById(R.id.teacher_createClass);
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = className.getText().toString().trim();
                if (!name.isEmpty()) {
                    Globals.teacherClassList.add(name);
                }

                Intent intent = new Intent(CreateClassActivity.this, TeacherClassListActivity.class);
                intent.putExtra(TeacherClassListActivity.NEW_CLASS_CODE, Globals.getClassCode(name));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
