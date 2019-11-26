package com.example.gym_inclass.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gym_inclass.GymLog;

import java.util.List;

@Dao
public interface GymLogDAO {
    @Insert
    void insert(GymLog... gynLogs);
    @Update
    void update(GymLog... gymLogs);
    @Delete
    void delete(GymLog gymLog);
    @Query(" SELECT * FROM " + AppDatabase.GYMLOG_TABLE)
    List<GymLog> getGymLogs();
    @Query("SELECT * FROM " + AppDatabase.GYMLOG_TABLE + " Where mLogId = :logId")
    GymLog getQuestionWithId(int logId);

}
