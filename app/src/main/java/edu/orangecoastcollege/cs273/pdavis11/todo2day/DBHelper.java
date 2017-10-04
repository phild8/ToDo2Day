package edu.orangecoastcollege.cs273.pdavis11.todo2day;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * creates database tables and updates database tables
 * the fields this class uses is (_id, description, done)
 *
 * @author Phillip Davis
 * @version 1.00
 *
 * Created by pdavis11 on 9/28/2017.
 */
class DBHelper extends SQLiteOpenHelper {

    // Create some useful database constants
    public static final String DATABASE_NAME = " ToDo2Today";
    public static final String DATABASE_TABLE = "Tasks";
   // Update version if adding new fields
    public static final int DATABASE_VERSION = 1;

    // Create some useful table constants
    public static final String KEY_FIELD_ID = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_DONE = "done";

    // All we need is context, where we are calling it from, MainActivity
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates a new database table
     * @param db the database table to create
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Generate an SQL statement for creating a new table
        // CREATE TABLE Tasks (_id, INTEGER PRIMARY KEY, description TEXT, done INTEGER)
        String createTable = "CREATE TABLE " + DATABASE_TABLE
                + " (" + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_DONE + " INTEGER " + ")";
        db.execSQL(createTable);

    }

    /**
     * Upgrades table by dropping old version then creates a new table by calling onCreate
     * creates a new table if table does not exist
     * @param db the database to upgrade
     * @param i old version of db
     * @param i1 new version of db
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // 1) Drop the existing table
        db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);

        // 2) Build (create) the new one
        onCreate(db);
    }

    /**
     * adds a new task object ands the object to the the database
     * @param newTask the new task object
     */
    public void addTask(Task newTask)
    {
        // Too many spaces or wrong characters in string can break the DB!!
        SQLiteDatabase db = getWritableDatabase(); // extended SQLiteHelper, already db
        // Specify the values (fields) to insert into the databases
        // Everything, *except* the primary key _id (auto assigned) // _ cursor, what sql looks for
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, newTask.getDescription());
        values.put(FIELD_DONE, newTask.isDone() ? 1 : 0);
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    /**
     * Deletes one task from the database
     * @param taskToDelete the task to delete
     */
    public void deleteTask(Task taskToDelete)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_FIELD_ID +  " = " + taskToDelete.getId(), null);
        db.close();
    }

    /**
     * Deletes the who database table and clears and closes the file.
     */
    public void deleteAllTask()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.close();
    }

    /**
     *  Updates some or all information about at task in the DB
     *
     * @param taskToEdit the task to update
     */
    public void updateTask(Task taskToEdit)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, taskToEdit.getDescription());
        values.put(FIELD_DONE, taskToEdit.isDone() ? 1 : 0);

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + "=" + taskToEdit.getId(), null);
    }

    /**
     * Returns one task with the ID specified.
     * @param d the tasks ID
     */
    public Task getSingleTask(int d)
    {
        Task singleTask = null;

        SQLiteDatabase db = getReadableDatabase(); // extended SQLiteHelper, already db
        // To retrieve data from a database table, we use a cursor
        // Cursor stores the results of the a query
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                KEY_FIELD_ID + "=" + id, null, null, null, null);

        if (cursor.moveToFirst())
        {
            singleTask = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
        }
        cursor.close();
        db.close();

        return singleTask;
    }

    /**
     * Methods that gets all tasks and prints them
     * @return list of tasks
     */
    public List<Task> getAllTasks()
    {
        List<Task> allTasksList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase(); // extended SQLiteHelper, already db
        // To retrieve data from a database table, we use a cursor
        // Cursor stores the results of the a query
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            // Guaranteed at least one result from the query
            do {
                // Cursor idx starts at 0, because android object. SQL starts at 1
                // 3rd param is int, must convert to a bool, use comparison
                Task task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
                allTasksList.add(task);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return allTasksList;
    }
}