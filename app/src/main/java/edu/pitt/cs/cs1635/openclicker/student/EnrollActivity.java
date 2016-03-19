package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.pitt.cs.cs1635.openclicker.R;

public class EnrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        Button enrollClass = (Button)findViewById(R.id.student_enrollClass);
        enrollClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollActivity.this, StudentClassListActivity.class);
                startActivity(intent);
            }
        });
    }
}
