package edu.pitt.cs.cs1635.openclicker.teacher;

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

import edu.pitt.cs.cs1635.openclicker.Globals;
import edu.pitt.cs.cs1635.openclicker.R;

public class TeacherClassesAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public TeacherClassesAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
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
            view = inflater.inflate(R.layout.list_teacher_classes, null);
        }

        // Set class name
        TextView listItemText = (TextView) view.findViewById(R.id.list_class_name);
        listItemText.setText(list.get(position));

        // Clicking on row opens class activity
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.currentTeacherClass = list.get(position);
                Intent intent = new Intent(context, ClassInfoActivity.class);
                context.startActivity(intent);
            }
        });

        // Handle class deletion
        Button deleteBtn = (Button) view.findViewById(R.id.list_class_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Class")
                        .setMessage("Are you sure you want to remove this class? It will remove all of the students as well.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
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