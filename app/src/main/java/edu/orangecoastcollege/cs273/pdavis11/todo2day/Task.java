package edu.orangecoastcollege.cs273.pdavis11.todo2day;

/**
 * Created by pdavis11 on 9/28/2017.
 */

public class Task {

    private String mDescription;
    private int mId;
    private boolean mIsDone;

    public String getDescription() {
        return mDescription;
    }

    public int getId() {
        return mId;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }

    public Task(int id, String description, boolean isDone) {
        mId = id;
        mDescription = description;
        mIsDone = isDone;
    }

    public Task(String description, boolean isDone) {
        this(-1, description, isDone);
    }

    public Task() {
        this(-1, "", false);
    }

    @Override
    public String toString() {
        return "Task{" +
                "mDescription='" + mDescription + '\'' +
                ", mId=" + mId +
                ", mIsDone=" + mIsDone +
                '}';
    }
}
