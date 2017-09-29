package edu.orangecoastcollege.cs273.pdavis11.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> mAllTaskList = new ArrayList<>();

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Clear the existing database
        deleteDatabase(DBHelper.DATABASE_NAME);

        // Pre-populate the List with 4 tasks // For testing in Android Monitor
        mAllTaskList.add(new Task("Study for CS 273 Midterm", false));
        mAllTaskList.add(new Task("Eat cereal", false));
        mAllTaskList.add(new Task("Walk the dog", false));
        mAllTaskList.add(new Task("get some sleep", false));

        // Let's instantiate a new DBHelper
        DBHelper db = new DBHelper(this);

        // Let's loop through the list and each one to the database:
        for (Task t : mAllTaskList)
            db.addTask(t);

        // Let's clear out the list and rebuild from the databases this time:
        mAllTaskList.clear();

        // Retrieve the task from the database
        mAllTaskList = db.getAllTasks();

        // Loop through each of the tasks and print them to the Log.i
        Log.i(TAG, "Showing all task:");
        for (Task t: mAllTaskList)
            Log.i(TAG, t.toString());

        // Delete Task
        db.deleteTask(mAllTaskList.get(3));
        mAllTaskList.clear();
        mAllTaskList = db.getAllTasks();
        Log.i(TAG, "After deleting task 4:");
        for (Task t: mAllTaskList)
            Log.i(TAG, t.toString());
    }
}
