package com.example.cryptohelper;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class select extends  AppCompatActivity {

    public void sendNotification (String notif)
    {
        {
            NotificationChannel channel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder= new NotificationCompat.Builder(select.this,"My notification");
        builder.setContentTitle(("My Title"));
        builder.setContentText(notif);
        builder.setSmallIcon(R.drawable.ic_baseline_arrow_circle_up_24);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(select.this);
        managerCompat.notify(1,builder.build());

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        Intent intent=getIntent();
        int position =intent.getIntExtra("position",0);
        pos = position;

        EditText Pri = (EditText) findViewById(R.id.ReminderPrice);
        EditText Per = (EditText) findViewById(R.id.ChangePercentage);
        Pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y;
                y = Integer.parseInt(Per.getText().toString());
                Prii = y;
            }
        });
        Per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int z;
                z = Integer.parseInt(Per.getText().toString());
                Peri = z;
            }
        });
    }

    String notif,Name="Bitcoin";
    int Current_Value = 50000,Old_Value,pos,ac=0,Peri,Prii;

    public void setReminderUP(View view) {
        ac=1;
        //notif=Name +"'s Price is now " + Current_Value +"$ ";
        notif = "Entered % is " + Peri;
        sendNotification(notif);

    }

    public void setReminderDown(View view) {
        ac=2;
        {
            notif=Name +"'s Price is now " + Current_Value +"$ ";
            sendNotification(notif);
        }
    }
    public void setReminderPrice(View view) {

        //notif=Name +"'s Price is now " + Current_Value +"$ ";
        notif = "Entered % is " + Prii;
        sendNotification(notif);

    }
    public void deleteReminder(View view) {
        //notif=Name +"'s Price is now " + Current_Value +"$ ";
        int pzo = (int) view.getTag();
        notif = "Position IS" + pzo;

        sendNotification(notif);
    }




    /*public String notif;
    public String Name="Bitcoin";
    int Current_Value = 50000;
    int Old_Value;
    int pos;

    public void sendNotification(String notif)
    {
        {
            NotificationChannel channel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder= new NotificationCompat.Builder(select.this,"My notification");
        builder.setContentTitle(("My Title"));
        builder.setContentText(notif);
        builder.setSmallIcon(R.drawable.ic_baseline_arrow_circle_up_24);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(select.this);

        managerCompat.notify(1,builder.build());

    }

    public int ac=0;
    public void setReminderUP(View view) {
        ac=1;
        //notif=Name +"'s Price is now " + Current_Value +"$ ";
        notif = "Entered % is " ;
        sendNotification(notif);

    }
    public void setReminderDown(View view) {
        ac=2;
        {
            notif=Name +"'s Price is now " + Current_Value +"$ ";
            sendNotification(notif);
        }
    }
    public void setReminderPrice(View view) {

        notif=Name +"'s Price is now " + Current_Value +"$ ";
        sendNotification(notif);

    }

    public void deleteReminder(View view) {
        notif=Name +"'s Price is now " + Current_Value +"$ ";
        sendNotification(notif);
    }*/

}
