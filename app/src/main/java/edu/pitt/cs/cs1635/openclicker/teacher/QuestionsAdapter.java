package edu.pitt.cs.cs1635.openclicker.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.pitt.cs.cs1635.openclicker.Question;
import edu.pitt.cs.cs1635.openclicker.R;

public class QuestionsAdapter extends BaseAdapter implements ListAdapter {
    private List<Question> list = new ArrayList<Question>();
    private Context context;

    public QuestionsAdapter(List<Question> list, Context context) {
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
            view = inflater.inflate(R.layout.list_teacher_questions, null);
        }

        // Set question title
        TextView listItemText = (TextView) view.findViewById(R.id.list_question_title);
        listItemText.setText(list.get(position).text);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Handle question deletion
        Button deleteBtn = (Button) view.findViewById(R.id.list_question_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        return view;
    }
}