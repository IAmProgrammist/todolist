package com.arhiser.todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Room implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "roomend")
    public Long roomend;

    public Room(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return uid == room.uid && roomend == room.roomend;
    }

    @Override
    public int hashCode() {
        return (int) ((int) 31 * (uid + roomend));
    }

    protected Room(Parcel in) {
        uid = in.readInt();
        roomend = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeLong(roomend);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Room> CREATOR = new Parcelable.Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };
}
