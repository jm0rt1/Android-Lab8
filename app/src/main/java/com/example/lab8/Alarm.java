package com.example.lab8;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {



    public void setQuickAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i,PendingIntent.FLAG_MUTABLE);
        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, pi);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm done", Toast.LENGTH_SHORT).show();
    }
}
