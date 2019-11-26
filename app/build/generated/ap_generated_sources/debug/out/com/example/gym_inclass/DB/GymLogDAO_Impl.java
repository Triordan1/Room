package com.example.gym_inclass.DB;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.gym_inclass.DB.typeconverter.DateTypeConverter;
import com.example.gym_inclass.GymLog;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public final class GymLogDAO_Impl implements GymLogDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfGymLog;

  private final DateTypeConverter __dateTypeConverter = new DateTypeConverter();

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfGymLog;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfGymLog;

  public GymLogDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGymLog = new EntityInsertionAdapter<GymLog>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `gymlog`(`mLogId`,`mExercise`,`mWeight`,`mReps`,`date`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GymLog value) {
        stmt.bindLong(1, value.mLogId);
        if (value.getExercise() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getExercise());
        }
        stmt.bindLong(3, value.getWeight());
        stmt.bindLong(4, value.getReps());
        final long _tmp;
        _tmp = __dateTypeConverter.converDateToLong(value.getDate());
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfGymLog = new EntityDeletionOrUpdateAdapter<GymLog>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `gymlog` WHERE `mLogId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GymLog value) {
        stmt.bindLong(1, value.mLogId);
      }
    };
    this.__updateAdapterOfGymLog = new EntityDeletionOrUpdateAdapter<GymLog>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `gymlog` SET `mLogId` = ?,`mExercise` = ?,`mWeight` = ?,`mReps` = ?,`date` = ? WHERE `mLogId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GymLog value) {
        stmt.bindLong(1, value.mLogId);
        if (value.getExercise() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getExercise());
        }
        stmt.bindLong(3, value.getWeight());
        stmt.bindLong(4, value.getReps());
        final long _tmp;
        _tmp = __dateTypeConverter.converDateToLong(value.getDate());
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.mLogId);
      }
    };
  }

  @Override
  public void insert(GymLog... gynLogs) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfGymLog.insert(gynLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(GymLog gymLog) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfGymLog.handle(gymLog);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(GymLog... gymLogs) {
    __db.beginTransaction();
    try {
      __updateAdapterOfGymLog.handleMultiple(gymLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<GymLog> getGymLogs() {
    final String _sql = " SELECT * FROM gymlog";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMLogId = _cursor.getColumnIndexOrThrow("mLogId");
      final int _cursorIndexOfMExercise = _cursor.getColumnIndexOrThrow("mExercise");
      final int _cursorIndexOfMWeight = _cursor.getColumnIndexOrThrow("mWeight");
      final int _cursorIndexOfMReps = _cursor.getColumnIndexOrThrow("mReps");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final List<GymLog> _result = new ArrayList<GymLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GymLog _item;
        final String _tmpMExercise;
        _tmpMExercise = _cursor.getString(_cursorIndexOfMExercise);
        final int _tmpMWeight;
        _tmpMWeight = _cursor.getInt(_cursorIndexOfMWeight);
        final int _tmpMReps;
        _tmpMReps = _cursor.getInt(_cursorIndexOfMReps);
        _item = new GymLog(_tmpMExercise,_tmpMWeight,_tmpMReps);
        _item.mLogId = _cursor.getInt(_cursorIndexOfMLogId);
        final Date _tmpDate;
        final long _tmp;
        _tmp = _cursor.getLong(_cursorIndexOfDate);
        _tmpDate = __dateTypeConverter.convertLongToDate(_tmp);
        _item.setDate(_tmpDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public GymLog getQuestionWithId(int logId) {
    final String _sql = "SELECT * FROM gymlog Where mLogId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, logId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMLogId = _cursor.getColumnIndexOrThrow("mLogId");
      final int _cursorIndexOfMExercise = _cursor.getColumnIndexOrThrow("mExercise");
      final int _cursorIndexOfMWeight = _cursor.getColumnIndexOrThrow("mWeight");
      final int _cursorIndexOfMReps = _cursor.getColumnIndexOrThrow("mReps");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final GymLog _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMExercise;
        _tmpMExercise = _cursor.getString(_cursorIndexOfMExercise);
        final int _tmpMWeight;
        _tmpMWeight = _cursor.getInt(_cursorIndexOfMWeight);
        final int _tmpMReps;
        _tmpMReps = _cursor.getInt(_cursorIndexOfMReps);
        _result = new GymLog(_tmpMExercise,_tmpMWeight,_tmpMReps);
        _result.mLogId = _cursor.getInt(_cursorIndexOfMLogId);
        final Date _tmpDate;
        final long _tmp;
        _tmp = _cursor.getLong(_cursorIndexOfDate);
        _tmpDate = __dateTypeConverter.convertLongToDate(_tmp);
        _result.setDate(_tmpDate);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
