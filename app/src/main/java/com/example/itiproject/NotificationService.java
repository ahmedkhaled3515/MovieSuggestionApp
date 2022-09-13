package com.example.itiproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {
    final static String NOTIFICATION_CHANNEL_ID="ahmedkhaled";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

    }
    public void notify(Intent intent)
    {
        Bundle extra=intent.getExtras();
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,"notification",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Refresh channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
        intent1.putExtras(extra);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder notificationCompat=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
//                .setAutoCancel(true)
//                .setOngoing(false)
//                .setContentTitle(extra.getString("title"))
//                .setContentText(extra.getString("body"))
//                .setWhen(System.currentTimeMillis())
//                .setContentIntent(pendingIntent)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setSound(soundUri)
//                .setSmallIcon(R.drawable.ic_launcher_background);
//        notificationManager.notify(0,notificationCompat.build());
//        Log.d("Notification", extra.getString("title")+extra.getString("body"));
    }
}
