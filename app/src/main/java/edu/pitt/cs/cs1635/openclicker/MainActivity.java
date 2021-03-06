package edu.pitt.cs.cs1635.openclicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

import edu.pitt.cs.cs1635.openclicker.student.StudentLoginActivity;
import edu.pitt.cs.cs1635.openclicker.teacher.TeacherLoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Globals.init(); //initializes Globals -- Don't touch ask Luke Kljucaric

        Button student = (Button)findViewById(R.id.main_student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.onTeacherSide = true;
                Intent intent = new Intent(MainActivity.this, StudentLoginActivity.class);
                startActivity(intent);
            }
        });

        Button teacher = (Button)findViewById(R.id.main_teacher);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.onTeacherSide = false;
                Intent intent = new Intent(MainActivity.this, TeacherLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
