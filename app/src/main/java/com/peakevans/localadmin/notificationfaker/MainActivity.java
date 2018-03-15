package com.peakevans.localadmin.notificationfaker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Notification notification;

    public String snapper;
    public int waitTime;


    public void onClick_new(View view){

        EditText snapperInput = (EditText) findViewById(R.id.snapperInput);
        snapper = snapperInput.getText().toString();

        EditText timeInput = (EditText)findViewById(R.id.timeInput);
        waitTime = Integer.parseInt(timeInput.getText().toString());

        String message = "Snap created- name: "+snapper+", time: "+waitTime+" seconds";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onClick_notify(View view) {

        Intent notificationIntent = new Intent(this, MainActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.snapicon2)
                .setAutoCancel(true)
                .setTicker("tickerText")
                .setContentText("from "+snapper)
                .setContentIntent(PendingIntent.getActivity(this, 0, notificationIntent, 0))
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Snapchat")
                .setColor(Color.RED)
                .setDefaults(Notification.DEFAULT_ALL);

        notification = builder.build();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run(){

                pushNotification();

            }

        }, waitTime*1000);

    }


    public void pushNotification(){

        int randomInt = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        ((NotificationManager)this.

                getSystemService(NOTIFICATION_SERVICE)).

                notify(randomInt, notification);

                PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Tag");
                wakeLock.acquire(5000);
                wakeLock.release();

    }

}



