package com.example.cryptohelper;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.util.Pools;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class select extends  MainActivity {
    EditText Per, Pri, SName, Svalue;
    String notif, Name = "Bitcoin", val, Bname,rgh;
    long Current_Value;
    CurrencyModal Ov;
    long  Old_Value=0, T_Value;
    long ac = 0;
    long Peri;
    long Prii;
    int i,pos=0,posc=0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        Intent intent = getIntent();
        Per = (EditText) findViewById(R.id.ChangePercentage);
        Pri = (EditText) findViewById(R.id.ReminderPrice);
        SName = (EditText) findViewById(R.id.Activecoin);
        Svalue = (EditText) findViewById(R.id.ActivecoinValue);
    }


    public void currentVal() {
        Name = Bname;
        posc=0;
        for (CurrencyModal item : currencyModalArrayList) {
            posc++;
            if (item.getName().toLowerCase().contains(Name)) {
                Ov = currencyModalArrayList.get(posc);
                Current_Value = (long) Ov.price;
            }
        }
    }


    //Method to Create thread

    class exr implements Runnable {
        long Value;
        String name;
        char O;

        exr(long Value, String name, char O) {
            this.Value = Value;
            this.name = name;
            this.O = O;
        }

        @Override
        public void run() {

                if (O == 'U')
                {
                    for (i = 0; ; ) {
                        try {
                            Thread.sleep(5000);
                            i++;
                            currentVal();
                            if (Current_Value >= Value) {
                                sendNotification(name + "'s" + "Price is now " + Current_Value);
                                Thread.interrupted();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    for (i = 0; ; )
                    {

                        try {
                        Thread.sleep(5000);
                        i++;
                        currentVal();
                        if (Current_Value <= Value) {
                            sendNotification(name + "'s" + "Price is now " + Current_Value);
                            Thread.interrupted();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
    //Method To Start thread

    private void startThread(long Value, String name, char O) {
        exr runnable = new exr(Value, name, O);
        new Thread(runnable).start();
    }

    //Method for sending notification

    public void sendNotification(String notif) {
        {
            NotificationChannel channel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(select.this, "My notification");
        builder.setContentTitle(("Price Alert"));
        builder.setContentText(notif);
        builder.setSmallIcon(R.drawable.ic_baseline_arrow_circle_up_24);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(select.this);
        managerCompat.notify(1, builder.build());

    }


    //Method for searching value

    public void Search() {
        Name = SName.getText().toString();
        Bname = Name;
        pos = 0;
        for (CurrencyModal item : currencyModalArrayList) {
            if (item.getName().toLowerCase().contains(Name)) {
                Ov = currencyModalArrayList.get(pos);
                Old_Value = (int)Ov.price;
                val = String.valueOf(Old_Value);
                rgh = Ov.name;
                Svalue.setText(val);
                ac=pos;
            }
            pos++;
        }
        sendNotification(rgh + "  Price is " + Old_Value);
    }

    //


    //Percentage Up Reminder

    public void setReminderUP(View view) {
        Search();
        Peri = Integer.parseInt(Per.getText().toString());
        T_Value = Old_Value * (1 + (Peri / 100));
        startThread(T_Value, Name, 'U');
    }

    //Percentage Down Reminder

    public void setReminderDown(View view) {
        Search();
        Peri = Integer.parseInt(Per.getText().toString());
        T_Value = Old_Value * (1 - (Peri / 100));
        startThread(T_Value, Name, 'D');
    }

    //PRICE Reminder

    public void setReminderPriceUp(View view) {
        Search();
        Prii = Integer.parseInt(Pri.getText().toString());
        T_Value = Prii;
        startThread(T_Value, Name, 'U');
    }

    //Price Reminder down

    public void setReminderPriceDown(View view) {
        Search();
        //Prii = Integer.parseInt(Pri.getText().toString());
        //T_Value = Prii;
        //startThread(T_Value, Name, 'D');
    }
}



