package com.arhiser.todolist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.arhiser.todolist.model.Room;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM Room")
    List<Room> getAll();

    @Query("SELECT * FROM Room")
    LiveData<List<Room>> getAllLiveData();

    @Query("SELECT * FROM Room WHERE uid IN (:roomIds)")
    List<Room> loadAllByIds(int[] roomIds);

    @Query("SELECT * FROM Room WHERE uid = :uid LIMIT 1")
    Room findById(int uid);

    @Query("SELECT * FROM Room")
    List<Room> getAny();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Room room);

    @Update
    void update(Room room);

    @Delete
    void delete(Room room);

    @Query("DELETE FROM Room")
    void clear();
}
