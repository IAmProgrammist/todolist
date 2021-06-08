package com.arhiser.todolist.screens.details;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.arhiser.todolist.App;
import com.arhiser.todolist.R;
import com.arhiser.todolist.model.Note;
import com.arhiser.todolist.model.Room;

import java.util.List;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    private static final int NOTIFY_ID = 43;

    private static final String CHANNEL_ID = "ToDo";

    @Override
    public void onReceive(Context context, Intent intent) {
        List<Room> rooms = App.getInstance().getRoomDao().getAll();
        if(rooms.size() != 0){
            Room room = rooms.get(0);
            int done = 0;
            int undone = 0;
            for(Note a : App.getInstance().getNoteDao().getAll()){
                if(a.done){
                    done++;
                }else{
                    undone++;
                }
            }
            createNotificationChannel(context);
            notificate(context, done, undone);
            if(System.currentTimeMillis() >= room.roomend){
                App.getInstance().getRoomDao().clear();
                App.getInstance().getNoteDao().clear();
            }
        }
    }

    public void notificate(Context ctx, int done, int undone){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(ctx, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Напоминание")
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(
                                String.format("Время истекло! Список ToDo очищен! Выполнено: %d, не выполнено: %d. Так держать!", done, undone)))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(ctx);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    private void createNotificationChannel(Context ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = "Освобождает от ToDo";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = ctx.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
