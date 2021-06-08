package com.arhiser.todolist.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arhiser.todolist.App;
import com.arhiser.todolist.model.Note;
import com.arhiser.todolist.model.Room;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();
    private LiveData<List<Room>> roomLiveData = App.getInstance().getRoomDao().getAllLiveData();

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }

    public LiveData<List<Room>> getRoomLiveData() {
        return roomLiveData;
    }
}
