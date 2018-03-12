package com.peakevans.localadmin.notificationfaker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //private Button button1;
    //button1 = (Button) findViewById(R.id.button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Notification notification;

    public void onClick_new(View view){

        Intent intent = new Intent(this, NewNotification.class);
        startActivity(intent);

    }

    public void onClick_notify(View view) {

        Intent notificationIntent = new Intent(this, MainActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.snapicon)
                .setAutoCancel(true)
                .setTicker("tickerText")
                .setContentText("from Louis E")
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

        }, 2000);
    }

    public void pushNotification(){

        int randomInt = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        ((NotificationManager)this.

                getSystemService(NOTIFICATION_SERVICE)).

                notify(randomInt, notification);

    }
}


