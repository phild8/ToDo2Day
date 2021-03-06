package edu.orangecoastcollege.cs273.pdavis11.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Lets the user handle database operations while interacting with the App. The user can create
 * different task to complete and to check off.
 *
 * @author Phillip Davis
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    // For back end debug
    //public static final String TAG = MainActivity.class.getSimpleName();

    // Reference to the list of all tasks.
    private List<Task> mAllTaskList = new ArrayList<>();
    // Reference to the database
    private DBHelper mDB;
    // References to the widgets needed
    private EditText mDescriptionEditText;
    private ListView mTaskListView;

    // Reference to the custom list adapter.
    private TaskListAdapter mTaskListAdapter;

    /**
     * Loads the user interface. Loads list filled with task objects
     * @param savedInstanceState loads saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new DBHelper(this);
        mDescriptionEditText = (EditText) findViewById(R.id.taskEditText);
        mTaskListView = (ListView) findViewById(R.id.taskListView);
    }

    /**
     * loads the previous list with the last database
     */
    protected void onResume() {
        super.onResume();
        // Database related "stuff"
        // 1) Populate the list from the database (using DBHelper)
        mAllTaskList = mDB.getAllTasks();
        // 2) Connect the ListView with custom list adapter.
        mTaskListAdapter = new TaskListAdapter(this, R.layout.task_item, mAllTaskList);
        mTaskListView.setAdapter(mTaskListAdapter);
    }

    /**
     * Adds a new Task object to the list
     * @param v the add task list click.
     */
    public void addTask(View v)
    {
        // TODO: Fix the addTask so it does not return -1 when check once created.
        // TODO: Check Java2 folder for IC14 Video games on how to fix.

        // Check to see if the description is empty or null
        String description = mDescriptionEditText.getText().toString(); // to string because getText() charSequence
        if (TextUtils.isEmpty(description))
            Toast.makeText(this, "Please enter a description.", Toast.LENGTH_LONG).show();
        else
        {
            // Create the Task
            Task newTask = new Task(description, false); // one of the 3 constructors
            // Add it to the database
            mDB.addTask(newTask);
            // Add it to the list
            mAllTaskList.add(newTask);
            // Notify the list adapter that it's been changed.
            mTaskListAdapter.notifyDataSetChanged();
            // Clear out the EditText
            mDescriptionEditText.setText("");
        }

    }

    /**
     * Deletes all Task objects from the list
     * @param v the button click
     */
    public void clearAllTasks(View v)
    {
        mDB.deleteAllTask();

        mAllTaskList.clear();
        mTaskListAdapter.notifyDataSetChanged();
    }

    /**
     * Changes the status if the Task is done or not
     * @param v the button click
     */
    public void changeTaskStatus(View v)
    {
        CheckBox selectedCheckBox = (CheckBox) v;
        Task selectedTask = (Task) selectedCheckBox.getTag();
        // Update the task
        selectedTask.setDone(selectedCheckBox.isChecked());
        // Update the database
        mDB.updateTask((selectedTask));
    }

}



















