package com.arhiser.todolist;

import android.app.Application;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.arhiser.todolist.data.AppDatabase;
import com.arhiser.todolist.data.NoteDao;
import com.arhiser.todolist.data.RoomDao;

public class App extends Application {

    private AppDatabase database;
    private NoteDao noteDao;
    private RoomDao roomDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Room` (`uid` INTEGER, "
                    + "`roomend` BIGINT, PRIMARY KEY(`uid`))");
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-name")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();

        noteDao = database.noteDao();
        roomDao = database.roomDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }
}
