package com.example.gym_inclass;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.gym_inclass.DB.AppDatabase;

import java.util.Date;
@Entity(tableName = AppDatabase.GYMLOG_TABLE)
public class GymLog {
    @PrimaryKey(autoGenerate = true)
    public int mLogId;

    private String mExercise;
    private int mWeight;
    private int mReps;
    private Date date;

    public GymLog(String mExercise, int mWeight, int mReps) {
        this.mExercise = mExercise;
        this.mWeight = mWeight;
        this.mReps = mReps;
        date = new Date();
    }

    @Override
    public String toString() {
        return "GymLog{" +
                date + "\n" +
                mExercise + "\n" +
                mWeight + "\n" +
                mReps + "\n" +
                "=-=-=-=-=-=-\n";
    }

    public String getExercise() {
        return mExercise;
    }

    public void setExercise(String mExercise) {
        this.mExercise = mExercise;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int mWeight) {
        this.mWeight = mWeight;
    }

    public int getReps() {
        return mReps;
    }

    public void setReps(int mReps) {
        this.mReps = mReps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getmLogId() {
        return mLogId;
    }

    public void setmLogId(int mLogId) {
        this.mLogId = mLogId;
    }
}
