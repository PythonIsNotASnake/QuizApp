package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    public final int LOAD_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("5",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            nm.createNotificationChannel(channel);
        }

        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(this);
        myBuilder.setChannelId("5");
        myBuilder.setCategory(Notification.CATEGORY_PROGRESS);
        //Intent openIntent = new Intent(this, StartActivity.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, openIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //myBuilder.addAction(R.drawable.medaille, "Sieger", pendingIntent);
        myBuilder.setColor(getResources().getColor(R.color.colorPrimary));
        myBuilder.setContentText("Ich bin eine Notification!");
        myBuilder.setContentTitle("Notification");
        myBuilder.setSmallIcon(R.drawable.medaille);
        myBuilder.setPriority(Notification.PRIORITY_LOW);
        myBuilder.setLights(Color.argb(255, 0, 0, 255), 10, 10);
        //myBuilder.setProgress(100, 42, false);
        //long[] vibrate = {10};
        //myBuilder.setVibrate(vibrate);
        // myBuilder.setContentIntent()
        nm.notify(5, myBuilder.build());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToStart = new Intent(MainActivity.this, StartActivity.class);
                startActivity(goToStart);
                finish();
            }
        }, this.LOAD_TIME);
    }
}