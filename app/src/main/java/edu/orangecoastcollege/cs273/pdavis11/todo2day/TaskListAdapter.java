package edu.orangecoastcollege.cs273.pdavis11.todo2day;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

/**
 * Interface of the TaskItem. Populates the list with new TaskItems and connects it to the
 * the main_activity.
 *
 * @author Phillip Davis
 * @version 1.0
 *
 * Created by pdavis11 on 10/3/2017.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResourceId;
    private List<Task> mTaskList;

    public TaskListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        mContext = context;
        mResourceId = resource;
        mTaskList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Let's retrieve the selected task
        Task selectedTask = mTaskList.get(position);

        // Used LayoutInflator to inflate the view for this specific task:
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        // Get a reference to the checkbox
        CheckBox selectedCheckBox =  view.findViewById(R.id.isDoneCheckBox);
        selectedCheckBox.setChecked(selectedTask.isDone());
        selectedCheckBox.setText(selectedTask.getDescription());
        return view;
    }
}




















