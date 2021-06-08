package com.arhiser.todolist.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.arhiser.todolist.model.Note;
import com.arhiser.todolist.model.Room;

@Database(entities = {Note.class, Room.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    public abstract RoomDao roomDao();
}
