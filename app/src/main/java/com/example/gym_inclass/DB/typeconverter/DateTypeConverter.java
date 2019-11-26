package com.example.gym_inclass.DB.typeconverter;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverter {
    @TypeConverter
    public long converDateToLong(Date date){
        return date.getTime();
    }
    @TypeConverter
    public Date convertLongToDate(long time){
        return new Date(time);
    }
}
