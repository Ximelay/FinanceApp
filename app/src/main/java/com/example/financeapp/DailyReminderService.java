package com.example.financeapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DailyReminderService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ReminderService", "Напоминание: не забудьте записать траты!");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
