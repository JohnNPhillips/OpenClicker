package edu.pitt.cs.cs1635.openclicker.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;

public class EnrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        final EditText classCode = (EditText) findViewById(R.id.student_classCode);

        Button enrollClass = (Button) findViewById(R.id.student_enrollClass);
        enrollClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = classCode.getText().toString().trim();
                String className = Globals.getClassNameFromCode(code);
                if (className == null) {
                    Toast.makeText(EnrollActivity.this, "Sorry, no class with that code could be found", Toast.LENGTH_LONG).show();
                    return;
                }

                Globals.studentClassList.add(className);

                Intent intent = new Intent(EnrollActivity.this, StudentClassListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
