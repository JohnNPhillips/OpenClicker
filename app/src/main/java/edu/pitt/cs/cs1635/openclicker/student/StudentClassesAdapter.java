package edu.pitt.cs.cs1635.openclicker.student;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.pitt.cs.cs1635.openclicker.ClassObject;
import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;
import edu.pitt.cs.cs1635.openclicker.Student;

public class StudentClassesAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public StudentClassesAdapter(ArrayList<ClassObject> classList, Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        for (ClassObject c: classList) {
            this.list.add(c.getClassName());
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_student_classes, null);
        }

        // Set class name
        TextView listItemText = (TextView) view.findViewById(R.id.list_class_name);
        listItemText.setText(list.get(position));

        // Clicking on row opens class activity
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WaitForQuestionActivity.class);
                context.startActivity(intent);
            }
        });

        // Handle class deletion
        Button deleteBtn = (Button) view.findViewById(R.id.list_class_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Drop Class")
                        .setMessage("Are you sure you want to drop the class?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Student student = Globals.getActiveStudent();
                                ClassObject c = student.getClass(list.get(position));
                                student.removeClass(c);
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        return view;
    }
}
