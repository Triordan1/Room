package com.example.gym_inclass.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.gym_inclass.DB.typeconverter.DateTypeConverter;
import com.example.gym_inclass.GymLog;

@Database(entities = {GymLog.class},version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DBNAME = "db-gymlog";
    public static final String GYMLOG_TABLE = "gymlog";
    public abstract GymLogDAO getGymLogDAO();
}
