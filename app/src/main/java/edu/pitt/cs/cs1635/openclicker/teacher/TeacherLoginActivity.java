package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent intent = new Intent(TeacherLoginActivity.this, TeacherClassListActivity.class);
                intent.putExtra("Id", ((EditText) findViewById(R.id.idInput)).getText().toString());
                startActivity(intent);
            }
        });
    }
}
