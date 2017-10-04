package edu.orangecoastcollege.cs273.pdavis11.todo2day;

/**
 * The Model. Creates a new Task object that allows the user to set a description for. The task
 * can be checked done or not done. The Task Object is stored in a SQLite database
 *
 * @author Phillip Davis
 * @version 1.0
 * Created by pdavis11 on 9/28/2017.
 */
public class Task {

    private String mDescription;
    private int mId;
    private boolean mIsDone;

    /**
     * Gets the String of mDescription.
     * @return mDescription value.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Gets the int of mId
     * @return mId
     */
    public int getId() {
        return mId;
    }

    /**
     * Gets the boolean of isDone
     * @return True or False
     */
    public boolean isDone() {
        return mIsDone;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * Sets the mIsDone to the new boolean value
     * @param done the new boolean value
     */
    public void setDone(boolean done) {
        mIsDone = done;
    }

    /**
     * Overloaded Constructor, sets new values of id, description, isDone.
     * @param id the new value of mId.
     * @param description the new value of mDescription.
     * @param isDone the new value of mIsDone
     */
    public Task(int id, String description, boolean isDone) {
        mId = id;
        mDescription = description;
        mIsDone = isDone;
    }

    /**
     * Overloaded Constructor, calls super on mId, sets a new value on mDescription is mIsDone.
     * @param description the new value of mDescription.
     * @param isDone the new value of mIsDone.
     */
    public Task(String description, boolean isDone) {
        this(-1, description, isDone);
    }

    /**
     * Default constructor, mId is set -1, mDescription is set to "", mIsDone is set to false.
     */
    public Task() {
        this(-1, "", false);
    }

    /**
     * The Task object is converted into a string.
     * @return the String value of the Task object.
     */
    @Override
    public String toString() {
        return "Task{" +
                "mDescription='" + mDescription + '\'' +
                ", mId=" + mId +
                ", mIsDone=" + mIsDone +
                '}';
    }
}
