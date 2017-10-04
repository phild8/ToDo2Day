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

    /**
     * A new adapter to link the list and the database.
     * @param context The layout to connect with.
     * @param resource The Task object item.
     * @param objects A lis of Task object items.
     */
    public TaskListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        mContext = context;
        mResourceId = resource;
        mTaskList = objects;
    }

    /**
     * Embeds the Task object so the MainActivity can access it and see if it has been checked or not
     * @param position The position of the Task object insie of the list.
     * @param convertView Null.
     * @param parent The ViewGroup class.
     * @return the Task Object if it has been checked or not.
     */
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

        // Tag = invisible locker behind each view (store anything there)
        selectedCheckBox.setTag(selectedTask);

        return view;
    }
}




















