package com.arhiser.todolist.screens.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.arhiser.todolist.App;
import com.arhiser.todolist.R;
import com.arhiser.todolist.model.Room;
import com.arhiser.todolist.screens.details.AlarmBroadcastReceiver;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePicking extends AppCompatActivity {
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_room);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        final DatePicker picker = findViewById(R.id.date_picker);

        picker.setMinDate(System.currentTimeMillis());

        findViewById(R.id.pick_until).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = new GregorianCalendar();
                cal.set(picker.getYear(), picker.getMonth(), picker.getDayOfMonth(), 0, 0, 0);
                cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
                Room room = new Room();
                room.roomend = cal.getTimeInMillis();
                App.getInstance().getRoomDao().insert(room);
                Intent broadcastAlarm = new Intent(DatePicking.this, AlarmBroadcastReceiver.class);
                broadcastAlarm.putExtra("endTime", cal.getTimeInMillis());
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(DatePicking.this, 0,  broadcastAlarm, 0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (App.getInstance().getRoomDao().getAny().size() != 0){
            super.onBackPressed();
        }
    }
}
