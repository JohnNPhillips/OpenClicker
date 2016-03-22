package edu.pitt.cs.cs1635.openclicker.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;

public class TeacherClassListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_list);

        Button createClass = (Button) findViewById(R.id.teacher_createClass);
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(TeacherClassListActivity.this, CreateClassActivity.class);
                //startActivity(intent);
            }
        });

        TeacherClassesAdapter adapter = new TeacherClassesAdapter(Globals.teacherClassList, this);
        ListView class_list = (ListView) findViewById(R.id.teacher_class_list);
        class_list.setAdapter(adapter);
    }
}
