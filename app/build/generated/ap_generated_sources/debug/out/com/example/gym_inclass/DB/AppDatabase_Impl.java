package com.example.gym_inclass.DB;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AppDatabase_Impl extends AppDatabase {
  private volatile GymLogDAO _gymLogDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `gymlog` (`mLogId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `mExercise` TEXT, `mWeight` INTEGER NOT NULL, `mReps` INTEGER NOT NULL, `date` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"d7f2e59df3120732a123af49720f1378\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `gymlog`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsGymlog = new HashMap<String, TableInfo.Column>(5);
        _columnsGymlog.put("mLogId", new TableInfo.Column("mLogId", "INTEGER", true, 1));
        _columnsGymlog.put("mExercise", new TableInfo.Column("mExercise", "TEXT", false, 0));
        _columnsGymlog.put("mWeight", new TableInfo.Column("mWeight", "INTEGER", true, 0));
        _columnsGymlog.put("mReps", new TableInfo.Column("mReps", "INTEGER", true, 0));
        _columnsGymlog.put("date", new TableInfo.Column("date", "INTEGER", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGymlog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGymlog = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGymlog = new TableInfo("gymlog", _columnsGymlog, _foreignKeysGymlog, _indicesGymlog);
        final TableInfo _existingGymlog = TableInfo.read(_db, "gymlog");
        if (! _infoGymlog.equals(_existingGymlog)) {
          throw new IllegalStateException("Migration didn't properly handle gymlog(com.example.gym_inclass.GymLog).\n"
                  + " Expected:\n" + _infoGymlog + "\n"
                  + " Found:\n" + _existingGymlog);
        }
      }
    }, "d7f2e59df3120732a123af49720f1378", "2bc7e274a02f8ab67e76feb00b686c3f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "gymlog");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `gymlog`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public GymLogDAO getGymLogDAO() {
    if (_gymLogDAO != null) {
      return _gymLogDAO;
    } else {
      synchronized(this) {
        if(_gymLogDAO == null) {
          _gymLogDAO = new GymLogDAO_Impl(this);
        }
        return _gymLogDAO;
      }
    }
  }
}
